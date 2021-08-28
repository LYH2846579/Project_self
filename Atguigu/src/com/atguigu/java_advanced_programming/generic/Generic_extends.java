package com.atguigu.java_advanced_programming.generic;

import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.*;

/**
 * @author LYHstart
 * @create 2021-08-28 9:13
 *
 * 1.泛型在继承方面的体现
 *
 * 2.通配符的使用
 */
public class Generic_extends
{
    /*
    1、泛型在继承方面的体现
        虽然类A是类B的父类，但是G<A>和G<B>二者不具备子父类关系，二者是并列关系。
        补充:类A是类B的父类，A<G>是B<G>的父类
     */
    @Test
    public void test1()
    {
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //编译不通过
        //Date date = new Date();
        //str = date;     //Incompatible types.

        List<Object> list1 = null;
        List<String> list2 = null;

        //此时list1和list2的类型不具有子父类的关系
        //编译不通过
        //list1 = list2;    //Incompatible types.
        /*
        不妨设可以赋值：
            list1 = list2;
            list1.add(123);导致混入非String的数据。出错。
         */

    }

    @Test
    public void test2()
    {
        //A<G> 和 B<G>的关系
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;
    }

    /*
    通配符的使用:?

    类A是类B的父类，G<A>和G<B>是没有关系的，二者的共同父类是:G<?>
     */
    @Test
    public void test3()
    {
        //通配符的使用
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;

        //编译通过
        //print(list1);
        //print(list2);

        //list<?> 的有关操作
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据
        //除了添加null之外
        //list.add("AA");
        //list.add('?');
        list.add(null);

        //获取(读取)：允许读数据，读取的数据类型为Object.
        Object o = list.get(0);
        System.out.println(o);
    }

    /*
    有限制的通配符
        -> 根据集合区间论进行匹配
     */
    @Test
    public void test4()
    {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();

        list1 = list3;
        list1 = list4;
        //编译不通过
        //list1 = list5;

        ///编译不通过
        //list2 = list3;
        list2 = list4;
        list2 = list5;

        //读数据
        list1 = list3;
        Person p = list1.get(0);
        ///编译不通过
        //Student s = list1.get(0);         //不可以使用小范围!

        //读数据
        list2 = list5;
        Object o = list5.get(0);

        //写数据 -> 无法写入数据
        //编译不通过
        //list1.add(new Person());

        //写数据
        //编译不通过
        //list2.add(new Object());
        //编译通过
        list2.add(new Person());
        list2.add(new Student());
    }

    public void print(List<?> list)
    {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext())
        {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }
}
