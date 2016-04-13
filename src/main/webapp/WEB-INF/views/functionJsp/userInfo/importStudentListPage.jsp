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
    <%@ include file="../../baseJsp/baseJS.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#import_student_file_btn").bind("click", function () {
                $.ajaxFileUpload({
                    //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
                    type: 'POST',
                    url: 'rest/user/importStudentDataExcel',
                    secureuri: false,                       //是否启用安全提交,默认为false
                    fileElementId: 'import_student_file',           //文件选择框的id属性
                    dataType: 'text',                       //服务器返回的格式,可以是json或xml等
                    success: function (data, status) {        //服务器响应成功时的处理函数
                        var msg = eval("(" + data.match(/;">([\s\S]*?)<\/pre>/)[1] + ")");
                        if (msg.result == "1") {
                            console.log(msg.errorUserInfoList);
                            var errorUserInfoList = msg.errorUserInfoList;

                            console.log(errorUserInfoList[1]);
                            console.log(errorUserInfoList.length);
                            if (errorUserInfoList.length == 0) {
                                Lobibox.alert('success', {
                                    msg: msg.tip
                                });
                            } else {
                                var str = "<tr><td>学号</td><td>姓名</td></tr>";
                                for (var i = 0; i < errorUserInfoList.length; i++) {
                                    str += "<tr><td>" + errorUserInfoList[i].username + "</td>" +
                                            "<td>" + errorUserInfoList[i].real_name + "</td><tr>";
                                }
                                Lobibox.window({
                                    title: '下列用户已存在，请确认后重新提交',
                                    content: [
                                        '<table  style="border: 10px;border-color: #0c0c0c">',
                                        str,
                                        '</table>'
                                    ].join("")
                                });
                            }
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
            <h3>学生信息导入</h3><br/>

            <div class="form">
                <form id="paper_submit_form" name="paper_submit_form" class="niceform">
                    <fieldset>

                        <dl>
                            <dt><label for="import_student_file">选择文件:</label></dt>
                            <dd><input type="file" name="import_student_file" id="import_student_file"/></dd>
                        </dl>
                    </fieldset>

                    <a href="rest/user/downloadStudentModelExcel" class="bt_red">
                        <span class="bt_red_lft"></span>
                        <strong>下载学生样例excel</strong>
                        <span class="bt_red_r"></span>
                    </a>
                    <a id="import_student_file_btn" class="bt_blue">
                        <span class="bt_blue_lft"></span>
                        <strong>提交</strong>
                        <span class="bt_blue_r"></span>
                    </a>
                    <%--<a href="" ><button value="" >下载学生样例excel</button></a>--%>
                    <%--<a href="" ><button value="" style="background:#3cafdb;color:#fff;">下载老师样例excel</button></a>--%>

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
