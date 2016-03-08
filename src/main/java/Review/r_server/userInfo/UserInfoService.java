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
public interface UserInfoService {
    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(UserInfo userInfo);

    UserInfo selectUserInfoByUserId(Long user_id);

    int countUserInfos(UserInfo userInfo);

    List<UserInfo> selectUserInfos(UserInfo userInfo);

    List<UserInfo> selectUserInfosByPage(Map<String, Object> map);

}
