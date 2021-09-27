package connection;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-26 16:55
 *
 * 数据库连接测试  以MySQL为例
 */
public class ConnectionTest
{
    @Test   //连接方式一
    public void test1() throws SQLException
    {
        //添加MySQL驱动     以多态形式实现
        Driver driver = new com.mysql.jdbc.Driver();

        //创建连接统一资源定位符   协议://IP地址:端口号/数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //用户名+密码
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","3306");

        Connection connect = driver.connect(url, info);

        System.out.println(connect);    //com.mysql.jdbc.JDBC4Connection@63e31ee 数据库连接成功!
    }

    @Test   //连接方式二  ->  使用反射的方式获取驱动
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        //获取Driver实现类对象，使用反射
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //创建连接URL
        String url = "jdbc:mysql://localhost:3306/test01";
        //创建连接properties
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","3306");

        //连接数据库
        Connection connection = driver.connect(url,info);
        System.out.println(connection);
    }

    @Test   //方式三：使用DriverManager替换Driver
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        //获取Driver的实现类对象
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //登录需要的信息
        String url = "jdbc:mysql://localhost:3306/test01";
        String user = "root";
        String password = "3306";

        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test   //更加优化的一种方式 ->  MySQL会协助进行优化
    public void test4() throws ClassNotFoundException, SQLException
    {
        //1.需要的登录信息
        String url = "jdbc:mysql://localhost:3306/test01";
        String user = "root";
        String password = "3306";

        //2.驱动加载
        Class.forName("com.mysql.jdbc.Driver");

        //3.连接数据库
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test   //以一种更加优美的方式进行实现   -> 通过加载配置文件的形式进行实现
    public void test5() throws IOException, ClassNotFoundException, SQLException
    {
        //读取配置文件中的基本信息

        //创建properties对象
        Properties pros = new Properties();
        //获取类的加载器读取配置文件     //获取系统加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        //InputStream is = loader.getResourceAsStream("F:\\Java\\Project_self\\JDBC\\src\\connection\\jdbc.properties");
        //InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");
        //pros.load(is);
        //以上三种方式均会报空指针异常!
        FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");
        pros.load(fis);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String driver = pros.getProperty("driverClass");
        String url = pros.getProperty("url");

        //加载驱动
        Class.forName(driver);

        //连接数据库
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void test()
    {
        //文件存在性测试       ->  确实存在!
        File file = new File("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");

        System.out.println(file.exists());
    }
}
