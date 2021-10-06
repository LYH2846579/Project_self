package dao;

import bean.Customer;
import org.junit.Test;
import uitls.JDBCUtils;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-06 20:19
 *
 * 针对于CustomerDAOImpl的测试
 */
public class CustTest
{
    @Test
    public void test() throws Exception
    {
        Connection connection = JDBCUtils.getConnection();
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        //1.查询行数
        //Long count = customerDAO.getCount(connection);
        //System.out.println(count);

        //2.查询最大生日
        //Date maxBirth = customerDAO.getMaxBirth(connection);
        //System.out.println(maxBirth);

        //3.插入一条数据
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date parse = sdf.parse("1975-08-09");
        //java.sql.Date date = new java.sql.Date(parse.getTime());
        //Customer c = new Customer(17,"立即寻址","soihd@qq.com",date);
        //customerDAO.insert(connection,c);

        //4.根据id修改属性
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date parse = sdf.parse("1975-08-08");
        //java.sql.Date date = new java.sql.Date(parse.getTime());
        //Customer c = new Customer(17,"立即寻址","soihd@qq.com",date);
        //customerDAO.update(connection,c);

        //5.根据指定的id获取到对象
        //Customer customerById = customerDAO.getCustomerById(connection, 17);
        //System.out.println(customerById);

        //6.获取所有对象
        //LinkedList<Customer> all = customerDAO.getAll(connection);
        //for(Customer c:all)
        //    System.out.println(c);

        //7.根据id删除
        //customerDAO.deleteById(connection,17);
    }
}
