package statement;

import bean.ExamStudent;
import org.junit.Test;
import uitls.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-10-05 14:16
 *
 * 使用JAVA数据库操作进行练习
 */
public class Practise
{
    @Test   //从控制台向数据库的表customer中插入一条数据
    public void test1()
    {
        String sql = "INSERT INTO CUSTOMERS(id,`name`,email,birth) VALUES(?,?,?,?);";
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入id:");
        int id = scan.nextInt();
        System.out.println("请输入name:");
        String name = scan.next();
        System.out.println("请输入email:");
        String email = scan.next();
        System.out.println("请输入birth:");
        String date = scan.next();
        //将String转换为date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date birth = null;
        try
        {
            Date parse = sdf.parse(date);
            //将util.date转换为sql.date
            birth = new java.sql.Date(parse.getTime());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        //调用CustomerInsert方法
        CustomerInsert(sql,id,name,email,birth);
    }
    public void CustomerInsert(String sql,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            //获取连接
            connection = JDBCUtils.getConnection();
            //获取ps  +   预处理sql
            ps = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }

            int j =  0;         //消除重复提醒

            //执行语句
            ps.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(connection,ps);
        }
    }

    @Test   //创建数据库表examstudent //一般不在java中创建
    public void test2()
    {
        //添加数据与上述类似，这里仅学习查询数据
        String sql = "SELECT * FROM EXAMSTUDENT;";
        LinkedList<ExamStudent> query = query(sql, ExamStudent.class);
        Scanner scan = new Scanner(System.in);
        System.out.println("请选择查询方式:");
        int select = scan.nextInt();
        ExamStudent temp = null;
        if(select == 1)
        {
            System.out.println("请输入身份证号:");
            String IDCard = scan.next();
            for(ExamStudent e:query)
            {
                if(e.getIDCard().equals(IDCard))
                {
                    temp = e;
                    break;
                }
            }
        }
        else if(select == 2)
        {
            System.out.println("请输入学生证号:");
            String ExamCard = scan.next();
            for(ExamStudent e:query)
            {
                if(e.getExamCard().equals(ExamCard))
                {
                    temp = e;
                    break;
                }
            }
        }
        else
            System.out.println("方式错误!");

        if(temp == null)
        {
            System.out.println("无该条记录");
        }
        else
            System.out.println(temp);

    }
    private void upDate(String sql,Object ... args) throws Exception
    {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++)
        {
            ps.setObject(i+1,args[i]);
        }
        ps.executeUpdate();
    }
    private <T> LinkedList<T> query(String sql,Class<T> clazz,Object ... args)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
            {
                ps.setObject(i+1,args[i]);
            }
            ResultSet rs = ps.executeQuery();
            //整理结果集
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            LinkedList<T> list = new LinkedList<>();
            while(rs.next())
            {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++)
                {
                    //消除重复提示
                    int k = 0;
                    //获取属性值
                    Object object = rs.getObject(i + 1);
                    //获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //设置属性值
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
        } finally
        {
            JDBCUtils.closeResource(connection,ps);
        }
        return null;
    }


}
