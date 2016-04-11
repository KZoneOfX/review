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

<script type="text/javascript">
    function showMsg(msg) {

    }


    $(document).ready(function () {
        $("#submit_paper_btn").bind("click", function () {
            var submit_paper_stu_tch_name = document.getElementById("submit_paper_stu_tch_name").value;
            var submit_paper_stu_place = document.getElementById("submit_paper_stu_place").value;
            var info = "请确认以下信息!<br>指导教师：" + submit_paper_stu_tch_name + "  所属教学点：" + submit_paper_stu_place + "<br>";
            info += "如有错误，请联系学院教学办！ 电话：024-83680497";
            Lobibox.confirm({
                msg: info,
                callback: function ($this, type, ev) {
                    if (type === 'yes') {
                        Lobibox.notify('success', {
                            msg: '文件上传中！'
                        });
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
                                    window.location.href = "rest/index";
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
                    } else if (type === 'no') {
                        Lobibox.notify('info', {
                            msg: '取消上传！'
                        });
                    }
                }
            });


        });
    });

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
            <h2>论文提交</h2><br/>

            <div class="form">
                <form id="paper_submit_form" name="paper_submit_form" class="niceform">
                    <fieldset>

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


                        <dl>
                            <dt><label for="submit_paper_name">论文名称:</label></dt>
                            <dd><input type="text" name="submit_paper_name" id="submit_paper_name" size="54"/></dd>
                        </dl>
                        <dl>
                            <dt><label for="submit_paper_file">选择文件:</label></dt>
                            <dd><input type="file" id="submit_paper_file" name="submit_paper_file"/></dd>
                        </dl>
                        <dl class="submit">
                            <dd><input type="button" name="submit_paper_btn" id="submit_paper_btn" value="提交"/>
                            </dd>
                        </dl>
                    </fieldset>
                </form>
            </div>
        </div>
        <input id="submit_paper_stu_tch_name" type="hidden" value="${userInfo.stu_tch_name }"/>
        <input id="submit_paper_stu_place" type="hidden" value="${userInfo.place}"/>
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
