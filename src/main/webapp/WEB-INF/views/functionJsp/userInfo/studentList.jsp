<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 4/10/2016
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
            $('#dataTables-example').DataTable({
                "processing": true,
                "scrollY": 250,
                "columns": [
                    {
                        "data": "checkbox",

                        orderable: false,
                        "sWidth": "20px"
                    },

                    {
                        "data": "no",
                        "sWidth": "30px"
                    },

                    {
                        "data": "username",
                        "sWidth": "50px"
                    },

                    {
                        "data": "real_name",
                        "sWidth": "50px"
                    },

                    {
                        "data": "place",
                        "sWidth": "50px"
                    },

                    {
                        "data": "stu_tch_name",
                        "sWidth": "50px"
                    },

                    {"data": "stu_paper_name"},

                    {
                        "data": "stu_paper_status",
                        "sWidth": "50px"
                    },

                    {
                        "data": "stu_edit",
                        "sWidth": "30px",
                        orderable: false
                    }
                ],
                "oLanguage": {                          //汉化
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前页",
                        "sNext": "后页",
                        "sLast": "尾页"
                    }
                }

            });

            $("#studentList_export_students_btn").bind("click", function () {
                var info = "确定要导出学生信息excel？<br>";
                Lobibox.confirm({
                    msg: info,
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            var form = $("<form>");//定义一个form表单
                            form.attr("style", "display:none");
                            form.attr("target", "");
                            form.attr("method", "post");
                            form.attr("action", "rest/user/export_students");
                            $("body").append(form);//将表单放置在web中
                            form.submit();//表单提交

                            Lobibox.notify('success', {
                                msg: '文件整理打包中~！'
                            });
                        } else if (type === 'no') {
                            Lobibox.notify('info', {
                                msg: '取消下载！'
                            });
                        }
                    }
                });
            });

            $("#studentList_import_btn").bind("click", function () {
                window.location.href = 'rest/user/importStudentListPage';
            });

            $("#studentList_add_student_btn").bind("click", function () {
                window.location.href = 'rest/user/addStudent';
            });

            $("#studentList_download_papers_btn").bind("click", function () {
                var select = $('input[name="selected"]:checked');
                var selectAjax = select.serialize();
                if (select.length == 0) {
                    Lobibox.alert('error', {
                        msg: "请选择要下载的论文！"
                    });
                    return;
                }
                var info = "确定要下载 以下学号学生的论文？<br>";
                for (var i = 0; i < select.length; i++) {
                    if (select[i].checked) {
                        info += select[i].value + " ";
                    }
                }
                Lobibox.confirm({
                    msg: info,
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            var form = $("<form>");//定义一个form表单
                            form.attr("style", "display:none");
                            form.attr("target", "");
                            form.attr("method", "post");
                            form.attr("action", "rest/paperInfo/downLoadZip");
                            var input1 = $("<input>");
                            input1.attr("type", "hidden");
                            input1.attr("name", "select");
                            input1.attr("value", selectAjax);
                            $("body").append(form);//将表单放置在web中
                            form.append(input1);
                            form.submit();//表单提交

                            Lobibox.notify('success', {
                                msg: '文件整理打包中~！'
                            });
                        } else if (type === 'no') {
                            Lobibox.notify('info', {
                                msg: '取消下载！'
                            });
                        }
                    }
                });
            });
        });
        function selectAll() {
            var checklist = document.getElementsByName("selected");
            if (document.getElementById("controlAll").checked) {
                for (var i = 0; i < checklist.length; i++) {
                    if (!(checklist[i].value.substr(0, 1) == "_")) {

                        checklist[i].checked = 1;
                    }
                }
            } else {
                for (var j = 0; j < checklist.length; j++) {
                    checklist[j].checked = 0;
                }
            }
        }
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
            <div align="left" style="width:900px">
                <table id="dataTables-example" class="dataTables-example" style="width:900px;">
                    <thead>
                    <tr>
                        <th style="text-align:center;">
                            <input onclick="selectAll()" type="checkbox" name="controlAll" id="controlAll">
                        </th>
                        <th style="text-align:center;" scope="col" class="rounded">序号</th>
                        <th style="text-align:center;" scope="col" class="rounded">学号</th>
                        <th style="text-align:center;" scope="col" class="rounded">姓名</th>
                        <th style="text-align:center;" scope="col" class="rounded">教学点</th>
                        <th style="text-align:center;" scope="col" class="rounded">指导教师</th>
                        <th style="text-align:center;" scope="col" class="rounded">论文名称</th>
                        <th style="text-align:center;" scope="col" class="rounded">论文状态</th>
                        <th style="text-align:center;" scope="col" class="rounded">查看</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% int i = 1; %>
                    <c:forEach items="${studentList }" var="student">
                        <tr>
                            <td align="center"><input type="checkbox" id="selected" name="selected"
                                    <c:if test="${student.stu_paper_status!=1}">
                                        disabled value="_${student.username}"
                                    </c:if>
                                    <c:if test="${student.stu_paper_status==1}">
                                        value="${student.username}"
                                    </c:if>
                            /></td>
                            <td align="center" valign="middle"><%out.print(i++); %></td>
                            <td align="center" valign="middle">${student.username }</td>
                            <td align="center" valign="middle">${student.real_name }</td>
                            <td align="center" valign="middle">${student.place }</td>
                            <td align="center" valign="middle">${student.stu_tch_name }</td>
                            <td align="center" valign="middle">${student.stu_paper_name }</td>
                            <td align="center" valign="middle">
                                <c:if test="${student.stu_paper_status==1}">
                                    <a href="rest/paperInfo/${student.username }/download"> <b
                                            style="color: red">已提交</b><img
                                            src="appResource/images/download_1.png"></a>
                                </c:if>
                                <c:if test="${student.stu_paper_status!=1}">
                                    <a href="rest/paperInfo/${student.username }/upload"> 未提交
                                        <img src="appResource/images/upload.png"></a>
                                </c:if>
                            </td>
                            <td align="center" valign="middle"><a href="rest/user/${student.username }/info">
                                <img src="appResource/images/user_edit.png"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <a id="studentList_export_students_btn" class="bt_green" >
                    <span class="bt_green_lft"></span>
                    <strong>导出学生数据</strong>
                    <span class="bt_green_r"></span>
                </a>
                <a id="studentList_import_btn" class="bt_red">
                    <span class="bt_red_lft"></span>
                    <strong>批量导入学生</strong>
                    <span class="bt_red_r"></span>
                </a>
                <a id="studentList_add_student_btn" class="bt_blue">
                    <span class="bt_blue_lft"></span>
                    <strong>添加学生</strong>
                    <span class="bt_blue_r"></span>
                </a>
                <a id="studentList_download_papers_btn" class="bt_green" style="position: absolute;">
                    <span class="bt_green_lft"></span>
                    <strong>批量下载论文</strong>
                    <span class="bt_green_r"></span>
                </a>
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
