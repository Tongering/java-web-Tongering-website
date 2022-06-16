package com.postapost.servlet;

import com.postapost.query.EachPostQuery;
import com.showpost.instantiation.SayingInstantiation;
import com.showpost.query.QuerySaying;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
        String htmltype = servleturl.split("/")[1];

        req.setAttribute("htmltype",htmltype);


        String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime\n" +
                "from invitation\n" +
                "inner join id_photo\n" +
                "on invitation.id = id_photo.id\n" +
                "inner join id_user_password\n" +
                "on invitation.id = id_user_password.id\n" +
                "where invitation.typeinvitation = ?\n" +
                "order by invitation.posttime desc";
        EachPostQuery EachPostQuery = new EachPostQuery();
        List posts = EachPostQuery.queryeachposts(sql,htmltype);

        req.setAttribute("posts",posts);

        Random rand = new Random();
        int min = 1, max = 2;
        int sayingid = rand.nextInt(max - min + 1) + min;

        String sqlrandsaying = "select saying\n" +
                "from wellknowsaying\n" +
                "where sayingid = ?";

        QuerySaying querySaying = new QuerySaying();
        SayingInstantiation sayingInstantiation = querySaying.querysaying(sqlrandsaying,sayingid);

        req.setAttribute("wellsay",sayingInstantiation.getSaying());

        req.getRequestDispatcher("Html.jsp").forward(req,resp);
    }
}
