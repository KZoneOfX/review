package Review.r_basic.r_b_user;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/10/2016
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3019786822753756859L;

    private Long id;

    private String username;

    private String password;

    private int state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}
