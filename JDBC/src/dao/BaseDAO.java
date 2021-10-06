package dao;

import bean.User;
import org.junit.Test;
import uitls.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-10-06 18:56
 *
 * 实现对基本表的增删改查操作 -> version2.0考虑事务
 * 一定不要关闭参数传入的Connection!
 * 抽象方法
 */
public abstract class BaseDAO
{
    @Test
    public void test1()
    {
        Connection connection = null;
        try
        {
            connection = JDBCUtils.getConnection();
            //String sql = "select * from user_table";
//            User instance = getInstance(connection, sql, User.class);
//            System.out.println(instance);
//            LinkedList<User> query = query(connection, sql, User.class);
//            for(User u:query)
//                System.out.println(u);
//            String sql1 = "update user_table set balance = 1000 where user = ?";
//            update(connection,sql1,"BB");
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }


    //更新操作
    public void update(Connection connection,String sql,Object ... args)
    {
        PreparedStatement ps = null;
        try
        {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,ps);
        }
    }

    //查询一条记录
    public <T> T getInstance(Connection connection,String sql,Class<T> clazz,Object ... args)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();
            //处理结果集
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if(rs.next())
            {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++)
                {
                    Object object = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                return t;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }  finally
        {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }

    //查询多条记录
    public <T> LinkedList<T> query(Connection connection,String sql,Class<T> clazz,Object ... args)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            LinkedList<T> list = new LinkedList<>();
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
                }
                list.offerLast(t);
            }
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        }  finally
        {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }

    //针对于一些特殊函数构建的查询方式
    public <E> E getValue(Connection connection,String sql,Object ... args)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);

            }
            rs = ps.executeQuery();
            if(rs.next())
                return (E) rs.getObject(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
}
