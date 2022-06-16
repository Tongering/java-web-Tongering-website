package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class updata {
    public void updateutil(String sql, Object ...args){
        Connection getconnection = null;
        PreparedStatement preparedStatement = null;
        try {
            getconnection = JDBCutils.getconnection();
            preparedStatement = getconnection.prepareStatement(sql);

            for(int i = 0; i < args.length; i++){
                preparedStatement.setObject(i+1,args[i]);
            }

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCutils.closeResource(getconnection,preparedStatement);
        }

    }
}
