package Review.r_server.basic.security;

import Review.r_basic.r_b_permission.Permission;
import Review.r_basic.r_b_permission.PermissionService;
import Review.r_basic.r_b_role.Role;
import Review.r_basic.r_b_role.RoleService;
import Review.r_basic.r_b_user.User;
import Review.r_basic.r_b_user.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:28 AM
 * Apache Shiro 授权 类
 */

@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    private static Logger logger = Logger.getLogger(SecurityRealm.class);
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        final User user = userService.selectByUsername(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        logger.info("user:"+username+" doGetAuthorizationInfo");
        logger.info(roleInfos.toString());
        for (Role role : roleInfos) {
            // 添加角色
            System.err.println(role);
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                // 添加权限
                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
            logger.info(permissions.toString());
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        logger.info("user:"+username+" doGetAuthenticationInfo  login before");
        // 通过数据库进行验证
        final User authentication = userService.authentication(new User(username, password));
        if (authentication == null) {
            logger.info("user:"+username+" doGetAuthenticationInfo  login failed");
            throw new AuthenticationException("用户名或密码错误.");
        }
        logger.info("user:"+username+" doGetAuthenticationInfo  login success");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        System.out.println(authenticationInfo.toString());
        return authenticationInfo;
    }

}
