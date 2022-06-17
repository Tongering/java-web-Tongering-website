package com.modifyprofile.servlet;

import com.jdbc.updata;
import com.login.instantiation.UserProfileInstantiation;
import com.login.query.UserProfileQuery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String university = req.getParameter("university");
        String subject = req.getParameter("subject");
        String better = req.getParameter("better");
        String likes = req.getParameter("likes");

        String sqlexist = "select * from id_user_password where id = ? and user = ?";
        String sqlupself = "update id_myself set university = ?, subject = ?, better = ?, likes = ? where id = ?";
        String sqlupname = "update id_user_password set user = ? where id = ?";

        UserProfileQuery userProfileQuery = new UserProfileQuery();
        UserProfileInstantiation userProfileInstantiation = userProfileQuery.queryutil(sqlexist,id,name);

        PrintWriter pw=resp.getWriter();

        if(userProfileInstantiation==null){//没有此人
            System.out.println("查无此人");
            pw.print(0);
        }
        else{
            System.out.println("执行成功");
            updata updata = new updata();
            updata.updateutil(sqlupself,university,subject,better,likes,id);
            updata.updateutil(sqlupname,username,id);
            pw.print(1);
        }
    }
}
