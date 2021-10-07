package database_connection_pool;

import bean.Customer;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.CustomerDAOImpl;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-10-07 14:09
 */
public class DruitUtils
{
    //私有化属性
    private static DataSource source;

    //静态代码块创建数据库连接池
    static {
        Properties pro = new Properties();
        try
        {
            FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\druid.properties");
            pro.load(fis);
            source = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception
    {
        Connection connection = source.getConnection();
        return connection;
    }

    @Test
    public void test() throws Exception
    {
        Connection connection = DruitUtils.getConnection();
        String sql = "SELECT * FROM USER_TABLE;";
        CustomerDAOImpl c = new CustomerDAOImpl();
        LinkedList<Customer> all = c.getAll(connection);
        for(Customer cust:all)
            System.out.println(cust);
    }
}
