package dao.Plus;

import bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-10-06 19:47
 *
 * 此接口用于规范针对于Customers表的常用操作
 */
public interface CustomerDAO
{
    /**
     * @Description 将cust对象添加到数据库中
     * @param connection
     * @param cust
     */
    void insert(Connection connection, Customer cust);

    /**
     * @Description 针对指定的id,删除表中的一条记录
     * @param connection
     * @param id
     */
    void deleteById(Connection connection, int id);

    /**
     * @Description 针对于内存中的cust对象，去修改数据表中指定的记录
     * @param connection
     * @param cust
     */
    void update(Connection connection, Customer cust);
    //以上，创建新的对象将原有对象数据进行修改

    /**
     * @Description 针对指定的id得到对应的Customer对象
     * @param connection
     * @param id
     */
    Customer getCustomerById(Connection connection, int id);

    /**
     * @Description 查询表中的所有记录构成的集合
     * @param connection
     * @return
     */
    LinkedList<Customer> getAll(Connection connection);

    /**
     * @Description 返回数据表中数据的条目数
     * @param connection
     * @return
     */
    Long getCount(Connection connection);

    /**
     * @Description 返回数据表中最大的birth
     * @param connection
     * @return
     */
    Date getMaxBirth(Connection connection);
}
