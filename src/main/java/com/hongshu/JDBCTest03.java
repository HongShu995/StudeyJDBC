package com.hongshu;

import java.sql.*;

//注册驱动的另一种方式
public class JDBCTest03
{
    public static void main(String[] args)
    {
        try
        {   //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // 注册驱动的第二种方式：常用的(参数是一个字符串，可以写到xxx.properties文件中)
            // 以下方法不用接收返回值，只需要用到类加载的动作
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
            System.out.println(conn);
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
