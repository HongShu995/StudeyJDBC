package com.hongshu;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//模拟用户登录功能的实现
//程序运行的时候提供一个输入的入口，可以让用户输入用户名和密码，提交信息 java程序连接数据库验证用户名和密码是否合法
public class JDBCTest06
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
		Statement stmt = null;
		ResultSet rs = null;
		boolean loginSucess = false;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
			stmt = conn.createStatement();
			String sql = "select loginName,loginPwd from t_user where loginName = '"+userLoginInfo.get("loginName")+"' and loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				loginSucess = true;
			}
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
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
