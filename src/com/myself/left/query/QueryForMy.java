package com.myself.left.query;

import com.jdbc.JDBCutils;
import com.myself.left.instantiation.Usermyself;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryForMy {
    public Usermyself queryutilmyself(String sql, Object... args){
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

            if(resultSet.next()){
                Usermyself usermyself = new Usermyself();
                for(int i=0;i<columnCount;i++){
                    Object columnvalue = resultSet.getObject(i + 1);

                    String columnName = resultSetMetaData.getColumnName(i + 1);

                    Field field = Usermyself.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(usermyself,columnvalue);

                }
                return usermyself;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }

}

