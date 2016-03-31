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
    <script type="text/javascript" language="javascript" src="appResource/js/ajaxFileUpdate.js"></script>
</head>
<body>

<script type="text/javascript">
    function showMsg(msg) {

    }

    <%--$(document).ready(function () {--%>
    <%--var msg = eval("("+${userInfo.real_name }+")");--%>
    <%--});--%>
    function ajaxFileUpload() {
        //开始上传文件时显示一个图片,文件上传完成将图片隐藏
        var submit_paper_name = document.getElementById("submit_paper_name").value;
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            type: 'POST',
            url: 'rest/paperInfo/submit',
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: 'submit_paper_file',           //文件选择框的id属性
            data: {
                submit_paper_name: submit_paper_name
            },
            dataType: 'text',                       //服务器返回的格式,可以是json或xml等
            success: function (data, status) {        //服务器响应成功时的处理函数
                var msg = eval("(" + data.match(/;">([\s\S]*?)<\/pre>/)[1] + ")");
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
    }
    function ajaxUpload() {
        $("#paper_submit_form").submit();
    }

</script>
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
                <form id="paper_submit_form" name="paper_submit_form" class="niceform">
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
                            <dd><input type="button" onclick="ajaxFileUpload()" name="submit" id="submit" value="提交"/>
                            </dd>
                        </dl>
                    </fieldset>
                </form>
                <%--<sf:form enctype="multipart/form-data" method="post" class="niceform">--%>

                <%--<fieldset>--%>

                <%--<dl>--%>
                <%--<dt>--%>
                <%--<label for="submit_paper_std_username">学生学号:</label>--%>
                <%--</dt>--%>
                <%--<dd>--%>
                <%--<input type="text" name="submit_paper_std_username" id="submit_paper_std_username"--%>
                <%--size="54"--%>
                <%--value=""/>--%>
                <%--</dd>--%>
                <%--</dl>--%>
                <%--<dl>--%>
                <%--<dt>--%>
                <%--<label for="submit_paper_std_realName">学生姓名:</label>--%>
                <%--</dt>--%>
                <%--<dd>--%>
                <%--<input type="text" name="submit_paper_std_realName" id="submit_paper_std_realName"--%>
                <%--size="54"--%>
                <%--value=""/>--%>
                <%--</dd>--%>
                <%--</dl>--%>


                <%--<dl>--%>
                <%--<dt><label for="submit_paper_name">论文名称:</label></dt>--%>
                <%--<dd><input type="text" name="submit_paper_name" id="submit_paper_name" size="54"/></dd>--%>
                <%--</dl>--%>
                <%--<dl>--%>
                <%--<dt><label for="submit_paper_file">选择文件:</label></dt>--%>
                <%--<dd><input type="file" id="submit_paper_file" name="submit_paper_file"/></dd>--%>
                <%--</dl>--%>
                <%--<dl class="submit">--%>
                <%--<dd><input type="submit" name="submit" id="submit" value="提交"/></dd>--%>
                <%--</dl>--%>
                <%--</fieldset>--%>
                <%--</sf:form>--%>
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
