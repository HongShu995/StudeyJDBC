package com.hongshu;


import com.hongshu.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//1.测试DBUtil是否好用
//2.编写模糊查询
public class JDBCTest12
{
    public static void main(String[] args)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {   //获取连接
            conn = DBUtil.getConnection();
            /* 错误写法
            String sql = "select ename from emp where ename like '_?%'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");
             */
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");
            rs = ps.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getString("ename"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {   //释放资源
            DBUtil.close(conn,ps,rs);
        }
    }
}
