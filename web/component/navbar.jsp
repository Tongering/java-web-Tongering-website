<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>navbar</title>
    <script src="/static/js/quituser.js"></script>
    <script src="/static/js/refuserpho.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img src="/static/img/tongeringlogo.png" width="50px">
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li ><a href="/about">首页 about</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="true">代码分类 code part <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/html">Html</a></li>
                        <li><a href="/css">Css</a></li>
                        <li><a href="/js">Js</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/servlet">Servlet</a></li>
                        <li><a href="/cookies">Cookies</a></li>
                        <li><a href="/java">Java</a></li>
                        <li><a href="/jdbc">Jdbc</a></li>
                        <li><a href="/jstl">Jstl</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/vue">Vue</a></li>
                        <li><a href="/react">react</a></li>
                        <li><a href="/ajax">ajax</a></li>
                    </ul>
<%--                <li><a href="/plan">计划组 Plan</a></li>--%>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="true">工具 tool <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/calculator">计算器</a></li>
                        <li><a href="/KOF(done)/templates/kof.jsp">KOF</a></li>
                    </ul>
                    <%--                <li><a href="/plan">计划组 Plan</a></li>--%>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/Register.jsp" id="resg">注册</a></li>
                <li ><a href="/login.jsp" id="logi">登录</a></li>
                <li ><a href="" id="pho"></a></li>
                <li ><a href="/login.jsp" onclick="qq()" id="quit">退出</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</body>
</html>
