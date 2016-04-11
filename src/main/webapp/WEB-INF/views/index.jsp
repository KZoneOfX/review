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
    <%@ include file="baseJsp/baseCss.jsp" %>
    <%@ include file="baseJsp/baesJs.jsp" %>
</head>
<body>
<div class="header">
    <%@ include file="baseJsp/baseHeader.jsp" %>
</div>
<div class="main_content">
    <div class="center_content">
        <div class="left_content">
            <%@ include file="baseJsp/baseMenu.jsp" %>
        </div>
        <!--end of left content -->

        <div class="right_content">
            <div align="left" style="width:900px; margin:0 auto">
                <shiro:hasRole name="student">
                    <h2 style="color:#56c1ec;">论文状态显示</h2>
                    <table id="rounded-corner" style="width:930px;"
                           summary="2007 Major IT Companies' Profit">
                        <thead>
                        <tr>
                            <th scope="col" class="rounded" style="color:#fff;">论文名称</th>
                            <th scope="col" class="rounded" style="color:#fff;">论文状态</th>
                            <th scope="col" class="rounded" style="color:#fff;">上传时间</th>
                            <th scope="col" class="rounded-q4" style="color:#fff;">下载</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:if test="${userInfo.stu_paper_status==1}">
                            <tr>
                                <td>${paperInfo.paper_name }</td>
                                <td>已提交</td>
                                <td>${paperInfo.paper_create_time }</td>
                                <td><a href="rest/paperInfo/download"><img
                                        src="appResource/images/download.png" align="center" valign="middle"/></a></td>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${userInfo.stu_paper_status!=1}">
                            <tr>
                                <td></td>
                                <td>
                                    未提交
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

                </shiro:hasRole>
            </div>
        </div>
        <!-- end of right content-->

    </div>
    <!--end of center content -->

</div>
<!--end of main content-->

<div class="footer">
    <%@ include file="baseJsp/baseFooter.jsp" %>
</div>
</body>
</html>