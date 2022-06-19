package com.admin.query;

import com.admin.instantiation.AdminDisposeUserInstantiation;
import com.jdbc.JDBCutils;
import com.postcoment.instantiation.GetCommentInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class AdminDisposeUserQuery {
    public List disposeuser(String sql, Object... args){
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
                    AdminDisposeUserInstantiation adminDisposeUserInstantiation = new AdminDisposeUserInstantiation();
                    adminDisposeUserInstantiation.setId(resultSet.getInt(1));
                    adminDisposeUserInstantiation.setUser(resultSet.getString(2));
                    adminDisposeUserInstantiation.setUser_photo(resultSet.getString(3));
                    adminDisposeUserInstantiation.setUniversity(resultSet.getString(4));
                    adminDisposeUserInstantiation.setSubject(resultSet.getString(5));
                    adminDisposeUserInstantiation.setBirth(resultSet.getDate(6));
                    adminDisposeUserInstantiation.setBetter(resultSet.getString(7));
                    adminDisposeUserInstantiation.setLikes(resultSet.getString(8));

                    posts.add(adminDisposeUserInstantiation);
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
