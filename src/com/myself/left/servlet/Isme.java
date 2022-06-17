package com.myself.left.servlet;

import com.register.instantiation.GetId;
import com.register.query.QueryId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Isme extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("name");
        Object id = req.getSession().getAttribute("id_user".toString());

        String sqlid = "select id\n" +
                "from id_user_password\n" +
                "where user = ?";

        QueryId queryId = new QueryId();
        GetId getId = queryId.queryid(sqlid,username);

        Object personid = getId.getId();
        PrintWriter pw=resp.getWriter();
        if(personid.equals(id)){
            pw.print(1);
        }
        else{
            pw.print(0);
        }
    }
}
