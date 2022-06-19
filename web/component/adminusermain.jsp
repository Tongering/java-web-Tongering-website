<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>adminusermain</title>
</head>
<body>
    <div class="container">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-heading">用户管理</div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr align="center">
                            <td>userid</td>
                            <td>username</td>
                            <td>userphoto</td>
                            <td >university</td>
                            <td >subject</td>
                            <td >birth</td>
                            <td >better</td>
                            <td >likes</td>
                            <td >删除用户</td>
                        </tr>
                        <c:forEach items="${users}" var="user" varStatus="status">
                            <tr align="center">
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer">${user.id}</td>
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer">${user.user}</td>
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer">
                                    <img class="img-fluid borderpho" src="data:image/jpg;base64,${user.user_photo}" style="max-width: 35px;max-height: 20px">
                                </td>
                                <td align="center">${user.university}</td>
                                <td align="center">${user.subject}</td>
                                <td align="center">${user.birth}</td>
                                <td align="center">${user.better}</td>
                                <td align="center">${user.likes}</td>
                                <td align="center">
                                    <span class="glyphicon glyphicon-trash" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="admindeluser(this)" id="${user.id}"></span>
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
