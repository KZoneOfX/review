package Review.r_basic.r_b_permission;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Permission implements Serializable{
    private static final long serialVersionUID = 8929915949700749832L;

    private Long permission_id;

    private String permissionName;

    private String permissionSign;

    private String description;

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionSign() {
        return permissionSign;
    }

    public void setPermissionSign(String permissionSign) {
        this.permissionSign = permissionSign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission_id=" + permission_id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionSign='" + permissionSign + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
