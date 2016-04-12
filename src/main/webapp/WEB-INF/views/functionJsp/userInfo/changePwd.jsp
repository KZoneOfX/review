<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 4/12/2016
  Time: 10:19 AM
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

        function infoAlert(info) {
            Lobibox.alert('error', {
                msg: info
            });
        }
        $(document).ready(function () {
            $("#changePwdBtn").bind("click", function () {
                var old_password = $("#old_password").val();
                if (old_password.length > 0) {
                    var new_password = $("#new_password").val();
                    var confirm_password = $("#confirm_password").val();
                    var changePwd_username = $("#changePwd_username").val();
                    var changePwd_password = $("#changePwd_password").val();
                    var old_md5_pwd = $.md5(changePwd_username + old_password);
                    if (old_md5_pwd == changePwd_password) { //校验原密码是否正确
                        if (new_password.length != 0 && new_password == confirm_password) {  //校验新密码是否相同
                            var password = $.md5(changePwd_username + confirm_password);
                            //更新密码
                            $.ajax({
                                type: "POST",
                                url: 'rest/user/changePwd',
                                data: {
                                    "password": password
                                },
                                success: function (data) {
                                    console.log(data);
                                    var msg = eval("(" + data + ")");
                                    if (msg.result == "1") {
                                        Lobibox.alert('success', {
                                            msg: "密码更改成功！请重新登录！"
                                        });
                                        alert("密码更改成功！请重新登录！")
                                        logout(); //更新密码成功，重新登陆
                                    } else {
                                        infoAlert("密码更改失败！");
                                        return;
                                    }
                                },
                                error: function () {
                                    infoAlert("密码更改失败！");
                                    return;
                                }
                            });
                        } else {
                            infoAlert("密码长度不得为空 且 新密码与确认密码必须相同");
                            return;
                        }
                    } else {
                        infoAlert("原密码错误！");
                        return;
                    }
                } else {
                    infoAlert("原密码不得为空！");
                    return;
                }

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
            <h2>密码修改</h2>
            <div align="left" style="width:900px; margin:0 auto">

                <div class="form">
                    <form method="post" class="niceform">
                        <fieldset>
                            <dl>
                                <dt><label for="old_password">原密码:</label>
                                </dt>
                                <dd><input type="password" name="old_password" id="old_password" size="54"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="new_password">新密码:</label>
                                </dt>
                                <dd><input type="password" name="new_password" id="new_password" size="54"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="confirm_password">确认新密码：:</label>
                                </dt>
                                <dd><input type="password" name="confirm_password" id="confirm_password" size="54"/>
                                </dd>
                            </dl>
                            <dl class="submit">
                                <input type="button" name="changePwdBtn" id="changePwdBtn" value="更新"/>
                            </dl>

                        </fieldset>
                    </form>
                    <input type="hidden" id="changePwd_username" name="changePwd_username" value="${userInfo.username}">
                    <input type="hidden" id="changePwd_password" name="changePwd_password" value="${userInfo.password}">
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