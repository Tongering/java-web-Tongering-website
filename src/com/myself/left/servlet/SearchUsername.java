package com.myself.left.servlet;

import com.myself.left.instantiation.Userphoto;
import com.myself.left.query.QueryForPhoto;
import com.register.instantiation.GetId;
import com.register.query.QueryId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String searchusername = req.getParameter("searchusername");
        String sqlid = "select id\n" +
                "from id_user_password\n" +
                "where user = ?";

        QueryId queryId = new QueryId();
        GetId getId = queryId.queryid(sqlid,searchusername);

        PrintWriter out = resp.getWriter();

        if(getId.getId()==0){
            out.print(0);
        }
        else{
            int id = getId.getId();
            String sql = "select user_photo from id_photo where id = ? ";
            QueryForPhoto queryforphoto = new QueryForPhoto();

            Userphoto userphoto = queryforphoto.queryutilphoto(sql,id);
            String result = searchusername + "(" + id + "(" + userphoto.getUser_photo();
            out.print(result);
        }
    }
}
