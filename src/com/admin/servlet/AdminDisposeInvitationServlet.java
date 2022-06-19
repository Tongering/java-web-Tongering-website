package com.admin.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminUserQuery;
import com.postapost.query.EachPostQuery;
import com.postcoment.query.CommentInvitationQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminDisposeInvitationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());

        String sqladmin = "select id\n" +
                "from adminuser\n" +
                "where id = ?";

        AdminUserQuery adminUserQuery = new AdminUserQuery();

        AdminUserInstantiation adminUserInstantiation = adminUserQuery.queryadminid(sqladmin,id);
        int k = 0;
        if(adminUserInstantiation.getId()!=0){
            k = 0;
        }
        else{
            k=1;
        }

        if(k==0){
            String sqlall = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
                    "from invitation\n" +
                    "inner join id_photo\n" +
                    "on invitation.id = id_photo.id\n" +
                    "inner join id_user_password\n" +
                    "on invitation.id = id_user_password.id\n" +
                    "order by invitation.posttime desc";//查询所有帖子

            EachPostQuery EachPostQuery = new EachPostQuery();//查询帖子操作
            List posts = EachPostQuery.queryeachposts(sqlall);
            req.setAttribute("invitations",posts);

            req.getRequestDispatcher("/admininvitation.jsp").forward(req,resp);
        }
        else{
            resp.addHeader("refresh","0;URL=login.jsp");
        }
    }
}
