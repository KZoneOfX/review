package Review.r_basic.r_b_permission;

import Review.r_server.userInfo.UserInfo;
import org.apache.log4j.Logger;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/9/2016
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "permission")
public class PermissionController {
    private static Logger logger = Logger.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "")
    public String permission() {
        return "functionJsp/permission/permission";
    }

    @RequestMapping(value = "permissionList", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> permissionList(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("### " + userInfo.getReal_name() + " ### permissionList " + request.getRequestURI());
        return permissionService.selectAllPermissions();
    }

    @RequestMapping(value = "updatePermission", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> updatePermission(HttpServletRequest request, Permission permission) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        logger.info("### " + userInfo.getReal_name() + " ### updatePermission " + request.getRequestURI());
        System.out.println(permission);
//        permissionService
        return permissionService.selectAllPermissions();
    }
}
