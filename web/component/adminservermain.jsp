<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>adminservermain</title>
</head>
<body>
    <div class="container">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-heading">管理员授权</div>
                <div class="panel-body">
                    <div>已授权管理员列表</div>
                    <table class="table table-striped">
                        <tr align="center">
                            <td>userid</td>
                            <td>user</td>
                            <td>userphoto</td>
                        </tr>
                        <c:forEach items="${isexits}" var="isexit" varStatus="status">
                            <tr align="center">
                                <td align="center" onclick="window.open('/space/' + ${isexit.id});" style="cursor:pointer">${isexit.id}</td>
                                <td align="center" onclick="window.open('/space/' + ${isexit.id});" style="cursor:pointer">${isexit.user}</td>
                                <td align="center" onclick="window.open('/space/' + ${isexit.id});" style="cursor:pointer">
                                    <img class="img-fluid borderpho" src="data:image/jpg;base64,${isexit.user_photo}" style="max-width: 35px;max-height: 20px">
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <hr>
                    <div>请进行操作</div>
                    <table class="table table-striped">
                        <tr align="center">
                            <td>userid</td>
                            <td>user</td>
                            <td>userphoto</td>
                            <td >升级为管理员</td>
                            <td >删除管理员</td>
                        </tr>
                        <c:forEach items="${users}" var="user" varStatus="status">
                            <tr align="center">
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer" >${user.id}</td>
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer">${user.user}</td>
                                <td align="center" onclick="window.open('/space/' + ${user.id});" style="cursor:pointer">
                                    <img class="img-fluid borderpho" src="data:image/jpg;base64,${user.user_photo}" style="max-width: 35px;max-height: 20px">
                                </td>
                                <td align="center">
                                    <span class="glyphicon glyphicon-ok" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="adminadd(this)" id="${user.id}"></span>
                                </td>
                                <td align="center">
                                    <span class="glyphicon glyphicon-trash" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="admindel(this)" id="${user.id}"></span>
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
