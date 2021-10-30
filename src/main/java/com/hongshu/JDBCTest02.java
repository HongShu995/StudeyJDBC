package com.hongshu;

import java.sql.*;

public class JDBCTest02
{
    public static void main(String[] args)
    {
        Connection conn = null;
        Statement statement = null;
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
            String user = "root";
            String password = "526239976";
            conn = DriverManager.getConnection(url,user,password);
            statement = conn.createStatement();
            String sql = "delete from dept where deptno = 50";
            int count = statement.executeUpdate(sql);
            System.out.println(count==1 ? "删除成功":"删除失败");
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if(statement!=null)
            {
                try
                {
                    statement.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
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
        }
    }
}
