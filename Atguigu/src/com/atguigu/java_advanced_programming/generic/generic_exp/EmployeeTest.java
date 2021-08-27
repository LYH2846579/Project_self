package com.atguigu.java_advanced_programming.generic.generic_exp;

import java.util.*;

/**
 * @author LYHstart
 * @create 2021-08-27 18:11
 */
public class EmployeeTest
{
    public static void main(String[] args)
    {
        Employee e1 = new Employee("张三",17,new MyDate(2001,8,4));
        Employee e2 = new Employee("李四",18,new MyDate(2000,8,4));
        Employee e3 = new Employee("王五",19,new MyDate(1999,8,4));
        Employee e4 = new Employee("赵六",20,new MyDate(2011,8,4));

        //泛型
        List<Employee> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        //set
        Set<Employee> set = new TreeSet<>(new Comparator<Employee>()
        {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);

        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println("****************************");

        //Iterator遍历TreeSet
        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next().toString());
        }

    }
}
