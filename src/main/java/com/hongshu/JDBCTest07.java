package com.hongshu;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//解决SQL注入问题 用户提供的信息不参与SQL语句的编译过程
//PreparedStatement是属于预编译的数据库操作对象 原理是预先对SQL语句的框架进行编译，然后再给SQL语句传“值”。
public class JDBCTest07
{
    public static void main(String[] args)
    {
        //初始化一个界面
        Map<String,String> userLoginInfo = initUI();
        boolean loginSucess = login(userLoginInfo);
        if(loginSucess)
        {
            System.out.println("登录成功");
        }else
        {
            System.out.println("登录失败");
        }
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     *	初始化用户界面
     * 	@return 用户输入的用户名和密码等信息
     **/
    private static Map<String, String> initUI()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = s.nextLine();
        System.out.println("密码：");
        String loginPwd = s.nextLine();
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        return userLoginInfo;
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo)
    {	//JDBC代码
        Connection conn = null;
        PreparedStatement ps = null;//预编译的数据库操作对象
        ResultSet rs = null;
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        boolean loginSucess = false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
            //?是占位符,一个?接收一个值，不能用单引号括起来
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            //程序执行到此，会发送sql语句框子给DBMS，然后DBMS进行sql语句的预编译
            ps = conn.prepareStatement(sql);
            //给占位符？传值，第一个?下标是1，以此类推
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            rs = ps.executeQuery();
            if(rs.next())
            {
                loginSucess = true;
            }
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
        return loginSucess;
    }
}
