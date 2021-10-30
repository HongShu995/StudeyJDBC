package com.hongshu;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest04
{
    public static void main(String[] args)
    {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        //使用资源绑定器绑定属性配置文件
        Connection conn = null;
        Statement statement = null;
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            statement = conn.createStatement();
            String sql = "update dept set dname = '销售部2',loc = '天津2' where deptno = 20";
            int count = statement.executeUpdate(sql);
            System.out.println(count==1 ? "修改成功":"修改失败");
        } catch (SQLException | ClassNotFoundException e)
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
