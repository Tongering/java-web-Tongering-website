<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/12
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>htmlbase</title>
    <script src="/static/js/htmlpost.js"></script>
    <script src="/static/js/searchposts.js"></script>
<%--    TODO searchpost或许可以删掉--%>
    <script src="/static/js/refuserpho.js"></script>
    <script src="/static/js/htmlpostsready.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12" >
                <div class="panel panel-default">
                    <div class="panel-body" style="text-align: center;"><h3 id="titletype" style="margin-top: 10px;">${htmltype}</h3><h5 style="margin-top: 10px;">${wellsay}</h5></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="text-align: center;">
        <button type="button" class="btn btn-primary">
            <span class="glyphicon glyphicon-edit" aria-hidden="true" onclick="showthepost()" id="writepost"> 我也来写一篇</span>
        </button>
    </div>
    <br>
    <div class="row" id="postinvitation" style="display:none ;">
        <div class="col-xs-2"></div>
        <div class="col-xs-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"> ${htmltype}</span>
                </div>
                <div class="panel-body">
                    <form action="postinvitation" method="post" target="myIframe">
                        <div class="form-group">
                            <label for="types">类型</label>
                            <input type="text" class="form-control" id="types" name="types" value="${htmltype}" readonly="readonly" required="required">
                        </div>
                        <div class="form-group">
                            <label for="title">标题</label>
                            <input type="text" class="form-control" id="title" name="title" required="required">
                        </div>
                        <div class="form-group">
                            <label for="content" >正文</label>
                            <textarea class="form-control" rows="3" id="content" style="resize: vertical;min-height: 140px" name="content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success">发帖</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-xs-2"></div>
    </div>
    <c:forEach items="${posts}" var="i" varStatus="status">
        <div class="row"style="">
            <div class="col-xs-4" style="float: none;display: block;margin-left: auto;margin-right: auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <img class="img-fluid borderpho" src="data:image/jpg;base64,${i.user_photo}" style="max-width: 35px;max-height: 20px">
                        <a id="${i.id}" href="/space/${i.id}" target="_blank">${i.user}</a>
                        <a class="posttype" style="display: none;float: right;margin-right: 10px" href="/${i.typeinvitation}">${i.typeinvitation}</a>
                    </div>
                    <div class="panel-body" style="text-align: center;">
                        <a id="${i.invitationid}"onclick="turn(this)" style="user-select:none;" href="/${i.typeinvitation}/content/${i.invitationid}">${i.title}</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
<%--        <div class="col-xs-2"></div>--%>
    </div>
</body>
</html>
