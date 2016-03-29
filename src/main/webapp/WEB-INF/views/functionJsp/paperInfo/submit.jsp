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
            <h2>系统故障，请联系学院教学办，解决相应问题</h2> <br/> <br/> <br/> <br/>

            <div class="form">
                <sf:form enctype="multipart/form-data" method="post" class="niceform">

                    <fieldset>

                        <dl>
                            <dt>
                                <label for="submit_paper_std_username">学生学号:</label>
                            </dt>
                            <dd>
                                <input type="text" name="submit_paper_std_username" id="submit_paper_std_username"
                                       size="54"
                                       value=""/>
                            </dd>
                        </dl>
                        <dl>
                            <dt>
                                <label for="submit_paper_std_realName">学生姓名:</label>
                            </dt>
                            <dd>
                                <input type="text" name="submit_paper_std_realName" id="submit_paper_std_realName"
                                       size="54"
                                       value=""/>
                            </dd>
                        </dl>


                        <dl>
                            <dt><label for="submit_paper_name">论文名称:</label></dt>
                            <dd><input type="text" name="submit_paper_name" id="submit_paper_name" size="54"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="submit_paper_file">选择文件:</label></dt>
                            <dd><input type="file" id="submit_paper_file" name="submit_paper_file"/></dd>
                        </dl>
                        <dl class="submit">
                            <dd><input type="submit" name="submit" id="submit" value="提交"/></dd>
                        </dl>
                    </fieldset>
                </sf:form>
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
