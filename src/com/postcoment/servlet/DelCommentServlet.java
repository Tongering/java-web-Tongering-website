package com.postcoment.servlet;

import com.jdbc.updata;
import com.postcoment.instantiation.OnlyCommentInstantiation;
import com.postcoment.query.OnlyCommentQuery;
import com.showpost.instantiation.ShowPostPageInstantiation;
import com.showpost.query.QueryShowPostPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DelCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Object id = req.getSession().getAttribute("id_user".toString());
        String commentid = req.getParameter("commentid");
        String invitationid = req.getParameter("invitationid");

        String sqlexistuser = "select id, invitationid, commentid, content, commenttime\n" +
                "from comments\n" +
                "where commentid = ? and id = ?";

        OnlyCommentQuery onlyCommentQuery = new OnlyCommentQuery();
        OnlyCommentInstantiation isuser = onlyCommentQuery.querycomment(sqlexistuser,commentid,id);//查看是否是自己发的评论

        String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.id = ? and invitation.invitationid = ?";

        QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sql,id,invitationid);

        PrintWriter pw=resp.getWriter();

        if(isuser.getContent()==null&&showPostPageInstantiation.getUser()==null){//不是自己或题主发的评论
            pw.print(0);
        }
        else{
            String sqldelcom = "delete from comments where commentid = ?";
            updata updata = new updata();
            updata.updateutil(sqldelcom,commentid);
            pw.print(1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Object id = req.getSession().getAttribute("id_user".toString());
        String commentid = req.getParameter("commentid");
        String invitationid = req.getParameter("invitationid");

        String sqlexistuser = "select id, invitationid, commentid, content, commenttime\n" +
                "from comments\n" +
                "where commentid = ? and id = ?";

        OnlyCommentQuery onlyCommentQuery = new OnlyCommentQuery();
        OnlyCommentInstantiation isuser = onlyCommentQuery.querycomment(sqlexistuser,commentid,id);//查看是否是自己发的评论

        String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.id = ? and invitation.invitationid = ?";

        QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sql,id,invitationid);

        PrintWriter pw=resp.getWriter();

        if(isuser.getContent()==null&&showPostPageInstantiation.getUser()==null){//不是自己或题主发的评论
            pw.print(0);
        }
        else {
            String sqldelcom = "delete from comments where commentid = ?";
            updata updata = new updata();
            updata.updateutil(sqldelcom,commentid);
            pw.print(1);
        }
    }
}
