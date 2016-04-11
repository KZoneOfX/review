<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/29/2016
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<div class="sidebarmenu">
    <shiro:hasRole name="student">

        <a class="menuitem" href="rest/index">主页</a>
        <a class="menuitem" href="rest/paperInfo/submit">论文提交</a>
    </shiro:hasRole>

    <shiro:hasRole name="developer">
        <a class="menuitem" href="rest/user/studentList">主页</a>
        <%--<a class="menuitem submenuheader" href="">个人信息</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/user/check_info">查看信息</a></li>--%>
        <%--&lt;%&ndash;TODO 查看信息 包含修改信息&ndash;%&gt;--%>
        <%--<li><a href="rest/user/info_check">信息完善</a></li>--%>
        <%--<li><a href="rest/user/forget">忘记密码</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<a class="menuitem submenuheader" href="">数据列表</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/user/studentList">学生列表</a></li>--%>
        <%--<li><a href="rest/paperInfo/submit">论文列表</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<a class="menuitem submenuheader" href="">论文相关</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/paperInfo">论文列表</a></li>--%>
        <%--<li><a href="rest/paperInfo/submit">论文提交</a></li>--%>
        <%--<li><a href="rest/paper_review">论文评审</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<a class="menuitem  submenuheader" href="">数据管理</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/data_update">信息导入</a></li>--%>
        <%--<li><a href="rest/data_download">信息导出</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<a class="menuitem  menuitem_red submenuheader" href="">权限管理</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/paper_submit_set">提交控制</a></li>--%>
        <%--<li><a href="rest/paper_teacher_set">论文分配</a></li>--%>
        <%--<li><a href="rest/email_set">邮件管理</a></li>--%>
        <%--<li><a href="rest/system_set">系统管理</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<a class="menuitem  menuitem_red submenuheader" href="">系统管理</a>--%>
        <%--<div class="submenu">--%>
        <%--<ul>--%>
        <%--<li><a href="rest/paper_submit_set">用户管理</a></li>--%>
        <%--<li><a href="rest/paper_teacher_set">角色管理</a></li>--%>
        <%--<li><a href="rest/permission">权限管理</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>

    </shiro:hasRole>

</div>
