package Review.r_server.userInfo;


import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoMapper userInfoMapper;

    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    public int deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteUserInfo(userInfo);
    }

    public UserInfo selectUserInfoByUserId(Long user_id) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!  userid: " + user_id);
        return userInfoMapper.selectUserInfoByUserId(user_id);
    }

    public int countUserInfos(UserInfo userInfo) {
        return userInfoMapper.countUserInfos(userInfo);
    }

    public List<UserInfo> selectUserInfos(UserInfo userInfo) {
        return userInfoMapper.selectUserInfos(userInfo);
    }

    public List<UserInfo> selectUserInfosByPage(Map<String, Object> map) {
        return userInfoMapper.selectUserInfosByPage(map);
    }

    public UserInfoMapper getUserInfoMapper() {
        return userInfoMapper;
    }

    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }
}
