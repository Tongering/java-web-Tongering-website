package com.myself.left.servlet;

import com.myself.left.query.QueryForPhoto;
import com.myself.left.instantiation.Userphoto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DisposeIdPhotoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String sql = "select user_photo from id_photo where id = ? ";
        QueryForPhoto queryforphoto = new QueryForPhoto();

        Userphoto userphoto = queryforphoto.queryutilphoto(sql,id);

        String result = "{\"imgbase\":\"" + userphoto.getUser_photo() + "\"}";

        PrintWriter out = resp.getWriter();
        out.print(result);
    }
}
