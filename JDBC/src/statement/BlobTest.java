package statement;

import bean.Customer;
import org.junit.Test;
import uitls.JDBCUtils;

import java.io.*;
import java.sql.*;

/**
 * @author LYHstart
 * @create 2021-10-05 16:24
 *
 * 测试使用PreparedStatement操作Blob类型的数据
 */
public class BlobTest
{
    @Test   //向数据表customers中插入Blob类型的字段
    public void test1() throws Exception
    {
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO customers(name,email,birth,photo) values(?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1,"张宇豪");
        ps.setObject(2,"12345@qq.com");
        ps.setObject(3,"1992-08-09");
        FileInputStream fis = new FileInputStream(new File("F:\\Java\\Project_self\\JDBC\\src\\statement\\-222d8cd663b7e260.jpg"));
        ps.setBlob(4,fis);

        ps.execute();

        JDBCUtils.closeResource(connection,ps);
    }

    //读取Blob字段
    @Test
    public void test2()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM CUSTOMERS WHERE ID = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,24);
            rs = ps.executeQuery();

            if(rs.next())
            {
                int id  =rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer c = new Customer(id,name,email,birth);
                System.out.println(c);

                //将Blob类型的变量存储到本地
                Blob photo = rs.getBlob("photo");
                fis = photo.getBinaryStream();
                fos = new FileOutputStream("F:\\Java\\Project_self\\JDBC\\src\\statement\\张宇豪.jpg");
                byte[] bbuf = new byte[1024];
                int read = 0;
                while((read = fis.read(bbuf)) != -1)
                {
                    fos.write(bbuf,0,read);
                }

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps,rs);
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
                if(fos != null)
                    fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //插入和读取Blob字段
    @Test
    public void test3()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO CUSTOMERS(id,name,email,birth,photo) VALUES(?,?,?,?,?);";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,21);
            ps.setObject(2,"张三丰");
            ps.setObject(3,"123456@qq.com");
            ps.setObject(4,"2020-07-31");
            fis = new FileInputStream("F:\\Java\\Project_self\\JDBC\\src\\statement\\Mario.gif");
            ps.setBlob(5,fis);

            ps.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps);
            try
            {
                if(fis != null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test4()
    {
        Connection connection = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT id,name,email,birth,photo FROM CUSTOMERS WHERE ID = ?;";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,21);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");
                Customer c = new Customer(id,name,email,birth);
                System.out.println(c);
            }

            //接收Blob资源
            Blob photo = rs.getBlob("photo");
            //加载输入流资源
            InputStream is = photo.getBinaryStream();
            //加载输出流资源
            FileOutputStream fos = new FileOutputStream("zhangsanfeng.gif");
            //开始读取资源
            byte[] bbuf = new byte[1024];
            int read = 0;
            while((read = is.read(bbuf)) != -1)
            {
                fos.write(bbuf,0,read);
            }

            ps.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps);
            try
            {
                if(fis != null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
