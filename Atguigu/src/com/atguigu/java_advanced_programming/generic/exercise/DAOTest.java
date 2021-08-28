package com.atguigu.java_advanced_programming.generic.exercise;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-08-28 10:50
 */
public class DAOTest
{
    @Test
    public void test1()
    {
        DAO<User> dao = new DAO<>(new HashMap<>());

        dao.save("1001",new User(17,17,"Tom"));
        dao.save("1002",new User(27,27,"Jerry"));
        dao.save("1003",new User(37,37,"Jack"));

        //get()
        User user = dao.get("1001");
        System.out.println(user);

        //遍历
        System.out.println("************");
        List<User> list = dao.list();
        //第一种方式
        for(User u:list)
        {
            System.out.println(u);
        }
        System.out.println("*************");
        //第二种方式
        list.forEach(System.out::println);
    }
}
