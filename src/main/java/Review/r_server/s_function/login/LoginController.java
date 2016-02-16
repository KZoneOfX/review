package Review.r_server.s_function.login;

import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserServiceImpl;
import Review.r_server.basic.security.PermissionSign;
import Review.r_server.basic.security.RoleSign;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping(value = "/user")
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private UserServiceImpl userService;

    /**
     * @param user
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request){
        logger.info("login user"+user.toString());
        try {
            Subject subject = SecurityUtils.getSubject();
            //如果已登陆，则调到主页
            if (subject.isAuthenticated()){
                return "redirect:/rest/backgroundindex";
            }
            if (result.hasErrors()){
                model.addAttribute("error","参数错误！");
                return "back/login";
            }
            //身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo",authUserInfo);
        } catch (AuthenticationException e){
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "back/login";
        }
        return "redirect:/rest/backgroundindex";
    }

    /**
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/welcome";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }
}
