package statement;

import bean.S;
import bean.StudInfo;
import org.junit.Test;
import sun.plugin.dom.html.ns4.NS4DOMObject;
import uitls.JDBCUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-30 18:14
 *
 * 实现针对于不同表的通用查询操作
 */
public class PreparedStatementQueryTest
{
    @Test
    public void test()
    {
        String sql = "SELECT * FROM StuInfo WHERE ID = ?;";
        int id = 5;
        StudInfo instance = getInstance(StudInfo.class, sql,id);
        System.out.println(instance.toString());
    }

    //泛型方法
    public <T> T getInstance(Class<T> clazz,String sql,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            connection = JDBCUtils.getConnection();

            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();
            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取列数
            int columnCount = rsmd.getColumnCount();

            if(rs.next())
            {
                T t = clazz.newInstance();
                //处理结果集的一行数据中的每一列
                for (int i = 0; i < columnCount; i++)
                {
                    //获取列值
                    Object object = rs.getObject(i + 1);
                    //获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射同态设置属性值
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                return t;
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            JDBCUtils.closeResource(connection,ps,rs);
        }

        return null;
    }

    //对以上是针对不同表的查询操作进行复习 -> 这里采取获取多个元素的方式(不使用工具包)
    @Test
    public void test1()
    {
        String sql = "SELECT * FROM STUINFO;";
        LinkedList<StudInfo> query = Query(sql, StudInfo.class);
        for(StudInfo s:query)
            System.out.println(s);
        System.out.println("***********");
        //另外一种遍历方式
        query.forEach(System.out::println);
        System.out.println("***********");
        //再另外一种遍历方式
        query.forEach((studInfo) -> {
            System.out.println(studInfo.toString());
        });

        //查询另外一个表格
        System.out.println("*****************");   //类属性与SQL中的列名不同一定要使用别名(别名一定和属性名完全相同!)
        String sql1 = "SELECT SNO id,SNAME name,SAGE age FROM S;";
        LinkedList<S> query1 = Query(sql1,S.class);
        query1.forEach(System.out::println);
    }
    public <T> LinkedList<T> Query(String sql,Class<T> clazz,Object ... args)
    {
        File file = null;
        FileInputStream fis = null;
        Properties pros = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            //1.加载配置信息
            file = new File("F:\\Java\\Project_self\\JDBC\\src\\jdbc.properties");
            fis = new FileInputStream(file);
            pros = new Properties();
            pros.load(fis);
            //2.读取有效信息
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");
            //3.建立连接 -> 加载类
            Class.forName(driverClass);
            //获取连接  ->  DriverManager在类加载之后自动获取连接对象
            connection = DriverManager.getConnection(url, user, password);

            //获取ps对象
            ps = connection.prepareStatement(sql);
            //设置占位符
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            //执行sql
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            //创建查询结果队列
            LinkedList<T> list = new LinkedList<>();
            while(rs.next())
            {
                //构造一个对象 -> 反射
                T t = clazz.newInstance();
                //进入属性赋值循环
                for (int i = 0; i < columnCount; i++)
                {
                    //获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //获取属性值
                    Object object = rs.getObject(i + 1);
                    //使用反射赋值    //这里一定使用t.getClass()※!!!
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                //将该对象加入队列
                list.offerLast(t);
            }
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(fis != null)
                    fis.close();
            } catch (IOException e)
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
        return null;
    }
}
