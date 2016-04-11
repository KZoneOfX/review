<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/25/2016
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/25/2016
  Time: 11:04 AM
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
    <%@ include file="../../baseJsp/baesJs.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#pwd_reset_btn").bind("click", function () {
                var stu_name = $("#real_name").val();
                var username = $("#username").val();
                var info = "确定要重置 " + stu_name + " 的密码？";
                Lobibox.confirm({
                    msg: info,
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            $.ajax({
                                type: "POST",
                                url: 'rest/user/restPassword',
                                data: {
                                    "username": username
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
                                        msg: "重置失败！"
                                    });
                                }
                            });
                            Lobibox.notify('success', {
                                msg: '密码重置中！'
                            });
                        } else if (type === 'no') {
                            Lobibox.notify('info', {
                                msg: '取消重置！'
                            });
                        }
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
            <div class="form">
                <form method="post" class="niceform">

                    <c:if test="${ifCheckStudent ==0}">
                        <fieldset>
                            <dl>
                                <dt><label for="username">
                                    <shiro:hasRole name="student">
                                        学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:

                                    </shiro:hasRole>
                                    <shiro:hasRole name="developer">
                                        账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
                                    </shiro:hasRole>
                                </label>
                                </dt>
                                <dd><input type="text" name="username" id="username" size="54"
                                           value="${userInfo.username }"
                                           disabled="disabled"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="real_name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                </dt>
                                <dd><input type="text" name="real_name" id="real_name" size="54"
                                           value="${userInfo.real_name}" disabled="disabled"/></dd>
                            </dl>
                            <shiro:hasRole name="student">
                                <dl>
                                    <dt><label for="stu_tch_name">指&nbsp;导&nbsp;教&nbsp;师&nbsp;&nbsp;:</label></dt>
                                    <dd><input type="text" name="stu_tch_name" id="stu_tch_name" size="54"
                                               value="${userInfo.stu_tch_name }"
                                               disabled="disabled"
                                    /></dd>
                                </dl>

                            </shiro:hasRole>
                            <dl>
                                <dt><label for="place">所属教学点&nbsp;:</label></dt>
                                <dd><input type="text" name="place" id="place" size="54" value="${userInfo.place }"
                                           disabled="disabled"
                                /></dd>
                            </dl>
                            <dl>
                                <dt><label
                                        for="phone">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
                                </dt>
                                <dd><input type="text" name="phone" id="phone" size="54"
                                           value="${userInfo.phone }"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
                                </dt>
                                <dd><input type="text" name="email" id="email" size="54"
                                           value="${userInfo.email }"/></dd>
                            </dl>
                            <dl class="submit">
                                <input type="submit" name="submit" id="submit" value="更新"/>
                            </dl>
                        </fieldset>

                    </c:if>

                    <c:if test="${ifCheckStudent ==1}">
                        <fieldset>
                            <dl>
                                <dt><label for="username">
                                    学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
                                </label>
                                </dt>
                                <dd><input type="text" name="username" id="username" size="54"
                                           value="${studentInfo.username }"
                                           disabled/></dd>
                            </dl>
                            <dl>
                                <dt><label for="real_name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                </dt>
                                <dd><input type="text" name="real_name" id="real_name" size="54"
                                           value="${studentInfo.real_name}"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="stu_tch_name">指&nbsp;导&nbsp;教&nbsp;师&nbsp;&nbsp;:</label></dt>
                                <dd><input type="text" name="stu_tch_name" id="stu_tch_name" size="54"
                                           value="${studentInfo.stu_tch_name }"

                                /></dd>
                            </dl>
                            <dl>
                                <dt><label for="place">所属教学点&nbsp;:</label></dt>
                                <dd><input type="text" name="place" id="place" size="54" value="${studentInfo.place }"

                                /></dd>
                            </dl>
                            <dl>
                                <dt><label
                                        for="phone">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
                                </dt>
                                <dd><input type="text" name="phone" id="phone" size="54"
                                           value="${studentInfo.phone }"/></dd>
                            </dl>
                            <dl>
                                <dt><label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
                                </dt>
                                <dd><input type="text" name="email" id="email" size="54"
                                           value="${studentInfo.email }"/></dd>
                            </dl>
                            <dl class="submit">
                                <input type="submit" name="submit" id="submit" value="更新"/>
                                <input type="button" name="submit" id="pwd_reset_btn" value="密码重置"/>
                            </dl>
                        </fieldset>

                    </c:if>
                </form>
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
