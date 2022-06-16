<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registermain</title>
</head>
<body>
<div class="container">
    <div class="col-xs-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-center">注册 Register</h3> <br>
                <form class="form-horizontal" action="register" method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label">用户名<a class="text-danger" id="user-c">
                            *</a></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="username" placeholder="Username" name="username"
                                   required="required" onblur="checkusername()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">密码 <a class="text-danger" id="pas-c">
                            *</a></label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password"
                                   required="required" onblur="checkrepassword()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">重复一遍密码 <a class="text-danger" id="resetpa">
                            *</a></label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="repassword" placeholder="Repassword" name="repassword"
                                   required="required" onblur="checkrepassword()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCode" class="col-sm-3 control-label">验证码 <a class="text-danger">
                            *</a></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="validate" size="5"  onBlur="checkCode()"
                                   id="inputCode" placeholder="Password" required="required">
                        </div>
                        <div class="col-sm-2">
                            <img class="code" alt="点击刷新" src="code" onclick="this.src='code?s='+new Date().getTime();">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="submit" class="btn btn-success" id="sub">注册 Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
