package com.admin.query;

import com.admin.instantiation.AdminIsExistInstantiation;
import com.jdbc.JDBCutils;
import com.postapost.instantiation.EachPostInstantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class AdminIsExistQuery {
    public List queryisexist(String sql, Object... args) {
        Connection getconnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            getconnection = JDBCutils.getconnection();
            preparedStatement = getconnection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            List posts = new ArrayList();
            if (resultSet != null) {
                while (resultSet.next()) {
                    AdminIsExistInstantiation adminIsExistInstantiation = new AdminIsExistInstantiation();
                    adminIsExistInstantiation.setId(resultSet.getInt(1));
                    adminIsExistInstantiation.setUser(resultSet.getString(2));
                    adminIsExistInstantiation.setUser_photo(resultSet.getString(3));
                    posts.add(adminIsExistInstantiation);
                }
            }
            return posts;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCutils.closeResource(getconnection, preparedStatement, resultSet);
        }
        return null;
    }
}
