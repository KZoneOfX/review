<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 4/11/2016
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>东北大学软件学院论文系统</title>
    <%@ include file="../../baseJsp/baseCss.jsp" %>
    <%@ include file="../../baseJsp/baseJS.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#add_student_submit_btn").bind("click", function () {
                var username = $("#add_student_username").val();
                var password = $("#add_student_password").val();
                var real_name = $("#add_student_real_name").val();
                var teacher_name = $("#add_student_teacher_name").val();
                var place = $("#add_student_place").val();
                var tip = "";
                if (username.length == 0) {
                    tip = "学号 不得为空！";
                } else if (password.length == 0) {
                    tip = "密码 不得为空！";
                } else if (real_name.length == 0) {
                    tip = "学生姓名 不得为空！";
                } else if (teacher_name.length == 0) {
                    tip = "指导教师姓名 不得为空！";
                } else if (place.length == 0) {
                    tip = "所属教学点 不得为空！";
                }
                if (tip.length > 0) {
                    Lobibox.alert('error', {
                        msg: tip
                    });
                    return;
                }

                var md5_password = $.md5(username + password);
                $.ajax({
                    type: "POST",
                    url: 'rest/user/addStudent',
                    data: {
                        "username": username,
                        "password": md5_password,
                        "real_name": real_name,
                        "stu_tch_name": teacher_name,
                        "place": place
                    },
                    success: function (data, status) {        //服务器响应成功时的处理函数
                        console.log(data);
                        var msg = eval("(" + data + ")");
                        if (msg.result == "1") {
                            Lobibox.alert('success', {
                                msg: msg.tip
                            });
                        } else {
                            Lobibox.alert('error', {
                                msg: msg.tip
                            });

                        }
                    },
                    error: function (data, status, e) { //服务器响应失败时的处理函数
                        Lobibox.alert('error', {
                            msg: "上传失败"
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="header">
    <%@ include file="../../baseJsp/baseHeader.jsp" %>
</div>
<div class="main_content">
    <div class="center_content">
        <div class="left_content">
            <%@ include file="../../baseJsp/baseMenu.jsp" %>
        </div>
        <!--end of left content -->

        <div class="right_content">
            <div align="left" style="width:900px; margin:0 auto">

                <div class="form">
                    <form class="niceform">
                        <fieldset>
                            <dl>
                                <dt>
                                    <label for="add_student_username">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                                </dt>
                                <dd>
                                    <input type="text" name="add_student_username" id="add_student_username" size="54"/>
                                </dd>
                                <dt>
                                    <label for="add_student_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                                </dt>
                                <dd>
                                    <input type="text" name="add_student_password" id="add_student_password" size="54"/>
                                </dd>
                                <dt>
                                    <label for="add_student_real_name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                                </dt>
                                <dd>
                                    <input type="text" name="add_student_real_name" id="add_student_real_name"
                                           size="54"/>
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    <label for="add_student_teacher_name">指&nbsp;&nbsp;&nbsp;&nbsp;导&nbsp;&nbsp;&nbsp;&nbsp;教&nbsp;&nbsp;&nbsp;&nbsp;师&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                                </dt>
                                <dd>
                                    <input type="text" name="add_student_teacher_name" id="add_student_teacher_name"
                                           size="54"/>
                                </dd>
                            </dl>
                            <dt>
                                <label for="add_student_place">所&nbsp;&nbsp;属&nbsp;&nbsp;教&nbsp;&nbsp;学&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                            </dt>
                            <dd>
                                <input type="text" name="add_student_place" id="add_student_place" size="54"/>
                            </dd>
                            <dl class="submit">
                                <input type="button" name="submit" id="add_student_submit_btn" value="提交"/>
                            </dl>
                        </fieldset>
                    </form>
                </div>

            </div>
        </div>
        <!-- end of right content-->

    </div>
    <!--end of center content -->

</div>
<!--end of main content-->

<div class="footer">
    <%@ include file="../../baseJsp/baseFooter.jsp" %>
</div>
</body>
</html>
