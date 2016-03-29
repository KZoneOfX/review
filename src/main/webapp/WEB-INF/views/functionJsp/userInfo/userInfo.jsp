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
            用户信息
            <div class="form">
                <form action="" method="post" class="niceform">

                    <fieldset>
                        <dl>
                            <dt><label for="real_name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                            </dt>
                            <dd><input type="text" name="real_name" id="real_name" size="54"
                                       value="${userInfo.real_name}" disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="username">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
                            </dt>
                            <dd><input type="text" name="username" id="username" size="54" value="${userInfo.user_id }"
                                       disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="place">所属教学点:</label></dt>
                            <dd><input type="text" name="place" id="place" size="54" value="${userInfo.place } "
                                       disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="phone">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
                            </dt>
                            <dd><input type="text" name="phone" id="phone" size="54"
                                       value=${userInfo.phone } disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
                            </dt>
                            <dd><input type="text" name="email" id="email" size="54"
                                       value=${userInfo.email } disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="tch_job_title">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</label>
                            </dt>
                            <dd><input type="text" name="tch_job_title" id="tch_job_title" size="54"
                                       value="${userInfo.tch_job_title }" disabled="disabled"/></dd>
                        </dl>

                        <dl>
                            <dt><label for="tch_department">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:</label>
                            </dt>
                            <dd><input type="text" name="tch_department" id="tch_department" size="54"
                                       value="${userInfo.tch_department }" disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="tch_department">身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:</label>
                            </dt>
                            <dd><input type="text" name="tch_department" id="tch_department" size="54"
                                       value="${userInfo.tch_department }" disabled="disabled"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="tch_office_phone">办&nbsp;&nbsp;公&nbsp;&nbsp;电&nbsp;&nbsp;话:</label></dt>
                            <dd><input type="text" name="tch_office_phone" id="tch_office_phone" size="54"
                                       value="${userInfo.tch_office_phone }" disabled="disabled"/></dd>
                        </dl>
                    </fieldset>
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
