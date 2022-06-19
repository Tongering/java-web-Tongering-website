<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/4/28
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>HTML</title>
    <link rel="shortcut icon" href="/static/img/tongeringlogo.png">
    <link rel="stylesheet" href="/utils/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <script src="/utils/jquery-3.6.0.min.js"></script>
    <script src="/utils/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/css/photoborder.css">
    <link rel="stylesheet" href="/static/css/roky.css">
    <script src="/static/js/showtherocky.js"></script>
    <link rel="stylesheet" href="/static/css/setbackground.css">
</head>
<body>
<%@include file="/component/navbar.jsp"%>
<%@include file="/component/htmlbase.jsp"%>
<%--<%@include file="/component/bottom.jsp"%>--%>
<div class="rock fly" onclick="showrocky()">

</div>
</body>
</html>
