<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminuser</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员界面</title>
    <link rel="stylesheet" href="/utils/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <script src="/utils/jquery-3.6.0.min.js"></script>
    <script src="/utils/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script src="/static/js/quituser.js"></script>
    <script src="/static/js/refuserpho.js"></script>
    <script src="/static/js/adminready.js"></script>
    <link rel="stylesheet" href="/static/css/roky.css">
    <script src="/static/js/showtherocky.js"></script>
    <link rel="stylesheet" href="/static/css/setbackground.css">
</head>
<body>
<%@include file="/component/adminnavbar.jsp"%>
<%@include file="/component/admincommentmain.jsp"%>
<%@include file="/component/bottom.jsp"%>
<div class="rock fly" onclick="showrocky()">
</body>
</html>
