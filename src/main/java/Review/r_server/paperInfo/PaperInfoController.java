package Review.r_server.paperInfo;

import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserService;
import Review.r_server.userInfo.UserInfo;
import Review.r_server.userInfo.UserInfoService;
import Review.r_util.R_FileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Date: 3/31/2016
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "paperInfo")
public class PaperInfoController {
    private static Logger logger = Logger.getLogger(PaperInfoController.class);
    @Autowired
    private PaperInfoService paperInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String paperInfo() {
        logger.info("!@#  turn to paperInfo.jsp");
        return "functionJsp/paperInfo/paperInfo";
    }


    @RequestMapping(value = "submit", method = RequestMethod.GET)
    public String submit() {
        logger.info("!@#  get to submit.jsp");
        return "functionJsp/paperInfo/submit";
    }

    @RequestMapping(value = "{stu_username}/upload", method = RequestMethod.GET)
    public String submit(@PathVariable String stu_username, HttpServletRequest request, Model model) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        User student = userService.selectByUsername(stu_username);
        UserInfo studentInfo = userInfoService.selectUserInfoByUserId(student.getId());
        logger.info("!@#  " + userInfo.getReal_name() + " go to submit " + studentInfo.getReal_name() + "`s paper");
        model.addAttribute("stu_username", stu_username);
        model.addAttribute("stu_real_name", stu_username);
        return "functionJsp/paperInfo/submit";
    }

    /**
     * TODO 判断 登陆者角色 判断是否是学生自己提交 还是 学校协助提交 需要考虑 提交者身份问题 、反馈信息 未显示
     *
     * @param submit_paper_file
     * @param httpServletRequest
     * @param submit_paper_name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> submit(@RequestParam("submit_paper_file") MultipartFile submit_paper_file,
                                      HttpServletRequest httpServletRequest, String submit_paper_name) throws IOException {
        UserInfo userInfo = (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        Map<String, String> msg = new HashMap<String, String>();
        if (submit_paper_file.isEmpty() || submit_paper_name.equals("") || submit_paper_file == null) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit empty paper ");
            msg.put("result", "0");
            msg.put("tip", "提交信息，不得为空");
        } else if (!(submit_paper_file.getOriginalFilename().endsWith(".pdf")
                || submit_paper_file.getOriginalFilename().endsWith(".doc")
                || submit_paper_file.getOriginalFilename().endsWith(".docx"))) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit error format paper ");
            msg.put("result", "0");
            msg.put("tip", "网站只允许提交pdf、doc、docx格式论文，其他格式论文，请转换格式后，重新提交");
        } else {
            String base_path = httpServletRequest.getSession().getServletContext().getRealPath("/");
            String paper_path = "serverFile/paper/" + userInfo.getUsername()
                    + "_" + userInfo.getReal_name() + "_" + submit_paper_file.getOriginalFilename();
            String file_path = base_path + paper_path;
            File file;
            PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(userInfo.getUser_id());
            if (paperInfo != null) {
                file = new File(paperInfo.getPaper_path());
                paperInfoService.deletePaperInfo(paperInfo);
                httpServletRequest.getSession().removeAttribute("paperInfo");
                if (file.exists()) {
                    file.delete();
                }
            }
            paperInfo = new PaperInfo();
            file = new File(file_path);
            FileUtils.copyInputStreamToFile(submit_paper_file.getInputStream(), file);//将上传的论文 copy到服务器中

            paperInfo.setId(UUID.randomUUID().toString());
            paperInfo.setPaper_submit_person(userInfo.getReal_name());
            paperInfo.setPaper_path(paper_path);
            paperInfo.setPaper_create_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            paperInfo.setPaper_name(submit_paper_name);
            paperInfo.setState(1);
            paperInfo.setUser_id(userInfo.getUser_id());
            userInfo.setStu_paper_status(1);
            userInfoService.updateUserInfo(userInfo);
            int success = paperInfoService.insertPaperInfo(paperInfo);

            if (success == 1) {
                System.out.println("submit success!!!!");
            }

            httpServletRequest.getSession().setAttribute("paperInfo", paperInfo);
            logger.info("!@# post to submit.jsp");
            msg.put("result", "1");
            msg.put("tip", "上传成功");
        }
        return msg;
    }

    /**
     * TODO 判断 登陆者角色 判断是否是学生自己提交 还是 学校协助提交 需要考虑 提交者身份问题 、反馈信息 未显示
     *
     * @param submit_paper_file
     * @param httpServletRequest
     * @param submit_paper_name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "submitByManager", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> submitByManager(@RequestParam("submit_paper_file") MultipartFile submit_paper_file,
                                               HttpServletRequest httpServletRequest,
                                               String submit_paper_name,
                                               String std_username
    ) throws IOException {
        UserInfo userInfo = (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        Map<String, String> msg = new HashMap<String, String>();
        User student = userService.selectByUsername(std_username);
        UserInfo studentInfo = userInfoService.selectUserInfoByUserId(student.getId());

        if (submit_paper_file.isEmpty() || submit_paper_name.equals("") || submit_paper_file == null) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit " + studentInfo.getReal_name() + "`s paper is empty paper ");
            msg.put("result", "0");
            msg.put("tip", "提交信息，不得为空");
        } else if (!(submit_paper_file.getOriginalFilename().endsWith(".pdf")
                || submit_paper_file.getOriginalFilename().endsWith(".doc")
                || submit_paper_file.getOriginalFilename().endsWith(".docx"))) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit " + studentInfo.getReal_name() + "`s paper is error format paper  ");
            msg.put("result", "0");
            msg.put("tip", "网站只允许提交pdf、doc、docx格式论文，其他格式论文，请转换格式后，重新提交");
        } else {
            logger.info("user:" + userInfo.getReal_name() + "\t submit " + studentInfo.getReal_name() + "`s paper is right format paper  ");
            String base_path = httpServletRequest.getSession().getServletContext().getRealPath("/");
            String paper_path = "serverFile/paper/" + studentInfo.getUsername()
                    + "_" + studentInfo.getReal_name() + "_" + submit_paper_file.getOriginalFilename();
            String file_path = base_path + paper_path;
            File file;
            PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(studentInfo.getUser_id());
            if (paperInfo != null) {
                logger.info("user:" + userInfo.getReal_name() + "\t submit " + studentInfo.getReal_name() + "`s paper : paper  exist ");
                file = new File(paperInfo.getPaper_path());
                paperInfoService.deletePaperInfo(paperInfo);
                if (file.exists()) {
                    file.delete();
                }
            }
            paperInfo = new PaperInfo();
            file = new File(file_path);
            FileUtils.copyInputStreamToFile(submit_paper_file.getInputStream(), file);//将上传的论文 copy到服务器中

            paperInfo.setId(UUID.randomUUID().toString());
            paperInfo.setPaper_submit_person(userInfo.getReal_name());
            paperInfo.setPaper_path(paper_path);
            paperInfo.setPaper_create_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            paperInfo.setPaper_name(submit_paper_name);
            paperInfo.setState(1);
            paperInfo.setUser_id(studentInfo.getUser_id());
            studentInfo.setStu_paper_status(1);
            userInfoService.updateUserInfo(studentInfo);
            int success = paperInfoService.insertPaperInfo(paperInfo);
            if (success == 1) {
                logger.info("user:" + userInfo.getReal_name() + "\t submit " + studentInfo.getReal_name() + "`s paper : paper  success！！！！ ");
                msg.put("result", "1");
                msg.put("tip", "上传成功");
            } else {
                msg.put("result", "0");
                msg.put("tip", "插入 数据库失败！");

            }
        }
        return msg;
    }

    /**
     * 通过学生编号，打包下载学生论文
     *
     * @param select   学生学号 字符串
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "downLoadZip", method = RequestMethod.POST)
    @ResponseBody
    public void downLoadZip(String select, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        //服务器根目录
        String base_path = request.getSession().getServletContext().getRealPath("/");
        logger.info("!@#  " + userInfo.getReal_name() + "prepare to download the paper zip");
        Map<String, String> msg = new HashMap<String, String>();
        String[] arr = select.split("\\D");
        //待压缩 论文所在的文件夹 名称
        String folder_name = "[" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "]";
        //待压缩 论文所在的文件夹 路径
        String folder_path = base_path + "serverFile/paper/ZipFiles/" + "[" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "]";
        File folder = new File(folder_path);
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("//不存在");
            folder.mkdir();
        } else {
            System.out.println("//目录存在::" + folder_path);
        }
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                User student = userService.selectByUsername(arr[i]);
                UserInfo studentInfo = userInfoService.selectUserInfoByUserId(student.getId());
                PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(studentInfo.getUser_id());
                String suffix = R_FileUtils.getFileSufix(paperInfo.getPaper_path());  //论文名后缀
                String target_file_name = studentInfo.getUsername() + "_" + studentInfo.getReal_name() + "." + suffix; //文件夹中 论文的名称
                File paper_file = new File(base_path + paperInfo.getPaper_path());
                File zip_paper_file = new File(folder_path + "/" + target_file_name);
                if (paper_file.exists() && !zip_paper_file.exists()) {
                    R_FileUtils.copyFile(base_path + paperInfo.getPaper_path(), folder_path + "/" + target_file_name);
                }
            }
        }
        //压缩 文件夹
        if (R_FileUtils.fileToZip(folder_path, base_path + "serverFile/paper/ZipFiles/", folder_name, folder_name)) {
            //压缩包 所在路径
            String zip_path = base_path + "serverFile\\paper\\ZipFiles\\" + folder_name + ".zip";

            //zip文件下载
            if (R_FileUtils.fileDowmLoad(zip_path, folder_name + ".zip", response)) {
                logger.info("!@#：" + userInfo.getReal_name() + " download student paper  zip success!!!");
            } else {
                logger.info("!@#：" + userInfo.getReal_name() + " download student paper  zip failure!!!");

            }
            if (R_FileUtils.deleteDir(folder) && new File(zip_path).delete()) {
                logger.info("!@#：" + userInfo.getReal_name() + " delete zip and folder   success!!!");
            } else {
                logger.info("!@#：" + userInfo.getReal_name() + " delete zip and folder   failure!!!");
            }
        } else {
            logger.info("!@#：" + userInfo.getReal_name() + " compress papers to zip failure !!!");
        }
    }

    /**
     * 通过学生学号，下载学生论文
     *
     * @param stu_username
     * @param request
     * @param response
     */
    @RequestMapping(value = "{stu_username}/download", method = RequestMethod.GET)
    public void paperDownload(@PathVariable String stu_username, HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        String bast_path = request.getSession().getServletContext().getRealPath("/");
        User stu_user = userService.selectByUsername(stu_username);
        UserInfo stu_userInfo = userInfoService.selectUserInfoByUserId(stu_user.getId());
        PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(stu_user.getId());
        if (stu_user != null && stu_userInfo != null && paperInfo != null) {
            String paper_path = bast_path + paperInfo.getPaper_path();
            String paper_name = stu_user.getUsername() + "_" + stu_userInfo.getReal_name() + "." + R_FileUtils.getFileSufix(paperInfo.getPaper_path());
            R_FileUtils.fileDowmLoad(paper_path, paper_name, response);
            logger.info("!@#  " + userInfo.getReal_name() + " download student:" + stu_userInfo.getReal_name() + " paper:" + paper_name + "success!!");
        } else {

            logger.info("!@#  " + userInfo.getReal_name() + " download student:" + stu_userInfo.getReal_name() + " failure!!");
        }

    }

    /**
     * 通过学生学号，下载学生论文
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void studentDownloadPaper(HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        PaperInfo paperInfo = paperInfoService.selectPaperInfoByUserId(userInfo.getUser_id());
        if (paperInfo != null) {
            String bast_path = request.getSession().getServletContext().getRealPath("/");
            String paper_path = bast_path + paperInfo.getPaper_path();
            String paper_name = userInfo.getUsername() + "_" + userInfo.getReal_name() + "." + R_FileUtils.getFileSufix(paperInfo.getPaper_path());
            R_FileUtils.fileDowmLoad(paper_path, paper_name, response);
            logger.info("!@#  " + userInfo.getReal_name() + " download  paper:" + paper_name + "success!!");
        } else {
            logger.info("!@#  " + userInfo.getReal_name() + " download paper failure!!");
        }

    }


}

