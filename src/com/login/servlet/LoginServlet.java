package com.login.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminUserQuery;
import com.login.query.UserProfileQuery;
import com.login.instantiation.UserProfileInstantiation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("validate");
        String sql = "select id,user,password from id_user_password where user = ? and password = ?";

        UserProfileQuery userProfileQuery = new UserProfileQuery();
        UserProfileInstantiation checknamepas = userProfileQuery.queryutil(sql, username, password);
        int test = -1;
        if(checknamepas !=null) test = checknamepas.getId();
        else test = -1;

        boolean codet = code.equalsIgnoreCase(req.getSession().getAttribute("code").toString());

        String sqladmin = "select id\n" +
                "from adminuser\n" +
                "where id = ?";

        AdminUserQuery adminUserQuery = new AdminUserQuery();

        if((test!=-1)&&codet){
            req.getSession().setAttribute("id_user", test);
            AdminUserInstantiation adminUserInstantiation = adminUserQuery.queryadminid(sqladmin,test);

            if(adminUserInstantiation.getId()!=0){
                resp.sendRedirect("/adminuser");
            }
            else{
                resp.sendRedirect("/space" + "/" + test);
            }
        }
        else{
            PrintWriter pw=resp.getWriter();
            resp.addHeader("refresh","0;URL=login.jsp");
            if(codet){
                pw.write("<script language='javascript'>alert('???????????????????????????')</script>");
            }
            else{
                pw.write("<script language='javascript'>alert('??????????????????')</script>");

            }

        }
    }
}
