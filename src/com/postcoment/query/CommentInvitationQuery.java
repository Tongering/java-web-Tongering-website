package com.postcoment.query;

import com.jdbc.JDBCutils;
import com.postcoment.instantiation.GetCommentInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CommentInvitationQuery {
    public List queryeachcomment(String sql, Object... args){
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



            List posts = new ArrayList();
            if(resultSet != null){
                while(resultSet.next()){
                    GetCommentInstantiation getCommentInstantiation = new GetCommentInstantiation();

                    getCommentInstantiation.setUser(resultSet.getString(1));
                    getCommentInstantiation.setPhoto(resultSet.getString(2));
                    getCommentInstantiation.setComment(resultSet.getString(3));
                    getCommentInstantiation.setId(resultSet.getInt(4));
                    getCommentInstantiation.setCommentid(resultSet.getInt(5));

                    posts.add(getCommentInstantiation);
                }
            }
            return posts;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement,resultSet);
        }

        return null;
    }
}
