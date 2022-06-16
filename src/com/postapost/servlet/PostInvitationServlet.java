package com.postapost.servlet;

import com.jdbc.updata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostInvitationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String types = req.getParameter("types");

        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.

        String sql = "insert into invitation (id,title,content,posttime,typeinvitation) values (?,?,?,?,?)";

        PrintWriter pw=resp.getWriter();

        if(id!=null){
            updata updata = new updata();
            updata.updateutil(sql,id,title,content,dateTime,types);
            resp.addHeader("refresh","0;URL=/" + types);
            pw.write("<script language='javascript'>alert('发帖成功')</script>");
        }
        else{
            pw.write("<script language='javascript'>alert('请登录')</script>");
        }

    }
}
