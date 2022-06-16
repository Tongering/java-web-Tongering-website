package com.register.query;

import com.jdbc.JDBCutils;
import com.register.instantiation.GetId;
import com.register.instantiation.Radompho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryId {
    public GetId queryid(String sql, Object... args){
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

            GetId getId = new GetId();
            if(resultSet.next()){
                getId.setId(resultSet.getInt(1));
            }
            return getId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
