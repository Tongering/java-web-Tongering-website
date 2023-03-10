package com.showpost.servlet;

import com.jdbc.updata;
import com.postcoment.query.CommentInvitationQuery;
import com.shares.instantiation.IsExistInstantiation;
import com.shares.query.IsExistQuery;
import com.showpost.instantiation.SayingInstantiation;
import com.showpost.query.QuerySaying;
import com.showpost.query.QueryShowPostPage;
import com.showpost.instantiation.ShowPostPageInstantiation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ExcuteURL extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String contentnone = req.getPathInfo();

        String contents = contentnone.split("/")[1];//已获取invitationid

        String servleturl = req.getServletPath();
        String htmltype = servleturl.split("/")[1];

        int k = 0;

        for(int i = contents.length(); --i>=0;)
        if(!Character.isDigit(contents.charAt(i))){
            resp.sendRedirect("/404.jsp");
            k=1;
            break;
        }
        String sqlserachpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.invitationid = ?";

        QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sqlserachpost,contents);
        if(showPostPageInstantiation.getUser()==null){
            resp.sendRedirect("/404.jsp");
            k=1;
        }

        if(k==0){

            Object id = req.getSession().getAttribute("id_user".toString());
            String sqlis = "select shareid, id, invitationid, likes, favorite, browse\n" +
                    "from shares\n" +
                    "where shares.id = ? and shares.invitationid = ?";
            if(id!=null){
                IsExistQuery isExistQuery = new IsExistQuery();
                IsExistInstantiation isExistInstantiation = isExistQuery.queryshareexist(sqlis,id,contents);
                updata updata = new updata();
                String sqlupinvitation = "update invitation set browse = browse + 1 where invitationid = ?";
                String sqlbrowse = "";
                if(isExistInstantiation.getShareid()==0){//对应的用户和帖子没有记录  insert
                    sqlbrowse = "insert into shares (id,invitationid,browse) values(?,?,1)";
                }
                else{
                    sqlbrowse = "update shares set browse = browse + 1 where id = ? and invitationid = ?";
                }
                updata.updateutil(sqlbrowse,id,contents);
                updata.updateutil(sqlupinvitation,contents);
            }

            String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                    "                from invitation\n" +
                    "                inner join id_photo\n" +
                    "                on invitation.id = id_photo.id\n" +
                    "                inner join id_user_password\n" +
                    "                on invitation.id = id_user_password.id\n" +
                    "where invitationid = ? and invitation.typeinvitation = ?";

            QueryShowPostPage queryshowpostpage = new QueryShowPostPage();
            ShowPostPageInstantiation ShowPostPageInstantiation = queryshowpostpage.queryshowpostpage(sql,contents,htmltype);

            req.setAttribute("user_photo", ShowPostPageInstantiation.getUser_photo());//把参数传给前端
            req.setAttribute("id", ShowPostPageInstantiation.getId());
            req.setAttribute("user", ShowPostPageInstantiation.getUser());
            req.setAttribute("title", ShowPostPageInstantiation.getTitle());
            req.setAttribute("invitationid", ShowPostPageInstantiation.getInvitationid());
            req.setAttribute("posttime", ShowPostPageInstantiation.getPosttime());
            req.setAttribute("likes", ShowPostPageInstantiation.getLikes());
            req.setAttribute("favorite", ShowPostPageInstantiation.getFavorite());
            req.setAttribute("browse", ShowPostPageInstantiation.getBrowse());


            String sqlcomments = "select id_user_password.user as user, id_photo.user_photo as photo, comments.content as comment, comments.id as id, comments.commentid as commentid\n" +
                    "from invitation\n" +
                    "\n" +
                    "inner join comments\n" +
                    "on invitation.invitationid = comments.invitationid\n" +
                    "\n" +
                    "inner join id_user_password\n" +
                    "on comments.id = id_user_password.id\n" +
                    "\n" +
                    "inner join id_photo\n" +
                    "on comments.id = id_photo.id\n" +
                    "\n" +
                    "where invitation.invitationid = ?\n" +
                    "order by comments.commentid desc";

            CommentInvitationQuery commentInvitationQuery = new CommentInvitationQuery();
            List comments = commentInvitationQuery.queryeachcomment(sqlcomments,contents);
            req.setAttribute("comments",comments);

            req.getRequestDispatcher("/htmlpage.jsp").forward(req,resp);
        }
    }
}
