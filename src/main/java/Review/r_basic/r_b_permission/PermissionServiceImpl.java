package Review.r_basic.r_b_permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionServiceImpl implements PermissionService{

    private PermissionMapper permissionMapper;

    public PermissionMapper getPermissionMapper() {
        return permissionMapper;
    }

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }
}
