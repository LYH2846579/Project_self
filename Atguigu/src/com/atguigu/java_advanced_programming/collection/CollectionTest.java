package com.atguigu.java_advanced_programming.collection;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-08-22 8:45
 *
 * 一、集合框架的概述
 *      1.集合、数组都是对多个数据进行存储操作的结构，简称ava容器。
 *      说明:此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储(.txt,.jpg,.avi...)
 *
 *      2.1数组在存储多个数据方面的特点:
 *          >—旦初始化以后，其长度就确定了。
 *          >数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了。
 *           比如: String[ ] arr;int[] arr1;object[] arr2;
 *      2.2数组在存储多个数据方面的缺点:
 *          >—旦初始化以后，其长度就不可修改。
 *          >数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。
 *          >获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用
 *          >数组存储数据的特点:有序、可重复。对于无序、不可重复的需求，不能满足。
 *
 *  二、集合框架
 *      l----Collection接口:单列集合，用来存储一个一个的对象
 *          /----List接口:存储有序的、可重复的数据。-->“动态”数组
 *              /----ArrayList、LinkedList、 Vector
 *
 *          /----set接口:存储无序的、不可重复的数据-->高中讲的"集合”
 *              /----HashSet、 LinkedHashSet、 TreeSet
 *
 *       /----Nap接口:双列集合，用来存储一对(key - value)一对的数据-->高中函数: y = f(x)
 *              /----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 */
public class CollectionTest
{
    //Collection常用方法使用
    @Test
    public void test1()
    {
        Collection coll = new ArrayList();

        //add()
        ((ArrayList) coll).add("123");
        ((ArrayList) coll).add(456);
        ((ArrayList) coll).add(new String("str"));

        //size()
        System.out.println(coll.size());

        //addAll()
        Collection coll1 = new ArrayList();
        ((ArrayList) coll1).add("789");
        ((ArrayList) coll1).add("CCC");
        ((ArrayList) coll).addAll(coll1);
        ((ArrayList) coll1).addAll(coll1);      //可以自己增加自己

        //toString()
        System.out.println(coll);
        System.out.println(coll1);

        //clear()
        coll1.clear();

        //isEmpty()
        System.out.println(coll1.isEmpty());                    //true

        //contains()
        System.out.println(coll.contains("789"));               //true
        System.out.println(coll.contains(new String("str")));   //true

        //containsAll()         //返回是否存在对应的序列   ->  一个一个比较?
        ((ArrayList) coll1).add("789");
        ((ArrayList) coll1).add("CCC");
        System.out.println(coll.containsAll(coll1));            //true
        System.out.println("**************************");
        //判断是否一个一个比较        是!
        Collection coll2 = new ArrayList();
        ((ArrayList) coll2).add(123);
        ((ArrayList) coll2).add(456);
        ((ArrayList) coll2).add(789);
        System.out.println(coll.containsAll(coll2));    //false
        System.out.println("**************************");

        //remove -> 通过equals()方法判断是否是要删除的元素，若存在则返回true并将原有元素删除
        System.out.println(coll1.remove("CCC"));        //在成功删除之后会返回true
        System.out.println(coll1);
        System.out.println(coll2);

        //retainAll()
        coll1.add(new Person("张三",18));     //添加匿名对象
        coll2.add(new Person("张三",18));     //添加匿名对象
        Person p = new Person("李四",19);     //实名对象  ->  可以在retainAll中保留下来
        ((ArrayList) coll1).add(p);
        ((ArrayList) coll2).add(p);
        System.out.println(coll2.retainAll(coll1));
        System.out.println(coll1);
        System.out.println(coll2);

        //输出
        System.out.println(coll);
        System.out.println(coll1);
        System.out.println(coll2);

        //contains增加
        System.out.println(coll1.contains(new Person("李四", 19)));   //false
        /*
        附：由于String重写了equal()方法，在contains()方法中可以输出true
           而在自定义Person类中没有重写equals()方法，所以输出为false
         */

        //equals()
        Collection coll3 = Arrays.asList(coll2);        //将coll2整体作为参数存储到coll3中
        System.out.println(coll3);
        System.out.println(coll2.equals(coll3));        //false
        //coll3.clear();                                //list不可以进行修改
        //((List) coll3).add(p);
        //System.out.println(coll2.equals(coll3));

        Collection coll4 = new ArrayList();
        ((ArrayList) coll4).add(p);
        System.out.println(coll2.equals(coll4));        //true

        //hashCode() -> 返回当前对象的哈希值
        System.out.println(coll.hashCode());            //-2124658075

        //集合 -> 数组 toArray()方法
        Object[] array = coll.toArray();                //用Object[]类型数组进行接收，输出为array地址值↓
        System.out.println(array);                      //[Ljava.lang.Object;@4ee285c6
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
        //数组 -> 集合
        String[] s = new String[]{"123","456"};
        List<String> strings = Arrays.asList(s);
        System.out.println(strings);

        List arr1 = Arrays.asList(new int[]{123,465});  //识别为一个对象
        System.out.println(arr1);
        System.out.println(arr1.size());    //1

        List arr2 = Arrays.asList(new Integer[]{123,456});  //两个对象
        System.out.println(arr2);
        System.out.println(arr2.size());    //2
    }

    @Test
    public void test2()
    {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }
}

class Person
{
    public String name;
    public int age;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
