<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myselfmain</title>
</head>
<body>
<div class="container" >
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body" id="panel">
                <img class="img-thumbnail" id="caricature" src="" alt="">
                <hr>
                <h3 class="text-center" id="message-myself">个 人 信 息</h3>
                <h5 class="text-center" id="name-myself">null</h5>
                <h5 class="text-center" id="birth-myself">null</h5>
                <h5 class="text-center" id="universitydetail-myself">null</h5>
                <h5 class="text-center" id="subjectdetail-myself">null</h5>
                <hr>
                <h3 class="text-center" id="better-myself">优 势 特 长</h3>
                <div id="better-pan"></div>
                <hr>
                <h3 class="text-center" id="like-myself">兴 趣 爱 好</h3>
                <div id="like-pan"></div>
                <br>
            </div>
        </div>
    </div>
    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3>he</h3>
                <hr>
                <h3>heljfla</h3>
                <hr>
                <a class="btn btn-info" href="#" role="button">修改信息</a>
                <img class="layui-upload-img" name="titleBase64Img"
                     id="base64Img" src="" style="display: none" width="300px" height="100px;">
                <input type="file" id="image" lay-verify="required"
                       onchange="toBase64()" accept="image/jpeg,image/png,image/jpg"
                       class="layui-upload-button">
                <br>
            </div>
        </div>

    </div>
</div>
</body>
</html>
