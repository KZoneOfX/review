package Review.r_server.basic.security;

/**
 * 角色标识配置类, <br>
 * 与 role_info 角色表中的 role_sign 字段 相对应 <br>
 * 使用:
 *
 * <pre>
 * &#064;RequiresRoles(value = RoleSign.ADMIN)
 * public String admin() {
 *     return &quot;拥有admin角色,能访问&quot;;
 * }
 * </pre>
 *
 * Created with IntelliJ IDEA.
 * User: KZoneOfX
 * Date: 2015/8/14
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */

public class RoleSign {

    /**
     * 普通后台管理员 标识
     */
    public static final String ADMIN = "admin";

    /**
     * 客户经理 标识
     */
    public static final String CONSULTANT = "consultant";

    /**
     * VIP客户 标识
     */
    public static final String VIP_USER = "vip_user";

    /**
     * 商家 标识
     */
    public static final String MERCHANT = "merchant";

    /**
     * 添加更多...
     */
}
