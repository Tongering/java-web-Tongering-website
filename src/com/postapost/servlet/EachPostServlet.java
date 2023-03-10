package com.postapost.servlet;

import com.postapost.query.EachPostQuery;
import com.showpost.instantiation.SayingInstantiation;
import com.showpost.query.QuerySaying;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EachPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String servleturl = req.getServletPath();
        String htmltype = servleturl.split("/")[1];//获取请求的页面类型

        req.setAttribute("htmltype",htmltype);//传送帖子类型


        String sqlappoint = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
                "from invitation\n" +
                "inner join id_photo\n" +
                "on invitation.id = id_photo.id\n" +
                "inner join id_user_password\n" +
                "on invitation.id = id_user_password.id\n" +
                "where invitation.typeinvitation = ?\n" +
                "order by invitation.posttime desc";//查询指定类型帖子

        String sqlall = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
                "from invitation\n" +
                "inner join id_photo\n" +
                "on invitation.id = id_photo.id\n" +
                "inner join id_user_password\n" +
                "on invitation.id = id_user_password.id\n" +
                "order by invitation.posttime desc";//查询所有帖子

        EachPostQuery EachPostQuery = new EachPostQuery();//查询帖子操作
        List posts;

        if(htmltype.equals("about")){
            posts = EachPostQuery.queryeachposts(sqlall);
        }
        else {
            posts = EachPostQuery.queryeachposts(sqlappoint,htmltype);
        }


        req.setAttribute("posts",posts);//传送查询帖子数据

        InputStream is = EachPostServlet.class.getClassLoader().getResourceAsStream("postrandom.properties");
        Properties pros = new Properties();
        pros.load(is);

        Random rand = new Random();
        int min = Integer.parseInt(pros.getProperty("postmin")), max = Integer.parseInt(pros.getProperty("postmax"));
        int sayingid = rand.nextInt(max - min + 1) + min;

        String sqlrandsaying = "select saying\n" +
                "from wellknowsaying\n" +
                "where sayingid = ?";

        QuerySaying querySaying = new QuerySaying();
        SayingInstantiation sayingInstantiation = querySaying.querysaying(sqlrandsaying,sayingid);

        req.setAttribute("wellsay",sayingInstantiation.getSaying());//传送名言

        req.getRequestDispatcher("Html.jsp").forward(req,resp);
    }
}
