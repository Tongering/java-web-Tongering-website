<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/14
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>htmlpagemain</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-1"></div>
        <div class="col-xs-10" >
            <div class="panel panel-default" >
                <div class="panel-body" style="text-align: center;padding-bottom: 0px;"><h3 style="margin-top: 10px;margin-bottom: 0px;">${title}</h3></div>
                <div style="display:inline;padding-left: 100px;"><img src="data:image/jpg;base64,${user_photo}" style="max-width: 50px;margin-top: -15px"/></div>
                <div style="display:inline;margin-left:20px">${user}</div>
                <div style="display:inline;float: right;margin-right: 100px;margin-top: 10px;">
                    <div style="display:inline;" id="postid">
                        <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
                        <div id="invitationid" style="display:inline;">${invitationid}</div>
                    </div>
                    <div style="display:inline;">
                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                        <div id="likes" style="display:inline;">${likes}</div>
                    </div>
                    <div style="display:inline;">
                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                        <div id="favorite" style="display:inline;">${favorite}</div>
                    </div>
                    <div style="display:inline;">
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        <div id="browse" style="display:inline;">${browse}</div>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-body"style="margin-left: 30px;">
                    <div id="testEditorMdview">
                        <textarea id="appendTest" style="display:none;"></textarea>
                    </div>
                    <div style="float: right;margin-right: 75px;">
                        <div style="display:inline;">Star</div>
                        <div style="display:inline;">Favorite</div>
                        <div style="display:inline;">Brows</div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-body" style="margin-left: 30px;">
                    <div>
                        <div>
                            <span class="glyphicon glyphicon-user" aria-hidden="true" id="noneuser"> 请登录</span>
                        </div>
                        <img src="" style="max-width: 40px;display: none;" id="userphoto">
                        <div style="margin-left: -10px;margin-top:5px;display: none;" id="username"></div>
                        <div style="display: inline;">
                            <textarea class="form-control" rows="3" id="commentcontent" style="resize: vertical;min-height: 140px" name="content"></textarea>
                        </div>
                        <div id="worryuser" style="color: red"></div>
                        <div>
                            <a class="btn btn-info" href="#" role="button" style="display:inline;float: right;margin-left:50px;margin-top: 25px" onclick="postcomments()">发表评论</a>
                        </div>
                    </div>
                    <hr style="margin-top: 55px;">
                    <div id="comments">
                        <c:forEach items="${comments}" var="comment" varStatus="status">
                            <div on="commentdrawing()" style="background-color: rgba(0,0,0,0.1);margin-top: 10px;margin-bottom: 10px;border-radius: 10px;padding-bottom: 10px">
                                <div style="margin-top: 5px;margin-left: 5px">
                                    <img class="img-fluid" src="data:image/jpg;base64,${comment.photo}" style="max-width: 35px;max-height: 20px">
                                    <a id="${comment.id}" style="margin-left: 5px">${comment.user}</a>
                                </div>
                                <div style="margin-left: 50px;margin-top: 10px;margin-bottom: 10px">
                                        ${comment.comment}
                                </div>

<%--                                    <textarea style="display:none;">${comment.comment}</textarea>--%>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
