package Review.r_server.paperInfo;

import java.io.Serializable;

public class PaperInfo implements Serializable {


    private static final long serialVersionUID = -4786552799453703363L;
    private String id;
    private String paper_name;
    private String paper_path;
    private String paper_comment_path;    //预留字段  用不到
    private String paper_result;// 最后综合总评价 给学生看的
    private String paper_score;            //预留字段  用不到
    private String paper_create_time;
    private String paper_submit_person;
    private Long user_id;
    private int state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getPaper_path() {
        return paper_path;
    }

    public void setPaper_path(String paper_path) {
        this.paper_path = paper_path;
    }

    public String getPaper_comment_path() {
        return paper_comment_path;
    }

    public void setPaper_comment_path(String paper_comment_path) {
        this.paper_comment_path = paper_comment_path;
    }

    public String getPaper_result() {
        return paper_result;
    }

    public void setPaper_result(String paper_result) {
        this.paper_result = paper_result;
    }

    public String getPaper_score() {
        return paper_score;
    }

    public void setPaper_score(String paper_score) {
        this.paper_score = paper_score;
    }

    public String getPaper_create_time() {
        return paper_create_time;
    }

    public void setPaper_create_time(String paper_create_time) {
        this.paper_create_time = paper_create_time;
    }

    public String getPaper_submit_person() {
        return paper_submit_person;
    }

    public void setPaper_submit_person(String paper_submit_person) {
        this.paper_submit_person = paper_submit_person;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PaperInfo{" +
                "id='" + id + '\'' +
                ", paper_name='" + paper_name + '\'' +
                ", paper_path='" + paper_path + '\'' +
                ", paper_comment_path='" + paper_comment_path + '\'' +
                ", paper_result='" + paper_result + '\'' +
                ", paper_score='" + paper_score + '\'' +
                ", paper_create_time='" + paper_create_time + '\'' +
                ", paper_submit_person='" + paper_submit_person + '\'' +
                ", user_id=" + user_id +
                ", state=" + state +
                '}';
    }
}
