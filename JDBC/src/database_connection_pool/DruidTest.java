package database_connection_pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;


/**
 * @author LYHstart
 * @create 2021-10-07 13:53
 *
 * Druid数据库连接池的使用练习
 */
public class DruidTest
{

    //获取连接
    @Test
    public void getConnection() throws Exception
    {
        //加载配置文件
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\druid.properties");
        pro.load(fis);
        //使用配置文件加载
        DataSource source = DruidDataSourceFactory.createDataSource(pro);
        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
