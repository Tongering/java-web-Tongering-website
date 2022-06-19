package com.admin.servlet;

import com.admin.instantiation.AdminUserInstantiation;
import com.admin.query.AdminDisposeUserQuery;
import com.admin.query.AdminUserQuery;
import com.jdbc.updata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String addid = req.getParameter("addid");

        String sqladmin = "select id\n" +
                "from adminuser\n" +
                "where id = ?";

        AdminUserQuery adminUserQuery = new AdminUserQuery();

        AdminUserInstantiation adminUserInstantiation = adminUserQuery.queryadminid(sqladmin,id);
        AdminUserInstantiation adminisexist = adminUserQuery.queryadminid(sqladmin,addid);
        PrintWriter pw=resp.getWriter();
        int k = 0;
        if(adminUserInstantiation.getId()!=0){
            k = 0;
        }
        else{
            k=1;
        }

        if(k==0){
            if(adminisexist.getId()==0){//当前用户可以添加
                String sqlin = "insert into adminuser (id) values (?)";
                updata updata = new updata();
                updata.updateutil(sqlin, addid);
                pw.print(1);
            }
            else{
                pw.print(0);
            }
        }
        else{
            resp.addHeader("refresh","0;URL=login.jsp");
        }
    }
}
