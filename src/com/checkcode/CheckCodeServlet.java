package com.checkcode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CheckCodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String code=request.getParameter("validate");
        PrintWriter out = response.getWriter();
        if (!code.equalsIgnoreCase(request.getSession().getAttribute("code").toString())) {
            out.print(0);
        } else {
            out.print(1);
        }
        out.flush();
        out.close();
    }

}
