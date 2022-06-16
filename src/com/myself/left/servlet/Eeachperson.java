package com.myself.left.servlet;

import com.myself.left.instantiation.Usermyself;
import com.myself.left.instantiation.Userphoto;
import com.myself.left.query.QueryForMy;
import com.myself.left.query.QueryForPhoto;
import com.postapost.query.EachPostQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

public class Eeachperson extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String personnone = req.getPathInfo();

        String personid = personnone.split("/")[1];//已获取personid

        int k = 0;

        for(int i = personid.length(); --i>=0;)
            if(!Character.isDigit(personid.charAt(i))){
                resp.sendRedirect("/404.jsp");
                k=1;
                break;
            }

        if(k==0) {
            String sqlphoto = "select user_photo from id_photo where id = ? ";
            QueryForPhoto queryforphoto = new QueryForPhoto();
            Userphoto userphoto = queryforphoto.queryutilphoto(sqlphoto,personid);

            req.setAttribute("userphoto",userphoto.getUser_photo());

            String sqlprofile = "select id, university,subject, birth,better,likes from id_myself where id = ? ";
            QueryForMy queryformy = new QueryForMy();
            Usermyself usermyself = queryformy.queryutilmyself(sqlprofile, personid);

            Object username = req.getSession().getAttribute("name_user".toString());
            String university = usermyself.getUniversity();
            String subject = usermyself.getSubject();
            Date birth = usermyself.getBirth();
            String better = usermyself.getBetter();
            String likes = usermyself.getLikes();

            req.setAttribute("username",username);
            req.setAttribute("university",university);
            req.setAttribute("subject",subject);
            req.setAttribute("birth",birth);
            req.setAttribute("better",better);
            req.setAttribute("likes",likes);

            String sqlpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
                    "                from invitation\n" +
                    "                inner join id_photo\n" +
                    "                on invitation.id = id_photo.id\n" +
                    "                inner join id_user_password\n" +
                    "                on invitation.id = id_user_password.id\n" +
                    "                where id_user_password.id = ?\n" +
                    "                order by invitation.posttime desc";

            EachPostQuery EachPostQuery = new EachPostQuery();
            List posts = EachPostQuery.queryeachposts(sqlpost,personid);
            req.setAttribute("posts",posts);




            req.getRequestDispatcher("/myself.jsp").forward(req,resp);
        }
    }
}
