package Review.r_server.login;

import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserService;
import Review.r_server.userInfo.UserInfo;
import Review.r_server.userInfo.UserInfoService;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 *
 * Created with IntelliJ IDEA.
 * User: KZoneOfX
 * Date: 2015/8/14
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    /**
     * @param user
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        Map<String, String> responseJSON = new HashMap<String, String>();
        logger.info("login user" + user.getUsername());
        try {
            Subject subject = SecurityUtils.getSubject();

            //如果已登陆，则调到主页
            if (subject.isAuthenticated()){
                responseJSON.put("result", "yes");
                return responseJSON;
            }
            if (result.hasErrors()){
                responseJSON.put("result", "no");
                responseJSON.put("error", "参数错误!");
                return responseJSON;
            }
            //身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            UserInfo userInfo = userInfoService.selectUserInfoByUserId(authUserInfo.getId());

            System.out.println(userInfo.toString());

//            request.getSession().setAttribute("userInfo",authUserInfo);
            request.getSession().setAttribute("userInfo", userInfo);
        } catch (AuthenticationException e){
            // 身份验证失败

            responseJSON.put("result", "no");
//            responseJSON.put("Exception:",e.toString());
//            System.out.println("e.getLocalizedMessage()\t"+e.getLocalizedMessage());
//            System.out.println("e.getMessage()\t"+e.getMessage());
//            System.out.println("e.getCause()\t"+e.getCause());
            responseJSON.put("error", "用户名或密码错误!");
            return responseJSON;
        }
        responseJSON.put("result", "yes");
        return responseJSON;
    }

    /**
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> logout(HttpSession session) {
        Map<String, String> responseJSON = new HashMap<String, String>();
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        logger.info("user :" + user.getReal_name() + " Logout!");
        session.removeAttribute("userInfo");
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        responseJSON.put("result", "yes");
        return responseJSON;
    }
//
//    /**
//     * 基于角色 标识的权限控制案例
//     */
//    @RequestMapping(value = "/admin")
//    @ResponseBody
//    @RequiresRoles(value = RoleSign.ADMIN)
//    public String admin() {
//        return "拥有admin角色,能访问";
//    }
//
//    /**
//     * 基于权限标识的权限控制案例
//     */
//    @RequestMapping(value = "/create")
//    @ResponseBody
//    @RequiresPermissions(value = PermissionSign.USER_CREATE)
//    public String create() {
//        return "拥有user:create权限,能访问";
//    }
}
