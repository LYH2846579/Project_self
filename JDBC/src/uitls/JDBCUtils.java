package uitls;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-27 14:28
 *
 * 操作数据库的工具类
 */
public class JDBCUtils
{
    //获取数据库连接
    public static Connection getConnection() throws Exception
    {
        //读取配置文件
        FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");
        Properties pros = new Properties();
        pros.load(fis);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driver = pros.getProperty("driverClass");

        //加载驱动
        Class.forName(driver);

        //连接数据库
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    //关闭数据库连接 -> 资源关闭
    public static void closeResource(Connection connection, Statement ps)
    {
        try
        {
            if(connection != null)
                connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(ps != null)
                ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //关于查询语句的资源关闭
    public static void closeResource(Connection connection, Statement ps, ResultSet rs)
    {
        try
        {
            if(connection != null)
                connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(ps != null)
                ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(rs != null)
                rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
