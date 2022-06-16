package com.showpost.query;

import com.jdbc.JDBCutils;
import com.showpost.instantiation.ContentInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryContent {
    public ContentInstantiation querycontent(String sql, Object... args){
        Connection getconnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            getconnection = JDBCutils.getconnection();

            preparedStatement = getconnection.prepareStatement(sql);
            for (int i = 0;i <args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            ContentInstantiation ContentInstantiation = new ContentInstantiation();
            if(resultSet.next()){
                ContentInstantiation.setContent(resultSet.getString(1));
            }
            return ContentInstantiation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
