package database_connection_pool;

import bean.Customer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-07 11:13
 *
 * 对C3P0数据库连接池的学习测试
 */
public class C3P0Test
{
    @Test   //获取连接方式一
    public void test1() throws Exception
    {
        //获取C3P0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("3306");

        //通过设置相关的参数，对数据库连接池进行管理
        //设置初始时数据库连接池中的连接数
        cpds.setIdleConnectionTestPeriod(10);

        Connection connection = cpds.getConnection();
        System.out.println(connection);

        //销毁连接池
        //DataSources.destroy(cpds);
    }

    @Test   //使用配置文件获取连接    XML文件配置
    public void test2() throws SQLException
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }

    //使用c3p0获取数据库连接
    @Test
    public void getConnection()
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        try
        {
            Connection connection = cpds.getConnection();
            String sql = "SELECT * FROM USER_TABLE";
            CustomerDAOImpl c = new CustomerDAOImpl();
            LinkedList<Customer> all = c.getAll(connection);
            for(Customer cust:all)
                System.out.println(cust);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}


//以下为c3p0数据库连接池的创建及连接获取
class c3p0Utils
{
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = cpds.getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
