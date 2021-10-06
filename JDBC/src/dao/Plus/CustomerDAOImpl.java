package dao.Plus;

import bean.Customer;
import org.junit.Test;
import uitls.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-06 19:59
 *
 * 针对Customer表格的具体实现类 -> clazz升级版
 */
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO
{
    @Test   //测试改进之后的查询
    public void test() throws Exception
    {
        Connection connection = JDBCUtils.getConnection();
        CustomerDAOImpl c = new CustomerDAOImpl();
        LinkedList<Customer> all = c.getAll(connection);
        for(Customer cust:all)
            System.out.println(cust);
    }

    @Override
    public void insert(Connection connection, Customer cust) {
        String sql = "INSERT INTO Customers(id,name,email,birth) VALUES(?,?,?,?);";
        update(connection,sql,cust.getId(),cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "DELETE FROM CUSTOMERS WHERE ID = ?;";
        update(connection,sql,id);
    }

    @Override
    public void update(Connection connection, Customer cust) {
        String sql = "UPDATE CUSTOMERS SET NAME=?,EMAIL=?,BIRTH=? WHERE ID = ?;";
        update(connection,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        String sql = "SELECT id,name,email,birth FROM CUSTOMERS WHERE ID = ?;";
        Customer instance = getInstance(connection,sql,id);
        return instance;
    }

    @Override
    public LinkedList<Customer> getAll(Connection connection) {
        String sql = "SELECT id,name,email,birth FROM CUSTOMERS;";
        LinkedList<Customer> query = query(connection, sql);
        return query;
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "SELECT COUNT(*) FROM CUSTOMERS;";
        Object value = getValue(connection, sql);
        return (Long) value;
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "SELECT MAX(BIRTH) FROM CUSTOMERS;";
        Object value = getValue(connection, sql);
        return (Date) value;
    }
}
