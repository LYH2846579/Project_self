package statement;

import bean.StudInfo;
import uitls.JDBCUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-26 20:25
 *
 * 数据库的操作
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
            ps.setInt(1, 5);
            ps.setString(2, "Jack");

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
                if (ps != null)
                    ps.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            try
            {
                if (connection != null)
                    connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 调用工具类对数据库进行删除操作
     */
    @Test
    public void test2() throws Exception    //当然应该try-catch-finally一下
    {
        //获取连接
        Connection connection = JDBCUtils.getConnection();
        //sql语句+ps对象
        String sql = "DELETE FROM stuinfo WHERE id = ?;";
        //预编译sql
        PreparedStatement ps = connection.prepareStatement(sql);
        //填充占位符
        ps.setInt(1, 5);
        //执行sql
        ps.execute();
        //关闭资源
        JDBCUtils.closeResource(connection, ps);
    }

    @Test
    public void test3()
    {
        /*
        //增添操作
        String sql = "INSERT INTO STUINFO(ID,NAME) VALUES(?,?);";
        int id = 6;
        String name = "Tony";
        update(sql,id,name);

        //删除操作
        String sql = "DELETE FROM STUINFO WHERE ID = ?;";
        int id = 5;
        update(sql,id);
        */
        //修改操作
        String sql = "UPDATE STUINFO SET NAME = ? WHERE ID = ?;";
        String name = "Tony Stack";
        int id = 5;
        update(sql, name, id);
    }

    //采取可变形参的方式 -> 底层维护数组进行存储
    public void update(String sql, Object... args)
    {
        //6.异常处理
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i + 1, args[i]);          //切记SQL从1开始
            }
            //4.执行sql
            ps.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //5.资源关闭
            JDBCUtils.closeResource(connection, ps);
        }
    }

    /**
     * 实现数据库的查询操作
     */
    @Test
    public void test4()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            //1.建立连接
            connection = JDBCUtils.getConnection();
            //2.sql+预编译
            String sql = "SELECT * FROM STUINFO;";
            ps = connection.prepareStatement(sql);
            //3.执行s,获取数据集
            rs = ps.executeQuery();
            //4.遍历输出数据集
            while (rs.next())   //倘若存在下一行数据值
            {
                //获取数据
                int id = rs.getInt(1);
                String name = rs.getString(2);
                //构造StudInfo对象
                StudInfo info = new StudInfo(id, name);
                System.out.println(info);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection, ps, rs);
        }
    }

    //针对于stuInfo表的普遍查询方式
    @Test
    public void test5()
    {
        String sql = "SELECT * FROM STUINFO WHERE ID = ?;";
        int id = 5;
        getQuery(sql,id);
    }
    public void getQuery(String sql,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.预处理sql+获取ps对象
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            //4.执行语句部分
            rs = ps.executeQuery();
            //5.获取结果集的元数据   -> 获取结果集的列名
            ResultSetMetaData rsmd = rs.getMetaData();
            //6.获取列数
            int columnCount = rsmd.getColumnCount();
            //7.查询
            while(rs.next())
            {
                StudInfo s = new StudInfo();
                //遍历属性
                for (int i = 0; i < columnCount; i++)
                {
                    Object columnValue = rs.getObject(i+1);

                    //获取到对应的属性 -> 反射
                    String columnName = rsmd.getColumnName(i + 1);

                    //获取别名
                    //String columnLabel = rsmd.getColumnLabel(i + 1);
                    //System.out.println(columnLabel);

                    //获取到对应的属性
                    Field field = StudInfo.class.getDeclaredField(columnName);
                    //打开权限
                    field.setAccessible(true);
                    //给s对象的属性赋值
                    field.set(s,columnValue);
                }
                //输出
                System.out.println(s);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            //8.关闭资源
            JDBCUtils.closeResource(connection,ps,rs);
        }
    }

    @Test   //对表的通用查询进行复习       -> Successful
    public void test6()
    {
        String sql = "SELECT * FROM STUINFO WHERE ID > ?;";
        int id = 2;
        query(sql,id);
    }
    public void query(String sql,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            //获取连接
            connection = JDBCUtils.getConnection();
            //预处理sql
            ps = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }
            //执行sql
            rs = ps.executeQuery();
            //获取原数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            //开始输出查询结果
            while(rs.next())
            {
                //使用空参构造器创建对象
                StudInfo s = new StudInfo();
                //获取查询元素
                for (int i = 0; i < columnCount; i++)
                {
                    Object object = rs.getObject(i + 1);
                    //获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //使用反射赋值
                    Field field = StudInfo.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(s,object);
                }
                //将该结果输出
                System.out.println(s);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps,rs);
        }


    }
}
