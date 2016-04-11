package Review.r_server.userInfo;

import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserService;
import Review.r_server.paperInfo.PaperInfo;
import Review.r_server.paperInfo.PaperInfoService;
import Review.r_util.R_FileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/2/2016
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "user")
public class UserInfoController {
    private static Logger logger = Logger.getLogger(UserInfoController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PaperInfoService paperInfoService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userInfo() {
        logger.info("user:\t" + 1 + "check_info");
        return "functionJsp/userInfo/userInfo";
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String infoUpdate(UserInfo studentInfo, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("user:\t" + 1 + "check_info");
        userInfo.setPhone(studentInfo.getPhone() == null && studentInfo.getPhone().equals("") ? userInfo.getPhone() : studentInfo.getPhone());
        userInfo.setEmail(studentInfo.getEmail() == null && studentInfo.getEmail().equals("") ? userInfo.getEmail() : studentInfo.getEmail());
        userInfoService.updateUserInfo(userInfo);
        request.getSession().removeAttribute("userInfo");
        request.getSession().setAttribute("userInfo", userInfo);
        return "redirect:info";
    }

    //跳转到学生信息添加页面
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("!@# " + userInfo.getReal_name() + "turn to add student page");
        return "functionJsp/userInfo/addStudent";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addStudentPost(String username, String password, String place, String stu_tch_name, String real_name, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        System.out.println(username + " " + password + " " + real_name + " " + stu_tch_name + " " + place);
        Map<String, String> msg = new ManagedMap<String, String>();
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setState(1);

        UserInfo newUserInfo = new UserInfo();

        if ((userService.selectByUsername(newUser.getUsername()) == null) && userService.insertUser(newUser) == 1) {
            newUser = userService.selectByUsername(newUser.getUsername());
            newUserInfo.setUser_id(newUser.getId());
            newUserInfo.setStu_paper_status(0);
            newUserInfo.setReal_name(real_name);
            newUserInfo.setStu_tch_name(stu_tch_name);
            newUserInfo.setPlace(place);
            newUserInfo.setCreate_time(new Date());
            newUserInfo.setCreate_person_id(userInfo.getUser_id());
            if (userInfoService.insertUserInfo(newUserInfo) != 0) {
                //给上传的用户添加 角色
                userService.insertUserRole(newUserInfo.getUser_id(), (long) 5);
                System.out.println("^^^^^^^^^ user " + newUserInfo.getReal_name() + " success!");
                msg.put("result", "1");
                msg.put("tip", "添加成功");
            } else {
                System.out.println("^^^^^^^^^ user " + newUserInfo.getReal_name() + " insertUserInfo error!");
                msg.put("result", "0");
                msg.put("tip", "学号冲突");
            }
        } else {
            System.out.println("^^^^^^^^^ user " + newUserInfo.getReal_name() + " insertUser error!");
            msg.put("result", "0");
            msg.put("tip", "学号冲突");
        }
        logger.info("!@# " + userInfo.getReal_name() + " insert new  student");
        return msg;
    }


    //跳转到 学生列表
    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    public String studentList(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!");

        List<UserInfo> studentList = userInfoService.selectStudentList();
        for (UserInfo info : studentList) {
            if (info.getStu_paper_status() == 1) {
                PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(info.getUser_id());
                info.setStu_paper_name(paperInfo.getPaper_name());
            }
        }
        model.addAttribute("studentList", studentList);
        return "functionJsp/userInfo/studentList";
    }


    //跳转到学生信息导入页面
    @RequestMapping(value = "/importStudentListPage", method = RequestMethod.GET)
    public String importStudentListPage() {
        return "functionJsp/userInfo/importStudentListPage";
    }

    //
    @RequestMapping(value = "/downloadStudentModelExcel", method = RequestMethod.GET)
    public void downloadStudentModelExcel(HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        String path = request.getSession().getServletContext()
                .getRealPath("/serverFile/modelExcel")
                + "\\" + "student_demo.xlsx";
        if (R_FileUtils.fileDowmLoad(path, "学生样例模板.xlsx", response)) {

            logger.info("!@#：" + userInfo.getReal_name() + " download student model excel success!!!");
        } else {
            logger.info("!@#：" + userInfo.getReal_name() + " download student model excel failure!!!");

        }
    }

    /**
     * 导入学生信息
     *
     * @param request
     * @param import_student_file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importStudentDataExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importStudentDataExcel(HttpServletRequest request,
                                                      @RequestParam("import_student_file") MultipartFile import_student_file)
            throws IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("!@#：" + userInfo.getReal_name() + " import Student Data Excel success!!!");
        Map<String, Object> msg = new HashMap<String, Object>();
        if (import_student_file.isEmpty()) {
            logger.info("user:" + userInfo.getReal_name() + "\t import empty student_excel ");
            msg.put("result", "0");
            msg.put("tip", "不能提交空文件");
        } else {
            String path = request.getSession().getServletContext()
                    .getRealPath("/serverFile/import_data") + "\\"
                    + "学生信息表[" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "].xlsx";
            File f = new File(path); // 输入要删除的文件位置
            if (f.exists()) {
                f.delete();
            }
            FileUtils.copyInputStreamToFile(import_student_file.getInputStream(), f);
            Map<String, Object> importResult = userInfoService.importStudentFromExcel(path);
            if ("success".equals(importResult.get("result"))) {

                List<UserInfo> userInfoList = (List<UserInfo>) importResult.get("userInfoList");
                List<UserInfo> errorUserInfoList = new ArrayList<UserInfo>();
                User user = new User();

                System.out.println("userInfoList@@@@@@@@@@@@@@" + userInfoList.size());
                for (int i = 0; i < userInfoList.size(); i++) {
                    UserInfo info = userInfoList.get(i);

                    user.setUsername(info.getUsername());
                    user.setPassword(info.getPassword());
                    user.setState(1);

                    if ((userService.selectByUsername(user.getUsername()) == null) && userService.insertUser(user) == 1) {
                        user = userService.selectByUsername(info.getUsername());
                        info.setUser_id(user.getId());
                        info.setStu_paper_status(0);
                        if (userInfoService.insertUserInfo(info) != 0) {
                            //给上传的用户添加 角色
                            userService.insertUserRole(info.getUser_id(), (long) 5);
                            System.out.println(i + "^^^^^^^^^ user " + info.getReal_name() + " success!");
                        } else {
                            System.out.println(i + "^^^^^^^^^ user " + info.getReal_name() + " insertUserInfo error!");
                            errorUserInfoList.add(info);
                        }
                    } else {
                        System.out.println(i + "^^^^^^^^^ user " + info.getReal_name() + " insertUser error!");
                        errorUserInfoList.add(info);
                    }
                }
                System.out.println("errorUserInfoList##############" + errorUserInfoList.size());
                msg.put("errorUserInfoList", errorUserInfoList);
                msg.put("result", "1");
                msg.put("tip", "导入成功");

            } else {
                msg.put("result", "0");
                msg.put("tip", "导入失败");
            }


            msg.put("result", "1");
            msg.put("tip", "导入成功");
        }
        return msg;
    }


}
