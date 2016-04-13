package Review.r_server.index;

import Review.r_server.userInfo.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if ("developer".equals(userInfo.getRole_description())){
            return "redirect:user/studentList";
        } else {
            return "index";
        }
    }

}
