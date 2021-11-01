package com.hongshu.utils;

import java.sql.*;
import java.util.Collection;

//JDBC工具类，简化jdbc编程
public class DBUtil
{
    //工具类中的构造方法都是私有的。
    //因为工具类当中的方法都是静态的，不需要new对象，直接采用类名调用
    private DBUtil(){}

    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 数据库连接对象
     * @throws SQLException 抛异常
     */
    public static Connection getConnection() throws SQLException
    {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param stmt 操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs)
    {
        if(conn!=null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(stmt!=null)
        {
            try
            {
                stmt.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(rs!=null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
