package com.shares.servlet;

import com.jdbc.updata;
import com.shares.instantiation.IsExistInstantiation;
import com.shares.query.IsExistQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SharesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String invitationid = req.getParameter("invitationid");
        String sharestype = req.getParameter("sharetype");

        String sqlis = "select shareid, id, invitationid, likes, favorite, browse\n" +
                "from shares\n" +
                "where shares.id = ? and shares.invitationid = ?";
        PrintWriter pw=resp.getWriter();
        if(id==null){//用户未登录
            pw.print("error");
        }
        else{
            IsExistQuery isExistQuery = new IsExistQuery();
            IsExistInstantiation isExistInstantiation = isExistQuery.queryshareexist(sqlis,id,invitationid);
            if(sharestype.equals("getlikes")){
                pw.print(isExistInstantiation.getLikes());
            }
            else if(sharestype.equals("getfavorite")){
                pw.print(isExistInstantiation.getFavorite());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String invitationid = req.getParameter("invitationid");
        String sharestype = req.getParameter("sharetype");

        String sqlis = "select shareid, id, invitationid, likes, favorite, browse\n" +
                "from shares\n" +
                "where shares.id = ? and shares.invitationid = ?";
        PrintWriter pw=resp.getWriter();
        if(id==null){//用户未登录
            pw.print(0);
        }
        else{
            IsExistQuery isExistQuery = new IsExistQuery();
            IsExistInstantiation isExistInstantiation = isExistQuery.queryshareexist(sqlis,id,invitationid);
            updata updata = new updata();

            if(isExistInstantiation.getShareid()==0){//对应的用户和帖子没有记录  insert
                String sqlinshares = "";
                String sqlininvitation = "";

                if(sharestype.equals("addlike")){
                    sqlinshares = "insert into shares (id,invitationid,likes) values(?,?,1)";
                    sqlininvitation = "update invitation set likes = likes + 1 where invitationid = ?";
                    updata.updateutil(sqlinshares,id,invitationid);
                    updata.updateutil(sqlininvitation,invitationid);
                }
                else if(sharestype.equals("addfavorite")){
                    sqlinshares = "insert into shares (id,invitationid,favorite) values(?,?,1)";
                    sqlininvitation = "update invitation set favorite = favorite + 1 where invitationid = ?";
                    updata.updateutil(sqlinshares,id,invitationid);
                    updata.updateutil(sqlininvitation,invitationid);
                }
                else if(sharestype.equals("delfavorite")||sharestype.equals("dellike")){
                    pw.print(0);
                }
            }
            else{//对应的用户和帖子有记录  update
                String sqlupshares = "";
                String sqlupinvitation = "";
                if(sharestype.equals("addlike")&&(isExistInstantiation.getLikes()==0)){
                    sqlupshares = "update shares set likes = likes + 1 where id = ? and invitationid = ?";
                    sqlupinvitation = "update invitation set likes = likes + 1 where invitationid = ?";
                    updata.updateutil(sqlupshares,id,invitationid);
                    updata.updateutil(sqlupinvitation,invitationid);
                }
                else if(sharestype.equals("dellike")&&(isExistInstantiation.getLikes()==1)){
                    sqlupshares = "update shares set likes = likes - 1 where id = ? and invitationid = ?";
                    sqlupinvitation = "update invitation set likes = likes - 1 where invitationid = ?";
                    updata.updateutil(sqlupshares,id,invitationid);
                    updata.updateutil(sqlupinvitation,invitationid);
                }
                else if(sharestype.equals("addfavorite")&&(isExistInstantiation.getFavorite()==0)){
                    sqlupshares = "update shares set favorite = favorite + 1 where id = ? and invitationid = ?";
                    sqlupinvitation = "update invitation set favorite = favorite + 1 where invitationid = ?";
                    updata.updateutil(sqlupshares,id,invitationid);
                    updata.updateutil(sqlupinvitation,invitationid);
                }
                else if(sharestype.equals("delfavorite")&&(isExistInstantiation.getFavorite()==1)){
                    sqlupshares = "update shares set favorite = favorite - 1 where id = ? and invitationid = ?";
                    sqlupinvitation = "update invitation set favorite = favorite - 1 where invitationid = ?";
                    updata.updateutil(sqlupshares,id,invitationid);
                    updata.updateutil(sqlupinvitation,invitationid);
                }
                else{
                    pw.print(0);
                }
            }
        }
    }
}
