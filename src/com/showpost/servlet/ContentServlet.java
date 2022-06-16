package com.showpost.servlet;

import com.showpost.instantiation.ContentInstantiation;
import com.showpost.query.QueryContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ContentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String invitationid = req.getParameter("invitationid");

        System.out.println(invitationid);

        String sql = "select content\n" +
                "from invitation\n" +
                "where invitationid = ?";

        QueryContent querycontent = new QueryContent();
        ContentInstantiation ContentInstantiation = querycontent.querycontent(sql,invitationid);

        PrintWriter out = resp.getWriter();
        out.print(ContentInstantiation.getContent());
    }
}
