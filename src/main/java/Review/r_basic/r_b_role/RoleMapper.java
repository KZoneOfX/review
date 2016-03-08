package Review.r_basic.r_b_role;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */

public interface RoleMapper {
    /**
     * 通过用户id 查询用户 拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

}
