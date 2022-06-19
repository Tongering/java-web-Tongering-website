<%--
  Created by IntelliJ IDEA.
  User: wzhen
  Date: 2022/6/19
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admininvitationmain</title>
</head>
<body>
    <div class="container">
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <div class="panel panel-default">
                <div class="panel-heading">帖子管理</div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr align="center">
                            <td>userid</td>
                            <td>user</td>
                            <td>userphoto</td>
                            <td >invitationid</td>
                            <td >typeinvitation</td>
                            <td >title</td>
                            <td >posttime</td>
                            <td >删除帖子</td>
                        </tr>
                        <c:forEach items="${invitations}" var="invitation" varStatus="status">
                            <tr align="center">
                                <td align="center" onclick="window.open('/space/' + ${invitation.id});" style="cursor:pointer">${invitation.id}</td>
                                <td align="center" onclick="window.open('/space/' + ${invitation.id});" style="cursor:pointer">${invitation.user}</td>
                                <td align="center" onclick="window.open('/space/' + ${invitation.id});" style="cursor:pointer">
                                    <img class="img-fluid borderpho" src="data:image/jpg;base64,${invitation.user_photo}" style="max-width: 35px;max-height: 20px">
                                </td>
                                <td align="center">${invitation.invitationid}</td>
                                <td align="center">${invitation.typeinvitation}</td>
                                <td align="center" onclick="window.open('/' + '${invitation.typeinvitation}' + '/content/' + ${invitation.invitationid});" style="cursor:pointer">${invitation.title}</td>
                                <td align="center">${invitation.posttime}</td>
                                <td align="center">
                                    <span class="glyphicon glyphicon-trash" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="admindelinvitation(this)" id="${invitation.invitationid}"></span>
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
