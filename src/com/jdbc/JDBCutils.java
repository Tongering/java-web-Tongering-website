package com.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBCutils {

    /**
     * @Description 获取数据库连接
     * @author tongering
     * @data
     * @return
     * @throws Exception
     */
    public static Connection getconnection() throws Exception {
        InputStream is = JDBCutils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

//        String user = "tempjdbc";
//        String password = "ie8G5bwCrpSN7aAw";
//        String url = "jdbc:mysql://119.29.201.212:3306/tempjdbc";
//        String driverClass = "com.mysql.cj.jdbc.Driver";

        //加载驱动
        Class.forName(driverClass);

        //获取链接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * @Description 关闭链接
     * @author tongering
     * @data
     * @param connection
     * @param preparedStatement
     */
    public static void closeResource(Connection connection, PreparedStatement preparedStatement){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void closeResource(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

