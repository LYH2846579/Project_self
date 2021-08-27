package com.atguigu.java_advanced_programming.generic;

import org.junit.Test;

import java.util.*;

/**
 * 泛型编程
 *
 * 泛型的使用
 * 1.jdk 5.日新增的特性
 * 2.在集合中使用泛型:
 *  总结:
 *  ①集合接口或集合类在jdk5.日时都修改为带泛型的结构。
 *  ②在实例化集合类时，可以指明具体的泛型类型
 *  ③指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如:方法、构造器、属性等）使用到泛型的位置，都指定为实例化的泛型类型
 *      比如: add(E e)--->实例化以后: add( Integer e)
 *  ④注意点:泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类进行替换。
 *  ⑤如果实例化时，没有指明泛型的类型。默认类型为java.Lang.object类型。
 *
 * Set<Map.Entry<String,Integer>> set = map.entrySet();
 *
 * @author LYHstart
 * @create 2021-08-27 16:50
 */
public class GenericTest
{
    @Test   //在集合中使用泛型之前的情况:
    public void test1()
    {
        ArrayList list = new ArrayList();
        //要求:存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

        //问题一：类型不安全
        //list.add("Tom");    //ClassCastException

        //遍历输出
        for(Object obj:list)
        {
            //问题二：强转时，可能出现ClassCastException
            int score = (Integer) obj;
            System.out.println(score);
        }
    }

    @Test   //在集合中使用泛型之后的情况：
    public void test2()
    {
        //※泛型不能是基本数据类型 -> 使用包装类进行替换 (必须是对象?)
        List<Integer> list = new ArrayList<Integer>();

        list.add(123);
        list.add(23);
        list.add(3);

        //编译时对类型进行检查，保证数据安全
        //list.add("Tom");  //编译出错!

        for(int score:list)    //在使用泛型之后可以直接使用对应类型接受
        {
            //直接避免强转
            int stuScore = score;
            System.out.println(stuScore);
        }

        //使用Iterator进行遍历
        System.out.println("*********");
        Iterator iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

    @Test   //在集合中使用泛型的情况：以HashMap为例
    public void test3()
    {
        Map<String,Integer> map = new HashMap<String,Integer>();

        map.put("Tom",17);
        map.put("Jerry",18);
        map.put("Jack",24);
        map.put("Rose",32);

        //尝试添加异常数据
        //map.put(456,456); //Wrong 1st argument type. Found: 'int', required: 'java.lang.String' more..

        //泛型的嵌套     //Entry为Map的内部类
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator = entries.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
