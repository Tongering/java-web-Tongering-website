package com.showpost.query;

import com.jdbc.JDBCutils;
import com.showpost.instantiation.ShowPostPageInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryShowPostPage {
    public ShowPostPageInstantiation queryshowpostpage(String sql, Object... args){
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

            ShowPostPageInstantiation ShowPostPageInstantiation = new ShowPostPageInstantiation();
            if(resultSet.next()){
                ShowPostPageInstantiation.setId(resultSet.getInt(1));
                ShowPostPageInstantiation.setUser_photo(resultSet.getString(2));
                ShowPostPageInstantiation.setUser(resultSet.getString(3));
                ShowPostPageInstantiation.setTitle(resultSet.getString(4));
                ShowPostPageInstantiation.setInvitationid(resultSet.getInt(5));
                ShowPostPageInstantiation.setPosttime(resultSet.getTimestamp(6));
                ShowPostPageInstantiation.setLikes(resultSet.getInt(7));
                ShowPostPageInstantiation.setFavorite(resultSet.getInt(8));
                ShowPostPageInstantiation.setBrowse(resultSet.getInt(9));
            }
            return ShowPostPageInstantiation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
