package com.postcoment.servlet;

import com.jdbc.updata;
import com.postcoment.instantiation.OnlyCommentInstantiation;
import com.postcoment.query.OnlyCommentQuery;

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

        String sqlexistinv = "select id, invitationid, commentid, content, commenttime\n" +
                "from comments\n" +
                "where commentid = ? and invitationid = ?";

        OnlyCommentInstantiation isinvitation = onlyCommentQuery.querycomment(sqlexistinv,commentid,invitationid);

        PrintWriter pw=resp.getWriter();

        if(isuser.getContent()==null||isinvitation.getContent()==null){//不是自己或题主发的评论
            pw.print(0);
        }
        else{
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

        String sqlexistinv = "select id, invitationid, commentid, content, commenttime\n" +
                "from comments\n" +
                "where commentid = ? and invitationid = ?";

        OnlyCommentInstantiation isinvitation = onlyCommentQuery.querycomment(sqlexistinv,commentid,invitationid);

        PrintWriter pw=resp.getWriter();

        if(isuser.getContent()==null||isinvitation.getContent()==null){//不是自己或题主发的评论
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
