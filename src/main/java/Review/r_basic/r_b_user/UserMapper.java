package Review.r_basic.r_b_user;

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
// <!-- 对insert,update,delete 开头的方法进行事务管理,只要有异常就回滚 -->
// <tx:method name="insert*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
// <tx:method name="update*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
// <tx:method name="delete*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
// <!-- select,count开头的方法,开启只读,提高数据库访问性能 -->
// <tx:method name="select*" read-only="true"/>
// <tx:method name="count*" read-only="true"/>
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

}
