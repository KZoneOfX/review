package Review.r_basic.r_b_permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionMapper {

    /**
     * 通过角色id 查询角色 拥有的权限
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);


    /**
     * 获取全部权限
     *
     * @return
     */
    List<Permission> selectAllPermissions();
}
