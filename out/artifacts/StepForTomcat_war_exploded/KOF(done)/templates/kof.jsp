<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/16
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KOF</title>
    <link rel="stylesheet" href="/utils/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <script src="/utils/jquery-3.6.0.min.js"></script>
    <script src="/utils/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../static/css/base.css">
    <script src="../static/js/jquery-3.6.0.min.js"></script>
    <script src="/static/js/refuserpho.js"></script>
    <script src="/static/js/kofready.js"></script>
</head>
<body>
<%@include file="/component/navbar.jsp"%>
<div id="kof" tabindex="0" hidefocus="true">

</div>
<div style="height: 700px">

</div>

<script type="module">
    import {KOF} from '../static/js/base.js'

    let kof = new KOF('kof');  //传递body里的div标签为kof的
</script>
<%@include file="/component/bottom.jsp"%>
</body>
</html>
