package com.register.servlet;

import com.jdbc.updata;
import com.postapost.servlet.EachPostServlet;
import com.register.instantiation.GetId;
import com.register.instantiation.Radompho;
import com.register.query.QueryExamplePhoto;
import com.register.query.QueryId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String code = req.getParameter("validate");
        String sqlup = "insert into id_user_password (user,password) values (?,?)";

        boolean codet = code.equalsIgnoreCase(req.getSession().getAttribute("code").toString());

        PrintWriter pw=resp.getWriter();

        String sqlrandphoto = "select photobase64\n" +
                "from examphoto\n" +
                "where photoid = ?";

        InputStream is = EachPostServlet.class.getClassLoader().getResourceAsStream("postrandom.properties");
        Properties pros = new Properties();
        pros.load(is);

        Random rand = new Random();
        int min = Integer.parseInt(pros.getProperty("postmin")), max = Integer.parseInt(pros.getProperty("postmax"));
        int photoid = rand.nextInt(max - min + 1) + min;

        QueryExamplePhoto queryExamplePhoto = new QueryExamplePhoto();
        Radompho radompho = queryExamplePhoto.queryexamphoto(sqlrandphoto,photoid);
        String examphoto = radompho.getPhotobase();

        String sqlinpho = "insert into id_photo (id,user_photo) values(?,?)";

        String sqlid = "select id\n" +
                "from id_user_password\n" +
                "where user = ?";

        String sqlinself = "insert into id_myself (id,university,subject,birth,better,likes) values(?,?,?,?,?,?)";
        String defaultuniversity = "???????????????";
        String defaultsubject = "???????????????";
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//??????????????????
        String defaultbirth = df.format(date); // Formats a Date into a date/time string.
        String defaultbetter = "???????????????";
        String defaultlikes = "???????????????";



        if(codet&&password.equals(repassword)){

            boolean isDigit = false;
            boolean isLetter = false;
            for(int i=0; i<password.length(); i++){
                if(Character.isDigit(password.charAt(i))){
                    isDigit = true;
                }
                else if(Character.isLetter(password.charAt(i))){
                    isLetter = true;
                }
            }
            String regex = "^[a-zA-Z0-9]{8,20}$";
            boolean isRight = isDigit && isLetter && password.matches(regex);//???????????????????????????????????????????????????8~20???

            if(isRight==true){
                updata updata = new updata();
                updata.updateutil(sqlup, username, password);

                QueryId queryId = new QueryId();
                GetId getId = queryId.queryid(sqlid,username);

                int id = getId.getId();

                updata.updateutil(sqlinpho,id,examphoto);
                updata.updateutil(sqlinself,id,defaultuniversity,defaultsubject,defaultbirth,defaultbetter,defaultlikes);


                resp.addHeader("refresh","0;URL=login.jsp");

                pw.write("<script language='javascript'>alert('????????????')</script>");
            }
            else{
                pw.write("<script language='javascript'>alert('?????????????????????????????????2~20???')</script>");
            }
        }
        else if(!password.equals(repassword)){
            resp.addHeader("refresh","0;URL=Register.jsp");
            pw.write("<script language='javascript'>alert('???????????????????????????')</script>");
        }
        else {
            resp.addHeader("refresh","0;URL=Register.jsp");
            pw.write("<script language='javascript'>alert('???????????????')</script>");
        }
    }
}
