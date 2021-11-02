package com.hongshu;

import com.hongshu.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个程序开启一个事务，这个事务专门进行查询，并且使用行级锁/悲观锁，锁住相关的记录。
 */
public class JDBCTest13
{
    public static void main(String[] args)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "select ename,job,sal from emp where job = ? for update ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            rs = ps.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getString("ename")+" , "+rs.getString("job")+" , "+rs.getDouble("sal"));
            }
            conn.commit();

        } catch (Exception e)
        {
            if(conn!=null)
            {
                try
                {
                    conn.rollback();
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally
        {
            DBUtil.close(conn,ps,rs);
        }
    }
}
