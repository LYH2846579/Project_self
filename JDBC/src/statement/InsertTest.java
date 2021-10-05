package statement;

import org.junit.Test;
import uitls.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author LYHstart
 * @create 2021-10-05 18:47
 *
 * 实现批量插入
 */
public class InsertTest
{
    @Test
    public void testInsert1()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO GOODS(NAME) VALUES(?);";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++)
            {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testInsert2()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO GOODS(NAME) VALUES(?);";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i <= 2000; i++)
            {
                ps.setObject(1, "name_" + i);
                //1.“攒”SQL语句
                ps.addBatch();
                if (i % 500 == 0)
                {
                    //执行SQL
                    ps.executeBatch();
                    //清空缓冲区
                    ps.clearBatch();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection, ps);
        }
    }
}
