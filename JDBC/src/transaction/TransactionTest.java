package transaction;

import bean.User;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;
import uitls.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-06 10:06
 *
 * 对数据库事务进行学习
 * 外部传入Connection+关闭自动提交
 */
public class TransactionTest
{
    @Test
    public void test()
    {
        //获取连接
        Connection connection = null;
        try
        {
            connection = JDBCUtils.getConnection();
            System.out.println(connection.getAutoCommit());
            //关闭自动提交
            connection.setAutoCommit(false);

            String sql1 = "UPDATE user_table set balance = balance - 100 where user = ?;";
            update(connection,sql1,"AA");

            //模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "UPDATE user_table set balance = balance + 100 where user = ?;";
            update(connection,sql2,"BB");

            //提交操作
            connection.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            //回滚操作
            try
            {
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        } finally
        {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //通用的增删改操作
    public int update(Connection connection,String sql,Object ... args)
    {
        PreparedStatement ps = null;
        try
        {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,ps);
        }
        return 0;
    }


    //*************************************************************************************************
    //JAVA代码演示并设置数据库的隔离级别
    @Test   //对数据库的内容进行查询
    public void testTransactionSelect()
    {
        String sql = "SELECT * FROM user_table where user = ?;";
        LinkedList<User> query = query(sql, User.class, "CC");
        for(User u:query)
            System.out.println(u);

        //获取当前数据库的隔离级别
        try
        {
            Connection connection = JDBCUtils.getConnection();
            //获取当前级别
            System.out.println(connection.getTransactionIsolation());
            //设置当前级别
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test   //对数据库的内容更新
    public void testTransactionUpdate()
    {
        Connection connection = null;
        try
        {
            //关闭自动提交
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql = "UPDATE user_table set balance = 5000 WHERE user = ?;";
            update(connection,sql,"CC");

            //睡眠15s
            Thread.sleep(15000);
            //提交
            connection.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
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
    //通用的查询操作 -> 实现构造好User类
    public <T> LinkedList<T> query(String sql,Class<T> clazz,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            //获取连接
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();

            //处理查询结果
            LinkedList<T> list = new LinkedList<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next())
            {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++)
                {
                    Object object = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                    int j = 0;
                }
                list.offerLast(t);
            }
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps,rs);
        }
        return null;
    }
}
