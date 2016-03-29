package Review.r_server.paperInfo;

import Review.r_basic.r_b_user.UserService;
import Review.r_server.userInfo.UserInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    /**
     * TODO 判断 登陆者角色 判断是否是学生自己提交 还是 学校协助提交 需要考虑 提交者身份问题 、反馈信息 未显示
     *
     * @param submit_paper_file
     * @param httpServletRequest
     * @param submit_paper_name
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public String submit(@RequestParam("submit_paper_file") MultipartFile submit_paper_file,
                         HttpServletRequest httpServletRequest, String submit_paper_name, Model model) throws IOException {
        UserInfo userInfo = (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if (submit_paper_file.isEmpty() || submit_paper_name.equals("") || submit_paper_file == null) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit empty paper ");
            model.addAttribute("msg", "提交信息，不得为空");
        } else if (!(submit_paper_file.getOriginalFilename().endsWith(".pdf")
                || submit_paper_file.getOriginalFilename().endsWith(".doc")
                || submit_paper_file.getOriginalFilename().endsWith(".docx"))) {
            logger.info("user:" + userInfo.getReal_name() + "\t submit error format paper ");
            model.addAttribute("msg", "网站只允许提交pdf、doc、docx格式论文，其他格式论文，请转换格式后，重新提交");
        } else {

            PaperInfo paperInfo = new PaperInfo();
            String paper_path = httpServletRequest.getSession().getServletContext().getRealPath("/serverFile/paper")
                    + "\\" + userService.selectById(userInfo.getUser_id()).getUsername()
                    + "_" + userInfo.getReal_name() + "_" + submit_paper_file.getOriginalFilename();

            File file;
            if (paperInfoService.selectPaperInfoByUserId(userInfo.getUser_id()) != null) {
                file = new File(paperInfoService.selectPaperInfoByUserId(userInfo.getUser_id()).getPaper_path());
                if (file.exists()) {
                    file.delete();
                }
            }
            file = new File(paper_path);
            FileUtils.copyInputStreamToFile(submit_paper_file.getInputStream(), file);//将上传的论文 copy到服务器中

            paperInfo.setId(UUID.randomUUID().toString());
            paperInfo.setPaper_submit_person(userInfo.getReal_name());
            paperInfo.setPaper_path(paper_path);
            paperInfo.setPaper_create_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            paperInfo.setPaper_name(submit_paper_name);
            paperInfo.setState(1);
            paperInfo.setUser_id(userInfo.getUser_id());
            int success = paperInfoService.insertPaperInfo(paperInfo);
            if (success == 1) {
                System.out.println("submit success!!!!");
            }

            System.out.println("!!!!!!!!!!!" + paper_path);
            System.out.println(httpServletRequest.getSession().getServletContext().getContextPath());
            logger.info("!@# post to submit.jsp");
        }
        return "functionJsp/paperInfo/submit";
    }
}
