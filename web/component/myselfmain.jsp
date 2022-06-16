<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myselfmain</title>
</head>
<body>
<div class="container" >
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body" id="panel">
                <img class="img-thumbnail borderpho" id="caricature" src="data:image/jpg;base64,${userphoto}" alt="">
                <hr>
                <h3 class="text-center" id="message-myself">个 人 信 息</h3>
                <h5 class="text-center" id="name-myself">${username}</h5>
                <h5 class="text-center" id="birth-myself">${birth}</h5>
                <h5 class="text-center" id="universitydetail-myself">${university}</h5>
                <h5 class="text-center" id="subjectdetail-myself">${subject}</h5>
                <hr>
                <h3 class="text-center" id="better-myself">优 势 特 长</h3>
                <div id="better-pan" style="text-align: center">${better}</div>
                <hr>
                <h3 class="text-center" id="like-myself">兴 趣 爱 好</h3>
                <div id="like-pan" style="text-align: center">${likes}</div>
                <br>
            </div>
        </div>
    </div>
    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <a class="btn btn-info" href="#" role="button">修改信息</a>
                <img class="layui-upload-img" name="titleBase64Img"
                     id="base64Img" src="" style="display: none" width="300px" height="100px;">
                <input  type="file" id="image" lay-verify="required"
                       onchange="toBase64()" accept="image/jpeg,image/png,image/jpg"
                       class="layui-upload-button">
                <br>
            </div>
        </div>
        <c:forEach items="${posts}" var="i" varStatus="status">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${i.typeinvitation}</h4>
                    <hr style="margin-top: 5px;margin-bottom: 10px">
                    <div class="row">
                        <div class="col-xs-2 col-sm-1">
                            <img class="borderpho" src="data:image/jpg;base64,${i.user_photo}"style="max-width: 40px">
                        </div>
                        <div class="col-xs-10 col-sm-11">
                            <a href="/space/${i.id}" target="_blank">${i.user}</a>
                            <br>
                            <span>${i.posttime}</span>
                        </div>
                    </div>
                    <div style="text-align: center">
                        <a href="/${i.typeinvitation}/content/${i.invitationid}" target="_blank">${i.title}</a>
                    </div>
                    <hr>
                    <br>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
