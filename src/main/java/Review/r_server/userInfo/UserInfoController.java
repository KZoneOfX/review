package Review.r_server.userInfo;

import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserService;
import Review.r_server.paperInfo.PaperInfo;
import Review.r_server.paperInfo.PaperInfoService;
import Review.r_util.MD5Util;
import Review.r_util.R_FileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    /**
     * 查看个人信息
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userInfo(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        model.addAttribute("ifCheckStudent", 0);
        logger.info("user:" + userInfo.getReal_name() + "check personal info ");
        return "functionJsp/userInfo/userInfo";
    }

    /**
     * 更新个人信息
     * @param studentInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String infoUpdate(UserInfo studentInfo, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        userInfo.setPhone(studentInfo.getPhone() == null || studentInfo.getPhone().equals("") ? userInfo.getPhone() : studentInfo.getPhone());
        userInfo.setEmail(studentInfo.getEmail() == null || studentInfo.getEmail().equals("") ? userInfo.getEmail() : studentInfo.getEmail());
        userInfoService.updateUserInfo(userInfo);
        request.getSession().removeAttribute("userInfo");
        request.getSession().setAttribute("userInfo", userInfo);
        logger.info("user:\t" + userInfo.getReal_name() + "update personal info ");
        return "redirect:info";
    }

    /**
     * 跳转到学生信息添加页面
     * @param request
     * @return
     */
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
        logger.info(username + " " + password + " " + real_name + " " + stu_tch_name + " " + place);
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
                logger.info("^^^^^^^^^ user " + newUserInfo.getReal_name() + " success!");
                msg.put("result", "1");
                msg.put("tip", "添加成功");
            } else {
                logger.info("^^^^^^^^^ user " + newUserInfo.getReal_name() + " insertUserInfo error!");
                msg.put("result", "0");
                msg.put("tip", "学号冲突");
            }
        } else {
            logger.info("^^^^^^^^^ user " + newUserInfo.getReal_name() + " insertUser error!");
            msg.put("result", "0");
            msg.put("tip", "学号冲突");
        }
        logger.info("!@# " + userInfo.getReal_name() + " insert new  student");
        return msg;
    }


    /**
     *跳转到 学生列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    public String studentList(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("!@# " + userInfo.getReal_name() + " turn to studentList");

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


    /**
     *
     * 跳转到学生信息导入页面
     * @return
     */
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
            if (f.exists()) f.delete();
            FileUtils.copyInputStreamToFile(import_student_file.getInputStream(), f);
            Map<String, Object> importResult = userInfoService.importStudentFromExcel(path);
            if ("success".equals(importResult.get("result"))) {

                List<UserInfo> userInfoList = (List<UserInfo>) importResult.get("userInfoList");
                List<UserInfo> errorUserInfoList = new ArrayList<UserInfo>();
                User user = new User();

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
                            logger.info(i + "^^^^^^^^^ user " + info.getReal_name() + " insertUserInfo success!");
                        } else {
                            logger.info(i + "^^^^^^^^^ user " + info.getReal_name() + " insertUserInfo error!");
                            errorUserInfoList.add(info);
                        }
                    } else {
                        logger.info(i + "^^^^^^^^^ user " + info.getReal_name() + " insertUser error!");
                        errorUserInfoList.add(info);
                    }
                }
                logger.info("errorUserInfoList##############" + errorUserInfoList.size());
                msg.put("errorUserInfoList", errorUserInfoList);
                msg.put("result", "1");
                msg.put("tip", "导入成功");
            } else {
                msg.put("result", "0");
                msg.put("tip", "导入失败");
            }
        }
        return msg;
    }


    /**
     * 显示学生信息
     * @param stu_username
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/{stu_username}/info", method = RequestMethod.GET)
    public String studentInfo(@PathVariable String stu_username, HttpServletRequest request, Model model) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        User student = userService.selectByUsername(stu_username);
        UserInfo studentInfo = userInfoService.selectUserInfoByUserId(student.getId());
        model.addAttribute("studentInfo", studentInfo);
        model.addAttribute("ifCheckStudent", 1);
        logger.info("user:" + userInfo.getReal_name() + " check " + studentInfo.getReal_name() + " info");
        return "functionJsp/userInfo/userInfo";
    }

    /**
     * 更新学生信息
     * @param studentInfo
     * @param stu_username
     * @param request
     * @return
     */
    @RequestMapping(value = "/{stu_username}/info", method = RequestMethod.POST)
    public String studentInfoUpdate(UserInfo studentInfo, @PathVariable String stu_username, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        User student = userService.selectByUsername(stu_username);
        UserInfo studentInfoOld = userInfoService.selectUserInfoByUserId(student.getId());
        studentInfoOld.setReal_name(studentInfo.getReal_name() == null || studentInfo.getReal_name().equals("") ? studentInfoOld.getReal_name() : studentInfo.getReal_name());
        studentInfoOld.setStu_tch_name(studentInfo.getStu_tch_name() == null || studentInfo.getStu_tch_name().equals("") ? studentInfoOld.getStu_tch_name() : studentInfo.getStu_tch_name());
        studentInfoOld.setPlace(studentInfo.getPlace() == null || studentInfo.getPlace().equals("") ? studentInfoOld.getPlace() : studentInfo.getPlace());
        studentInfoOld.setEmail(studentInfo.getEmail() == null || studentInfo.getEmail().equals("") ? studentInfoOld.getEmail() : studentInfo.getEmail());
        studentInfoOld.setPhone(studentInfo.getPhone() == null || studentInfo.getPhone().equals("") ? studentInfoOld.getPhone() : studentInfo.getPhone());
        userInfoService.updateUserInfo(studentInfoOld);
        logger.info("user:" + userInfo.getReal_name() + " update " + studentInfo.getReal_name() + " info");
        return "redirect:info";
    }

    /**
     * 重置学生密码
     *
     * @param username
     * @param request
     * @return
     */
    @RequestMapping(value = "/restPassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> restPassword(String username, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Map<String, Object> msg = new HashMap<String, Object>();
        User student = userService.selectByUsername(username);
        UserInfo studentInfo = userInfoService.selectUserInfoByUserId(student.getId());
        student.setPassword(new MD5Util().compute(student.getUsername() + student.getUsername()));
        if (userService.updateUser(student) == 1) {
            msg.put("result", "1");
            msg.put("tip", "重置密码成功！");
            logger.info("user:" + userInfo.getReal_name() + " rest " + studentInfo.getReal_name() + "`s password success  !!!!");
        } else {
            msg.put("result", "0");
            msg.put("tip", "重置密码失败！");
            logger.info("user:" + userInfo.getReal_name() + " rest " + studentInfo.getReal_name() + "`s password failure  !!!!");
        }
        return msg;
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    public String toChangePwd(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("!@# " + userInfo.getReal_name() + " turn to change personal password");
        return "functionJsp/userInfo/changePwd";
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePwd(String password, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        System.out.println(password);
        Map<String, Object> msg = new HashMap<String, Object>();
        User user = userService.selectByUsername(userInfo.getUsername());
        user.setPassword(password);
        if (userService.updateUser(user) == 1) {
            logger.info("user:" + userInfo.getReal_name() + " change personal password success  !!!!");
            msg.put("result", 1);
        } else {
            logger.info("user:" + userInfo.getReal_name() + " change personal password failure  !!!!");
            msg.put("result", 0);
        }
        return msg;
    }
}
