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

public class AdminDelUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String userid = req.getParameter("userid");

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
            String sqldelusercom = "delete from comments where commentid = ?";
            String sqldeladmin = "delete from adminuser where id = ?";
            String sqldeluserprofile = "delete from id_myself where id = ?";
            String sqldelphoto = "delete from id_photo where id = ?";
            String sqldelpas = "delete from id_user_password where id = ?";
            String sqldelinvitation = "delete from invitation where id = ?";
            String sqldelshares = "delete from shares where id = ?";
            updata updata = new updata();
            updata.updateutil(sqldelusercom,userid);
            updata.updateutil(sqldeladmin,userid);
            updata.updateutil(sqldeluserprofile,userid);
            updata.updateutil(sqldelphoto,userid);
            updata.updateutil(sqldelpas,userid);
            updata.updateutil(sqldelinvitation,userid);
            updata.updateutil(sqldelshares,userid);
            pw.print(1);
        }
        else{
            pw.print(0);
        }
    }
}
