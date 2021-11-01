package com.hongshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//JDBC事务：自动提交 只要执行任意一条DML语句，则自动提交以此，这是JDBC默认的事务行为。
//但是在实际的业务当中,通常都是N标DM语句共同联合才能完成的，必须保证他们这些DML语句在同一个事务中同时成功或者同时失败。
//以下程序先来验证一下JDBc的事务是否是自动提交机制!
public class JDBCTest10
{
    public static void main(String[] args)
    {
        Connection conn =null;
        PreparedStatement ps =null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
            String sql = "update dept set dname = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            //第一次给展位符传值
            ps.setString(1,"X部门");
            ps.setInt(2,30);
            int count = ps.executeUpdate();
            System.out.println(count);
            //重新给占位符传值
            ps.setString(1,"Y部门");
            ps.setInt(2,20);
            count = ps.executeUpdate();
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
