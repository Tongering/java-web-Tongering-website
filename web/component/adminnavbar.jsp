<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminnavbar</title>
</head>
<body>
  <nav class="navbar navbar-inverse">
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
          <li ><a href="/adminuser">用户管理</a></li>
          <li ><a href="/admininvitation">帖子管理</a></li>
          <li ><a href="/admincomment">评论管理</a></li>
          <li ><a href="/adminserver">添加管理员</a></li>
          <li ><a href="/about">回到首页</a></li>
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
