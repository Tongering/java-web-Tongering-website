package com.myself.left.servlet;

import com.jdbc.updata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpLoadingImgServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String imgbase = req.getParameter("imgbase64");
        Object id = req.getSession().getAttribute("id_user".toString());

        String sql = "update id_photo set user_photo = ? where id = ?";
        updata updata = new updata();
        updata.updateutil(sql,imgbase,id);
    }
}
