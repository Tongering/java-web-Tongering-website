<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginmain</title>
</head>
<body>
<div class="container">
    <div class="col-xs-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-center">登录 Login</h3> <br>
                <form class="form-horizontal" action="login" method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label">用户名<a class="text-danger">
                            *</a></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="username" placeholder="Username" name="username"
                                   required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">密码 <a class="text-danger">
                            *</a></label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password"
                                   required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCode" class="col-sm-3 control-label">验证码 <a class="text-danger">
                            *</a></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="validate" size="5"  onBlur="checkCode()"
                                   id="inputCode" placeholder="auth code" required="required">
                        </div>
                        <div class="col-sm-2">
                            <img class="code" alt="点击刷新" src="code" onclick="this.src='code?s='+new Date().getTime();">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-2">
                            <button type="submit"  class="btn btn-success" id="sub">登入 Submit</button>
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-info"
                                    onclick="location.href='Register.jsp'">注册 Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
