package com.hongshu;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest05
{
    public static void main(String[] args)
    {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "select empno,ename,sal from emp";
            //专门执行DQL语句的方法
            //int executeUpdate (insert/delete/ update)
            //ResultSet executeQuery (select)
            rs = statement.executeQuery(sql);
            //处理结果集
            //getString不管数据库中的数据类型是什么都以String的类型取出
            //JDBC中所有下标从1开始。不是从o开始。string ename = rs.getstring (2);
            while (rs.next())
            {
                //String empno = rs.getString(1);
                //String ename = rs.getString(2);
                //String sal = rs.getString (3);
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");
                String sal = rs.getString ("sal");
                System.out.println (empno + " ," +ename + ", " + sal);
            }
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }finally
        {
            if (statement != null)
            {
                try
                {
                    statement.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if (conn != null)
            {
                try
                {
                    conn.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
