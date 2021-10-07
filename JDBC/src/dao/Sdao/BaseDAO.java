package dao.Sdao;

import uitls.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-07 10:21
 *
 * 针对昨天的学习内容进行复习，写出对于S表的通用查询
 */
public abstract class BaseDAO
{
    //通用更新操作
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
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,ps);
        }
    }
    //获取一个对象
    public <T> T getInstance(Connection connection,Class<T> clazz,String sql,Object ... args)
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
    public <T> LinkedList<T> query(Connection connection,Class<T> clazz,String sql,Object ... args)
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
