package Review.r_server.userInfo;

import Review.r_basic.r_b_user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/check_info", method = RequestMethod.GET)
    public String userInfo() {
        logger.info("user:\t" + 1 + "check_info");
        return "functionJsp/userInfo/userInfo";
    }

}
