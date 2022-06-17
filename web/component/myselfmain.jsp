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
                <ol class="breadcrumb" id="mynavbar" style="display: none">
                    <li><a onclick="showmodifyprofile()">修改我的信息</a></li>
                    <li><a onclick="showmodifypassword()">修改密码</a></li>
                    <li><a onclick="showmodifyphoto()">修改头像</a></li>
                    <li><a onclick="showmythumb()">我的点赞</a></li>
                    <li><a onclick="showmyfavorite()">我的收藏</a></li>
                    <li style="float: right"><a onclick="showsearchuser()">搜索用户</a></li>
                </ol>
                <div id="modifyprofile" style="display: none">
                    <hr>
                    <div class="col-sm-5" style="text-align: center;margin: 0 auto;">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">我的名字</span>
                            <input type="text" class="form-control" placeholder="${username}" required="required" name="username" onblur="modifyusernamecheck()" id="username">
                        </div>
                        <div id="worryusername"></div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">我的大学</span>
                            <input type="text" class="form-control" placeholder="${university}"  name="university">
                        </div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">我的专业</span>
                            <input type="text" class="form-control" placeholder="${subject}"  name="subject">
                        </div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">我的优势</span>
                            <input type="text" class="form-control" placeholder="${better}"  name="better">
                        </div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">我的特长</span>
                            <input type="text" class="form-control" placeholder="${likes}" name="likes">
                        </div>
                        <br>
                        <button class="btn btn-default" id="sub" onclick="postmodifyprofile()">提交修改</button>
                    </div>
                </div>
                <div id="modifypassword" style="display: none">
                    <hr>
                    <div class="col-sm-5" style="text-align: center;margin: 0 auto;">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">原密码</span>
                            <input type="text" class="form-control" id="oripassword">
                        </div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">新密码</span>
                            <input type="text" class="form-control" id="newpassword">
                        </div>
                        <br>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">重复新密码</span>
                            <input type="text" class="form-control" id="renewpassword">
                        </div>
                        <div id="worrypassword"></div>
                        <br>
                        <button class="btn btn-default" onclick="postmodifypassword()">提交修改</button>
                    </div>
                </div>
                <div id="modifyphoto" style="display: none">
                    <hr>
                    <h5>请上传头像</h5>
                    <img class="layui-upload-img" name="titleBase64Img"
                         id="base64Img" src="" style="display: none" width="300px" height="100px;">
                    <input  type="file" id="image" lay-verify="required"
                            onchange="toBase64()" accept="image/jpeg,image/png,image/jpg"
                            class="layui-upload-button">
                </div>
                <div id="searchuser" style="display: none">
                    <div class="col-sm-5">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </span>
                            <input type="text" class="form-control" id="searchname" placeholder="不支持模糊搜索,请输入用户名">
                        </div>
                        <button class="btn btn-default" onclick="searchuser()">搜索</button>
                        <br>
                        <div id="usercard">
                            <img id="searchuserphoto" style="width: 80px">
                            <a style="display: inline" id="searchusername"></a>
                        </div>
                    </div>
                </div>

                <br>
            </div>
        </div>
        <div id="mythumb" style="display: none">
            <c:forEach items="${thumbpost}" var="thumb" varStatus="status">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${thumb.typeinvitation}</h4>
                        <hr style="margin-top: 5px;margin-bottom: 10px">
                        <div class="row">
                            <div class="col-xs-2 col-sm-1">
                                <img class="borderpho" src="data:image/jpg;base64,${thumb.user_photo}"style="max-width: 40px">
                            </div>
                            <div class="col-xs-10 col-sm-11">
                                <a href="/space/${thumb.id}" target="_blank">${thumb.user}</a>
                                <br>
                                <span>${thumb.posttime}</span>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <a href="/${thumb.typeinvitation}/content/${thumb.invitationid}" target="_blank">${thumb.title}</a>
                        </div>
                        <hr>
                        <br>
                    </div>
                </div>
            </c:forEach>
            <div style="text-align: center"><a>👆 我的点赞</a><hr></div>
        </div>
        <div id="myfavorite" style="display: none">
            <c:forEach items="${favoritepost}" var="fav" varStatus="status">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${fav.typeinvitation}</h4>
                        <hr style="margin-top: 5px;margin-bottom: 10px">
                        <div class="row">
                            <div class="col-xs-2 col-sm-1">
                                <img class="borderpho" src="data:image/jpg;base64,${fav.user_photo}"style="max-width: 40px">
                            </div>
                            <div class="col-xs-10 col-sm-11">
                                <a href="/space/${fav.id}" target="_blank">${fav.user}</a>
                                <br>
                                <span>${fav.posttime}</span>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <a href="/${fav.typeinvitation}/content/${fav.invitationid}" target="_blank">${fav.title}</a>
                        </div>
                        <hr>
                        <br>
                    </div>
                </div>
            </c:forEach>
            <div style="text-align: center"><a>👆 我的收藏</a><hr></div>
        </div>
        <div id="mypost">
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
            <div style="text-align: center"><a>👆 帖子</a><hr></div>
        </div>
    </div>
</div>
</body>
</html>
