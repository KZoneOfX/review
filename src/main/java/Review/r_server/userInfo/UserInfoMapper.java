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
public interface UserInfoMapper {
    //    <tx:method name="insert*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
//    <tx:method name="update*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
//    <tx:method name="delete*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
//    <!-- select,count开头的方法,开启只读,提高数据库访问性能 -->
//    <tx:method name="select*" read-only="true"/>
//    <tx:method name="count*" read-only="true"/>
    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(UserInfo userInfo);

    UserInfo selectUserInfoByUserId(Long user_id);

    int countUserInfos(UserInfo userInfo);

    List<UserInfo> selectUserInfos(UserInfo userInfo);

    List<UserInfo> selectUserInfosByPage(Map<String, Object> map);


}
