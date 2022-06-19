package com.admin.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminDisposeUserQuery;
import com.admin.query.AdminIsExistQuery;
import com.admin.query.AdminUserQuery;
import com.postcoment.query.CommentInvitationQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminDisposeAdminServlet extends HttpServlet {
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
            String sqlisexit = "select id_user_password.id, id_user_password.user, id_photo.user_photo\n" +
                    "from adminuser\n" +
                    "inner join id_user_password\n" +
                    "on adminuser.id = id_user_password.id\n" +
                    "inner join id_photo\n" +
                    "on adminuser.id = id_photo.id";

            AdminIsExistQuery adminIsExistQuery = new AdminIsExistQuery();
            List isexist = adminIsExistQuery.queryisexist(sqlisexit);
            req.setAttribute("isexits",isexist);

            String sqluser = "select id_myself.id as id, id_user_password.user as user, id_photo.user_photo as user_photo, id_myself.university, id_myself.subject, id_myself.birth, id_myself.better, id_myself.likes\n" +
                    "from id_myself\n" +
                    "inner join id_photo\n" +
                    "on id_myself.id = id_photo.id\n" +
                    "inner join id_user_password\n" +
                    "on id_myself.id = id_user_password.id";
            AdminDisposeUserQuery adminDisposeUserQuery = new AdminDisposeUserQuery();
            List users = adminDisposeUserQuery.disposeuser(sqluser);
            req.setAttribute("users",users);

            req.getRequestDispatcher("/adminserver.jsp").forward(req,resp);
        }
        else{
            resp.addHeader("refresh","0;URL=login.jsp");
        }
    }
}
