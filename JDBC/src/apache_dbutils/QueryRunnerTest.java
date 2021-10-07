package apache_dbutils;

import bean.Customer;
import database_connection_pool.DruitUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import uitls.JDBCUtils;

import java.sql.*;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-10-07 14:20
 *
 * commons-dbutils 是Apache组织提供的一个开源JDBC工具库类,封装了针对于数据库的增删改查操作
 */
public class QueryRunnerTest
{
    @Test
    public void testInsert()
    {
        QueryRunner runner = new QueryRunner();

        Connection connection = null;
        try
        {
            connection = DruitUtils.getConnection();
            String sql = "INSERT INTO CUSTOMERS(NAME,EMAIL,BIRTH) VALUES(?,?,?);";
            int updateNum = runner.update(connection, sql, "蔡徐坤", "caixukun@126.com", "1997-07-02");
            System.out.println("添加了"+updateNum+"条记录");
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //测试查询  -> 需要使用ResultSetHandler接口的具体实现类的对象
    @Test
    public void testQuery1()
    {
        Connection connection = null;
        try
        {
            QueryRunner runner = new QueryRunner();
            connection = DruitUtils.getConnection();
            String sql = "SELECT ID,NAME,EMAIL,BIRTH FROM CUSTOMERS WHERE ID = ?;";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer query = runner.query(connection, sql, handler, 21);
            System.out.println(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //多条记录查询
    @Test
    public void testQuery2()
    {
        Connection connection = null;
        try
        {
            //1.实例化QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //2.获取连接
            connection = DruitUtils.getConnection();
            //3.sql语句
            String sql = "SELECT ID,NAME,EMAIL,BIRTH FROM CUSTOMERS;";
            //4.查询结果集处理对象
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            //5.执行查询
            List<Customer> list = runner.query(connection, sql, handler);

            //遍历方式1
            //for(Customer c:list)
            //    System.out.println(c);

            //遍历方式2
            //list.forEach(System.out::println);

            //遍历方式3
            list.forEach(customer -> {
                System.out.println(customer);
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //特殊记录查询ScalarHandler
    @Test
    public void testQuery3() throws Exception
    {
        //1.
        Connection connection = DruitUtils.getConnection();
        //2.
        QueryRunner runner = new QueryRunner();
        //3.
        String sql = "SELECT COUNT(*) FROM CUSTOMERS;";
        //4.
        ScalarHandler handler = new ScalarHandler();
        //5.
        Object query = runner.query(connection, sql, handler);
        System.out.println(query);
    }

    //自己手写一个ResultSetHandler的实现类
    @Test
    public void testQuery4() throws Exception
    {
        //1.
        Connection connection = DruitUtils.getConnection();
        //2.
        QueryRunner runner = new QueryRunner();
        //3.
        String sql = "SELECT ID,NAME,EMAIL,BIRTH FROM CUSTOMERS WHERE ID = ?;";
        //4.
        ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>()
        {
            @Override
            public Customer handle(ResultSet rs) throws SQLException {
                //和之前手写的处理结果集的方式类似
                if(rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date birth = rs.getDate("birth");
                    return new Customer(id,name,email,birth);
                }
                return null;
            }
        };
        //5.
        Customer query = runner.query(connection, sql, handler,21);
        System.out.println(query);
    }

    //资源关闭  DBUtils
    public void closeConnection(Connection connection, PreparedStatement ps,ResultSet rs)
    {
        try
        {
            DbUtils.close(connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            DbUtils.close(ps);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            DbUtils.close(rs);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        //悄悄的关闭~
        DbUtils.closeQuietly(rs);
    }
}
