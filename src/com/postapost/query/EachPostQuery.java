package com.postapost.query;

import com.jdbc.JDBCutils;
import com.postapost.instantiation.EachPostInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class EachPostQuery {
    public List queryeachposts(String sql, Object... args){
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
                    EachPostInstantiation EachPostInstantiation = new EachPostInstantiation();

                    EachPostInstantiation.setId(resultSet.getInt(1));
                    EachPostInstantiation.setUser_photo(resultSet.getString(2));
                    EachPostInstantiation.setUser(resultSet.getString(3));
                    EachPostInstantiation.setTitle(resultSet.getString(4));
                    EachPostInstantiation.setInvitationid(resultSet.getInt(5));
                    EachPostInstantiation.setPosttime(resultSet.getTimestamp(6));
                    posts.add(EachPostInstantiation);
                }
            }
//            if(resultSet.next()){
//                eachpost eachpost = new eachpost();
//
//                eachpost.setId(resultSet.getInt(1));
//                eachpost.setUser_photo(resultSet.getString(2));
//                eachpost.setUser(resultSet.getString(3));
//                eachpost.setTitle(resultSet.getString(4));
//                eachpost.setInvitationid(resultSet.getInt(5));
//                eachpost.setPosttime(resultSet.getTimestamp(6));
//                posts.add(eachpost);
//            }
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
