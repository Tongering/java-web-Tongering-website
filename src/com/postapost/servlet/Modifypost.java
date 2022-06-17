package com.postapost.servlet;

import com.jdbc.updata;
import com.showpost.instantiation.ShowPostPageInstantiation;
import com.showpost.query.QueryShowPostPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Modifypost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Object id = req.getSession().getAttribute("id_user".toString());
        String invitationid = req.getParameter("invitationid");

        PrintWriter pw=resp.getWriter();

        String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.id = ? and invitation.invitationid = ?";

        QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sql,id,invitationid);
        if(showPostPageInstantiation.getUser()==null){//该帖不属于该用户
            pw.print(0);
        }
        else{//该帖属于该用户
            pw.print(1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String modcontent = req.getParameter("modcontent");
        Object id = req.getSession().getAttribute("id_user".toString());
        String invitationid = req.getParameter("invitationid");

        String sql = "update invitation set content = ? where id = ? and invitationid = ?";
        String sqlsearchpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.id = ? and invitation.invitationid = ?";

        PrintWriter pw=resp.getWriter();

        QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sqlsearchpost,id,invitationid);
        if(showPostPageInstantiation.getUser()==null){//该帖不属于该用户
            pw.print(0);
        }
        else{//该帖属于该用户
            updata updata = new updata();
            updata.updateutil(sql, modcontent, id, invitationid);
            pw.print(1);
        }
    }
}
