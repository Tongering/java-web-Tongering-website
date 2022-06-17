package com.shares.query;

import com.jdbc.JDBCutils;
import com.shares.instantiation.IsExistInstantiation;
import com.showpost.instantiation.SayingInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class IsExistQuery {
    public IsExistInstantiation queryshareexist(String sql, Object... args){
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

            IsExistInstantiation isExistInstantiation = new IsExistInstantiation();
            if(resultSet.next()){
                isExistInstantiation.setShareid(resultSet.getInt(1));
                isExistInstantiation.setId(resultSet.getInt(2));
                isExistInstantiation.setInvitationid(resultSet.getInt(3));
                isExistInstantiation.setLikes(resultSet.getInt(4));
                isExistInstantiation.setFavorite(resultSet.getInt(5));
                isExistInstantiation.setBrowse(resultSet.getInt(6));
            }
            return isExistInstantiation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
