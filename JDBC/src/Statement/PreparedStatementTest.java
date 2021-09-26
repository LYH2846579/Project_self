package Statement;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-26 20:25
 */
public class PreparedStatementTest
{
    @Test
    public void test()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            //从配置文件读取所需信息并建立连接
            Properties pros = new Properties();
            FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");
            pros.load(fis);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String drive = pros.getProperty("driverClass");

            Class.forName(drive);

            connection = DriverManager.getConnection(url, user, password);
            //System.out.println(connection);

            //连接成功之后，创建preparedStatement对象实现对数据库的操纵
            //1.创建SQL语句(使用占位符)
            String sql = "Insert into stuinfo (id,name) values(?,?);";
            ps = connection.prepareStatement(sql);
            //2.设置占位符   -> 从1开始     //一定要按类型进行设置
            ps.setInt(1,5);
            ps.setString(2,"Jack");

            //执行语句
            ps.execute();
        /*
        若出现日期进行如下操作
        //1.使用SimpleDateFormat对需要转换的数据格式进行转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //2.将指定的数据模式转换为java.util.Date类型
        Date parse = sdf.parse("1000-01-01");
        //3.将java.util.Date转换为java.sql.Date
        java.sql.Date date = new java.sql.Date(parse.getTime());
        //4.将date传入参数中,插入数据库中即可
        ps.setString(n,"***");
         */
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //资源关闭
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
                if(connection != null)
                    connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
