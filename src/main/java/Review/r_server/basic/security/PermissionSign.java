package Review.r_server.basic.security;

/**
 *  权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 *
 * <pre>
 * &#064;RequiresPermissions(value = PermissionConfig.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 * Created with IntelliJ IDEA.
 * User: KZoneOfX
 * Date: 2015/8/14
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */

public class PermissionSign {

    /**
     * 用户新增权限 标识
     */
    public static final String USER_CREATE = "user:create";

    /**
     * 用户删除权限 标识
     */
    public static final String USER_DELETE = "user:delete";

    /**
     * 添加更多...
     */
}
