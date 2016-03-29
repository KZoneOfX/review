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

    private Long id;

    private Long pId;

    private String name;

    private int state;

    private int is_parent;

    private String permission_sign;

    private String description;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission_sign() {
        return permission_sign;
    }

    public void setPermission_sign(String permission_sign) {
        this.permission_sign = permission_sign;
    }

    public int getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(int is_parent) {
        this.is_parent = is_parent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", is_parent=" + is_parent +
                ", permission_sign='" + permission_sign + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
