package com.disposename;

import com.login.query.UserProfileQuery;
import com.login.instantiation.UserProfileInstantiation;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DisposeNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Test
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        Object id_user = req.getSession().getAttribute("id_user".toString());

        System.out.println("id_user:" + id_user);

        if(id_user != null){
            String sql = "select user from id_user_password where id = ?";

            UserProfileQuery userProfileQuery = new UserProfileQuery();
            UserProfileInstantiation getusername = userProfileQuery.queryutil(sql, id_user);

            String username = getusername.getUser();
            out.print(username);
        }
        else{
            out.print(0);
        }


    }

}
