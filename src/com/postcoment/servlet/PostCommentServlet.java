package com.postcoment.servlet;

import com.jdbc.updata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String invitationid = req.getParameter("invitationid");
        String content = req.getParameter("commentcontent");

        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String commenttime = df.format(date);

        String sql = "insert into comments (id,invitationid,content,commenttime) values(?,?,?,?)";

        PrintWriter pw=resp.getWriter();

        if(id!=null) {
            updata updata = new updata();
            updata.updateutil(sql, id, invitationid, content, commenttime);
//            resp.setIntHeader("Refresh", 0);
        }
        else{
            pw.print(0);
        }

    }
}
