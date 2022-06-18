package com.login.servlet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码的类
 * @author guduchina
 */
@SuppressWarnings("serial")
public class CodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.setProperty("java.awt.headless", "true");//在linux下避免发生sun.awt.X11GraphicsEnvironment异常
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.setProperty("java.awt.headless", "true");//在linux下避免发生sun.awt.X11GraphicsEnvironment异常
        response.setContentType("image/JPEG");
        ServletOutputStream out = response.getOutputStream();
        // 定义图片的高度和宽度
        int width = 80;
        int height = 25;
        // 定义随机数对象
        Random ran = new Random();
        // 定义图片缓冲区，使用RGB模式输出图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 定义画笔工具对象
        Graphics graphics = image.getGraphics();
        // 设置验证码框的背景颜色
        graphics.setColor(new Color(200, 200, 200));
        // 填充整个矩形
        graphics.fillRect(0, 0, width, height);
        // 定义要显示的验证码
        StringBuffer sb = new StringBuffer("");
        // 定义验证码数组
        String[] code = { "A", "a", "B", "b", "C", "c", "D", "d", "E", "e",
                "F", "f", "G", "g", "H", "h", "I", "i", "J", "j", "K", "k",
                "L", "l", "M", "m", "N", "n", "O", "o", "P", "p", "Q", "q",
                "R", "r", "S", "s", "T", "t", "U", "u", "V", "v", "W", "w",
                "X", "x", "Y", "y", "Z", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1",
                "2", "3", "4", "5", "6", "7", "8", "9", "0", "1" };
        for (int i = 0; i < 4; i++) {
            String str = code[ran.nextInt(code.length)];
            // 设置颜色
            graphics.setColor(new Color(ran.nextInt(150), ran.nextInt(150), ran
                    .nextInt(150)));
            // 设置字体
            graphics.setFont(new Font("宋体", Font.BOLD, 20));
            // 绘制验证码
            graphics.drawString(str, 20 * i+4, 20 - ran.nextInt(5));
            sb.append(str);
        }
        // 随机产生100个干扰点
        for (int i = 0; i < 100; i++) {
            // 随机生成干扰点的坐标
            int x = ran.nextInt(width);
            int y = ran.nextInt(height);
            // 随机生成干扰点的颜色
            graphics.setColor(new Color(ran.nextInt(185) + 40,
                    ran.nextInt(185) + 40, ran.nextInt(185) + 40));
            // 设置干扰点的位置长宽
            graphics.drawOval(x, y, 1, 1);
        }
        String codeInfo = sb.toString();
        // 将验证码的值放到session中，以供前台进行验证
        request.getSession().setAttribute("code", codeInfo);
        // 将Image通过out输出为jpeg格式的图片

        ImageIO.write(image, "JPEG", out);
        out.flush();
        out.close();
    }

}
