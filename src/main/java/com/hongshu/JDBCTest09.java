package com.hongshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//preparedStatement完成INSERT DELETE UPDATE
public class JDBCTest09
{
    public static void main(String[] args)
    {
        Connection conn =null;
        PreparedStatement ps =null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
            //增
//            String sql = "insert into dept (deptno,dname,loc) values (?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,60);
//            ps.setString(2,"销售部");
//            ps.setString(3,"上海");
            //改
//            String sql = "update dept set dname=?,loc=? where deptno=?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,"研发一部");
//            ps.setString(2,"北京");
//            ps.setInt(3,60);
            //删
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,60);
            int count = ps.executeUpdate();
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            if (ps != null)
            {
                try
                {
                    ps.close();
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
        }
    }
}
