package Review.r_basic.r_b_user;

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
}
