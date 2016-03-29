<%--
  Created by IntelliJ IDEA.
  User: Xiaoke Zhang
  Date: 2/29/2016
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="lunwen"
     style="color:#e289ea;float:left;font-size:30px;padding-top:20px;letter-spacing:1em;font-weight:700;">
    <a href="rest/index"><img src="appResource/images/article.png" style="width:100%;height:100%;"></a>
</div>
<div class="right_header">
    您好！ ${userInfo.real_name }| <a id="logout" class="logout">退出</a>
</div>
<div id="clock_a"></div>