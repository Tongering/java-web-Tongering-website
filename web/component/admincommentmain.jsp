<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admincommentmain</title>
</head>
<body>
    <div class="container">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-heading">评论管理</div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr align="center">
                            <td>userid</td>
                            <td>user</td>
                            <td>userphoto</td>
                            <td >commentid</td>
                            <td >context</td>
                            <td >删除评论</td>
                        </tr>
                        <c:forEach items="${comments}" var="comment" varStatus="status">
                            <tr align="center">
                                <td align="center" onclick="window.open('/space/' + ${comment.id});" style="cursor:pointer">${comment.id}</td>
                                <td align="center" onclick="window.open('/space/' + ${comment.id});" style="cursor:pointer">${comment.user}</td>
                                <td align="center" onclick="window.open('/space/' + ${comment.id});" style="cursor:pointer">
                                    <img class="img-fluid borderpho" src="data:image/jpg;base64,${comment.photo}" style="max-width: 35px;max-height: 20px">
                                </td>
                                <td align="center">${comment.commentid}</td>
                                <td align="center">${comment.comment}</td>
                                <td align="center">
                                    <span class="glyphicon glyphicon-trash" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="admindelcomment(this)" id="${comment.commentid}"></span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
