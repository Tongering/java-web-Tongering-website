package com.admin.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminUserQuery;
import com.postcoment.query.CommentInvitationQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminDisposeCommentServlet extends HttpServlet {
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
            String sqlcomments = "select id_user_password.user as user, id_photo.user_photo as photo, comments.content as comment, comments.id as id, comments.commentid as commentid\n" +
                    "from invitation\n" +
                    "\n" +
                    "inner join comments\n" +
                    "on invitation.invitationid = comments.invitationid\n" +
                    "\n" +
                    "inner join id_user_password\n" +
                    "on comments.id = id_user_password.id\n" +
                    "\n" +
                    "inner join id_photo\n" +
                    "on comments.id = id_photo.id\n" +
                    "\n" +
                    "order by comments.commentid desc";

            CommentInvitationQuery commentInvitationQuery = new CommentInvitationQuery();
            List comments = commentInvitationQuery.queryeachcomment(sqlcomments);
            req.setAttribute("comments",comments);

            req.getRequestDispatcher("/admincomment.jsp").forward(req,resp);
        }
        else{
            resp.addHeader("refresh","0;URL=login.jsp");
        }
    }
}
