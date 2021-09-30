package statement;

import bean.StudInfo;
import org.junit.Test;
import sun.plugin.dom.html.ns4.NS4DOMObject;
import uitls.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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
}
