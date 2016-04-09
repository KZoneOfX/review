package Review.r_basic.r_b_user;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.、
 *
 *
 *
 *
 *
 *
 *
 *
 */
public interface UserMapper {
    /**
     * 用户验证
     *
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);


    User selectById(Long id);


    int insertUser(User user);

    int insertUserRole(Map<String, Long> map);
}
