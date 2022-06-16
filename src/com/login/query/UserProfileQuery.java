package com.login.query;

import com.jdbc.JDBCutils;
import com.login.instantiation.UserProfileInstantiation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class UserProfileQuery {
    public UserProfileInstantiation queryutil(String sql, Object... args){
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
                UserProfileInstantiation userProfileInstantiation = new UserProfileInstantiation();
                for(int i=0;i<columnCount;i++){
                    Object columnvalue = resultSet.getObject(i + 1);

                    String columnName = resultSetMetaData.getColumnName(i + 1);

                    Field field = UserProfileInstantiation.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(userProfileInstantiation,columnvalue);

                }
                return userProfileInstantiation;
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
