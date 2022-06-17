package com.postcoment.query;

import com.jdbc.JDBCutils;
import com.postcoment.instantiation.OnlyCommentInstantiation;
import com.register.instantiation.GetId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class OnlyCommentQuery {
    public OnlyCommentInstantiation querycomment(String sql, Object... args){
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

            OnlyCommentInstantiation onlyCommentInstantiation = new OnlyCommentInstantiation();
            if(resultSet.next()){
                onlyCommentInstantiation.setId(resultSet.getInt(1));
                onlyCommentInstantiation.setInvitationid(resultSet.getInt(2));
                onlyCommentInstantiation.setCommentid(resultSet.getInt(3));
                onlyCommentInstantiation.setContent(resultSet.getString(4));
                onlyCommentInstantiation.setCommenttime(resultSet.getTimestamp(5));
            }
            return onlyCommentInstantiation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
