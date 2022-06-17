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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        Object id = req.getSession().getAttribute("id_user".toString());
        String oripa = req.getParameter("oripa");
        String newpa = req.getParameter("newpa");
        String renew = req.getParameter("renew");

        String sqlcheckoripa ="select password from id_user_password where id = ?";
        String sqluppas = "update id_user_password set password = ? where id = ?";

        PrintWriter pw=resp.getWriter();

        if(newpa.equals(renew)){
            boolean isDigit = false;
            boolean isLetter = false;
            for(int i=0; i<newpa.length(); i++){
                if(Character.isDigit(newpa.charAt(i))){
                    isDigit = true;
                }
                else if(Character.isLetter(newpa.charAt(i))){
                    isLetter = true;
                }
            }
            String regex = "^[a-zA-Z0-9]{8,20}$";
            boolean isRight = isDigit && isLetter && newpa.matches(regex);//判断新密码是否包含字母和数字，并为8~20位

            if(isRight==true){
                UserProfileQuery userProfileQuery = new UserProfileQuery();
                UserProfileInstantiation userProfileInstantiation = userProfileQuery.queryutil(sqlcheckoripa,id);

                if(Objects.equals(userProfileInstantiation.getPassword(), oripa)){
                    updata updata = new updata();
                    updata.updateutil(sqluppas,newpa,id);
                    pw.print(1);//正确
                }
                else{
                    pw.print(2);//原密码错误
                }
            }
            else{
                pw.print(3);//密码应包含字母和数字，且在8~20位
            }
        }
        else{
            pw.print(0);//重复密码错误
        }
    }
}
