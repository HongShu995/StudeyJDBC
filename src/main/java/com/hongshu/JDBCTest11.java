package com.hongshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * sql脚本:
 *  drop table if exists t_act;
 *      create table t_act(
 *      actno int,
 *      balance double(7,2)  //注意:7表示有效数字的个数，2z表示小数位的个数。
 *  );
 *  insert into t_act(actno,balance) values (111,20000) ;
 *  insert into t_act(actno,balance) values (222,0) ;
 *  commit;
 *  select * from t_act;
 */
public class JDBCTest11
{
    public static void main(String[] args)
    {
        Connection conn =null;
        PreparedStatement ps =null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","526239976");
            //将自动提交机制更改为手动提交
            conn.setAutoCommit(false);
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();
//            String s = null;
//            s.toString();
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();
            System.out.println(count == 2 ? "转账成功":"转账失败");
            //程序能走到这里说明程序没有异常，事务结束，手动提交数据
            conn.commit();
        } catch (ClassNotFoundException | SQLException e)
        {   //回滚事务
            if(conn !=null)
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
