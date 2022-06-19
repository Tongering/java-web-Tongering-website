package com.admin.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminUserQuery;
import com.jdbc.updata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminDelcomment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String commentid = req.getParameter("commentid");

        String sqladmin = "select id\n" +
                "from adminuser\n" +
                "where id = ?";

        AdminUserQuery adminUserQuery = new AdminUserQuery();

        AdminUserInstantiation adminUserInstantiation = adminUserQuery.queryadminid(sqladmin,id);
        PrintWriter pw=resp.getWriter();
        int k = 0;
        if(adminUserInstantiation.getId()!=0){
            k = 0;
        }
        else{
            k=1;
        }
        if(k==0){
            String sqldelcom = "delete from comments where commentid = ?";
            updata updata = new updata();
            updata.updateutil(sqldelcom,commentid);
            pw.print(1);
        }
        else{
            pw.print(0);
        }
    }
}
