package com.myself.left.servlet;

import com.myself.left.instantiation.Usermyself;
import com.myself.left.query.QueryForMy;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

public class MySelfLeftServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());

        String sql = "select id, university,subject, birth,better,likes from id_myself where id = ? ";
        QueryForMy queryformy = new QueryForMy();
        Usermyself usermyself = queryformy.queryutilmyself(sql, id);

        Object username = req.getSession().getAttribute("name_user".toString());
        String university = usermyself.getUniversity();
        String subject = usermyself.getSubject();
        Date birth = usermyself.getBirth();
        String better = usermyself.getBetter();
        String likes = usermyself.getLikes();

        String result = "{\"username\":\"" + username +
                "\",\"university\" :\"" + university +
                "\",\"subject\" :\"" + subject +
                "\",\"birth\" :\"" + birth +
                "\",\"better\" :\"" + better +
                "\",\"likes\" :\"" + likes  +
                "\"}";
        PrintWriter out = resp.getWriter();
        out.print(result);
    }
}
