package Review.r_server.userInfo;

import Review.r_basic.r_b_user.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2969823238981963912L;
    //用户 公有属性 start
    private Long user_id;


    private String username;
    private String password;
    private String role_description;

    private String real_name;
    private String place;
    private String email;
    private String phone;

    private Date create_time;
    private String create_time_string;

    private Long create_person_id;

    private Date last_login_time;
    private String last_login_time_string;
    //公有属性 end

    //学生 私有属性 start
    private String stu_review_no;
    private Integer stu_paper_status;
    private String stu_paper_status_string;

    private Date stu_paper_submit_time_start;
    private String stu_paper_submit_time_start_string;

    private Date stu_paper_submit_time_over;
    private String stu_paper_submit_time_over_string;

    private Long stu_tch_id;
    private String stu_tch_name;
    /**
     * 学生管理员 id 必须与 学生所属 教学点 相对应
     */
    private Long stu_admin_id;
    //学生 私有属性 end

    //教师 私有属性 start
    private String tch_job_title;
    private String tch_department;
    private String tch_office_phone;
    private String tch_work_place;  //教师所属 教学点
    private Date tch_paper_review_time_start;
    private String tch_paper_review_time_start_string;
    private Date tch_paper_review_time_over;
    private String tch_paper_review_time_over_string;
    //教师 私有属性 end


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public String getrole_description() {
        return role_description;
    }

    public void setrole_description(String role_description) {
        this.role_description = role_description;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time_string() {
        return create_time_string;
    }

    public void setCreate_time_string(String create_time_string) {
        this.create_time_string = create_time_string;
    }

    public Long getCreate_person_id() {
        return create_person_id;
    }

    public void setCreate_person_id(Long create_person_id) {
        this.create_person_id = create_person_id;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_time_string() {
        return last_login_time_string;
    }

    public void setLast_login_time_string(String last_login_time_string) {
        this.last_login_time_string = last_login_time_string;
    }

    public String getStu_review_no() {
        return stu_review_no;
    }

    public void setStu_review_no(String stu_review_no) {
        this.stu_review_no = stu_review_no;
    }

    public Integer getStu_paper_status() {
        return stu_paper_status;
    }

    public void setStu_paper_status(Integer stu_paper_status) {
        this.stu_paper_status = stu_paper_status;
    }

    public String getStu_paper_status_string() {
        return stu_paper_status_string;
    }

    public void setStu_paper_status_string(String stu_paper_status_string) {
        this.stu_paper_status_string = stu_paper_status_string;
    }

    public Date getStu_paper_submit_time_start() {
        return stu_paper_submit_time_start;
    }

    public void setStu_paper_submit_time_start(Date stu_paper_submit_time_start) {
        this.stu_paper_submit_time_start = stu_paper_submit_time_start;
    }

    public String getStu_paper_submit_time_start_string() {
        return stu_paper_submit_time_start_string;
    }

    public void setStu_paper_submit_time_start_string(String stu_paper_submit_time_start_string) {
        this.stu_paper_submit_time_start_string = stu_paper_submit_time_start_string;
    }

    public Date getStu_paper_submit_time_over() {
        return stu_paper_submit_time_over;
    }

    public void setStu_paper_submit_time_over(Date stu_paper_submit_time_over) {
        this.stu_paper_submit_time_over = stu_paper_submit_time_over;
    }

    public String getStu_paper_submit_time_over_string() {
        return stu_paper_submit_time_over_string;
    }

    public void setStu_paper_submit_time_over_string(String stu_paper_submit_time_over_string) {
        this.stu_paper_submit_time_over_string = stu_paper_submit_time_over_string;
    }

    public Long getStu_tch_id() {
        return stu_tch_id;
    }

    public void setStu_tch_id(Long stu_tch_id) {
        this.stu_tch_id = stu_tch_id;
    }

    public String getStu_tch_name() {
        return stu_tch_name;
    }

    public void setStu_tch_name(String stu_tch_name) {
        this.stu_tch_name = stu_tch_name;
    }

    public Long getStu_admin_id() {
        return stu_admin_id;
    }

    public void setStu_admin_id(Long stu_admin_id) {
        this.stu_admin_id = stu_admin_id;
    }

    public String getTch_job_title() {
        return tch_job_title;
    }

    public void setTch_job_title(String tch_job_title) {
        this.tch_job_title = tch_job_title;
    }

    public String getTch_department() {
        return tch_department;
    }

    public void setTch_department(String tch_department) {
        this.tch_department = tch_department;
    }

    public String getTch_office_phone() {
        return tch_office_phone;
    }

    public void setTch_office_phone(String tch_office_phone) {
        this.tch_office_phone = tch_office_phone;
    }

    public String getTch_work_place() {
        return tch_work_place;
    }

    public void setTch_work_place(String tch_work_place) {
        this.tch_work_place = tch_work_place;
    }

    public Date getTch_paper_review_time_start() {
        return tch_paper_review_time_start;
    }

    public void setTch_paper_review_time_start(Date tch_paper_review_time_start) {
        this.tch_paper_review_time_start = tch_paper_review_time_start;
    }

    public String getTch_paper_review_time_start_string() {
        return tch_paper_review_time_start_string;
    }

    public void setTch_paper_review_time_start_string(String tch_paper_review_time_start_string) {
        this.tch_paper_review_time_start_string = tch_paper_review_time_start_string;
    }

    public Date getTch_paper_review_time_over() {
        return tch_paper_review_time_over;
    }

    public void setTch_paper_review_time_over(Date tch_paper_review_time_over) {
        this.tch_paper_review_time_over = tch_paper_review_time_over;
    }

    public String getTch_paper_review_time_over_string() {
        return tch_paper_review_time_over_string;
    }

    public void setTch_paper_review_time_over_string(String tch_paper_review_time_over_string) {
        this.tch_paper_review_time_over_string = tch_paper_review_time_over_string;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_description'" + role_description + '\'' +
                ", real_name='" + real_name + '\'' +
                ", place='" + place + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", create_time_string='" + create_time_string + '\'' +
                ", create_person_id=" + create_person_id +
                ", last_login_time=" + last_login_time +
                ", last_login_time_string='" + last_login_time_string + '\'' +
                ", stu_review_no='" + stu_review_no + '\'' +
                ", stu_paper_status=" + stu_paper_status +
                ", stu_paper_status_string='" + stu_paper_status_string + '\'' +
                ", stu_paper_submit_time_start=" + stu_paper_submit_time_start +
                ", stu_paper_submit_time_start_string='" + stu_paper_submit_time_start_string + '\'' +
                ", stu_paper_submit_time_over=" + stu_paper_submit_time_over +
                ", stu_paper_submit_time_over_string='" + stu_paper_submit_time_over_string + '\'' +
                ", stu_tch_id=" + stu_tch_id +
                ", stu_admin_id=" + stu_admin_id +
                ", tch_job_title='" + tch_job_title + '\'' +
                ", tch_department='" + tch_department + '\'' +
                ", tch_office_phone='" + tch_office_phone + '\'' +
                ", tch_work_place='" + tch_work_place + '\'' +
                ", tch_paper_review_time_start=" + tch_paper_review_time_start +
                ", tch_paper_review_time_start_string='" + tch_paper_review_time_start_string + '\'' +
                ", tch_paper_review_time_over=" + tch_paper_review_time_over +
                ", tch_paper_review_time_over_string='" + tch_paper_review_time_over_string + '\'' +
                '}';
    }

    public String toAdminString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_description'" + role_description + '\'' +
                ", real_name='" + real_name + '\'' +
                ", place='" + place + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", create_time_string='" + create_time_string + '\'' +
                ", create_person_id=" + create_person_id +
                ", last_login_time=" + last_login_time +
                ", last_login_time_string='" + last_login_time_string + '\'' +
                '}';
    }

    public String toStuString() {
        return "UserInfo{" +
                "stu_admin_id=" + stu_admin_id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_description'" + role_description + '\'' +
                ", real_name='" + real_name + '\'' +
                ", place='" + place + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", create_time_string='" + create_time_string + '\'' +
                ", create_person_id=" + create_person_id +
                ", last_login_time=" + last_login_time +
                ", last_login_time_string='" + last_login_time_string + '\'' +
                ", stu_review_no='" + stu_review_no + '\'' +
                ", stu_paper_status=" + stu_paper_status +
                ", stu_paper_status_string='" + stu_paper_status_string + '\'' +
                ", stu_paper_submit_time_start=" + stu_paper_submit_time_start +
                ", stu_paper_submit_time_start_string='" + stu_paper_submit_time_start_string + '\'' +
                ", stu_paper_submit_time_over=" + stu_paper_submit_time_over +
                ", stu_paper_submit_time_over_string='" + stu_paper_submit_time_over_string + '\'' +
                ", stu_tch_id=" + stu_tch_id +
                ", stu_tch_name=\'" + stu_tch_name + "\'" +
                '}';
    }

    public String toTchString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_description'" + role_description + '\'' +
                ", real_name='" + real_name + '\'' +
                ", place='" + place + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", create_time_string='" + create_time_string + '\'' +
                ", create_person_id=" + create_person_id +
                ", last_login_time=" + last_login_time +
                ", last_login_time_string='" + last_login_time_string + '\'' +
                ", tch_job_title='" + tch_job_title + '\'' +
                ", tch_department='" + tch_department + '\'' +
                ", tch_office_phone='" + tch_office_phone + '\'' +
                ", tch_paper_review_time_start_string='" + tch_paper_review_time_start_string + '\'' +
                ", tch_paper_review_time_over_string='" + tch_paper_review_time_over_string + '\'' +
                ", tch_paper_review_time_over=" + tch_paper_review_time_over +
                ", tch_paper_review_time_start=" + tch_paper_review_time_start +
                ", tch_work_place='" + tch_work_place + '\'' +
                '}';
    }
}
