package com.register.servlet;

import com.login.query.UserProfileQuery;
import com.login.instantiation.UserProfileInstantiation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");


        if(username != null){
            String sql = "select id from id_user_password where user = ?";

            UserProfileQuery userProfileQuery = new UserProfileQuery();
            UserProfileInstantiation getusername = userProfileQuery.queryutil(sql, username);

            if(getusername == null){
                out.print(1);
            }
            else{
                out.print(0);
            }
        }
        else{
            out.print(0);
        }
    }
}
