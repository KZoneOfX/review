package Review.r_basic.r_b_user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int insertUserRole(Long user_id, Long role_id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("user_id", user_id);
        map.put("role_id", role_id);
        return userMapper.insertUserRole(map);
    }
}
