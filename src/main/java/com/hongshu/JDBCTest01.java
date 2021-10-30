package com.hongshu;

//JDBC编程六步
import java.sql.*;

public class JDBCTest01
{
    public static void main(String[] args)
    {
        Connection conn = null;
        Statement statement = null;
        try
        {
            //1、注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //2、获取连接
                //url：统一资源定位符
            String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
            String user = "root";
            String password = "526239976";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象===="+conn);
            //3、获取数据库操作对象(Statement专门执行sql语句的)
            statement = conn.createStatement();
            //4、执行sq1
            String sql = "insert into dept values(50,'人事部','北京')";
                //专门执行DML语句的（insert delete update）
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1 ? "插入成功":"插入失败");
            //5、处理查询结果集
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {   //6、释放资源 为了保证资源一定释放，并且遵循从小到大依次关闭
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
