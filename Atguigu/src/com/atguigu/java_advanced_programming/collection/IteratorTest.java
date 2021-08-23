package com.atguigu.java_advanced_programming.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author LYHstart
 * @create 2021-08-22 12:52
 */
public class IteratorTest
{
    @Test
    public void test1()
    {
        Collection coll = new ArrayList();

        ((ArrayList) coll).add("123");
        ((ArrayList) coll).add("456");
        ((ArrayList) coll).add("789");

        Iterator iterator = coll.iterator();
        System.out.println(iterator.hasNext()); //存在下一个对象
        System.out.println(iterator.next());    //取出下一个对象

        //遍历
        System.out.println("*************");
        iterator = coll.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        //remove()
        System.out.println("*************");
        iterator = coll.iterator();
        while(iterator.hasNext())
        {
            if(iterator.next().equals("789"))
                iterator.remove();
        }
        iterator = coll.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        //增强for循环   集合元素->局部变量:集合对象     (内部仍使用迭代器)
        System.out.println("*************");
        for(Object obj:coll)
        {
            System.out.println(obj.toString());
        }
        //原码分析
        System.out.println("*************");
        Object obj;
        for(Iterator iterator1=coll.iterator();iterator1.hasNext(); System.out.println(obj))
        {
            obj = iterator1.next();     //Obj子类重写toString方法
        }
        //一种错误方式
        /*
        for(Iterator iterator1=coll.iterator();iterator1.hasNext(); System.out.println(iterator1.toString()))
        {
            iterator1.next();       //打印出来的为iterator所指的单元地址!
        }
        */

        //增强for循环打印数组 -> 局部变量:数组对象
        System.out.println("*************");
        int[] arr = new int[]{0,1,6,3,4,5};
        for(int i:arr)
        {
            //System.out.println(arr[i]);       //错误写法  ArrayIndexOutOfBandsException
            System.out.println(i);              //正确写法
        }

    }

    @Test
    public void test2()
    {
        //增强for练习题
        String[] str = new String[]{"MM","MM","MM"};

        //方式一：普通for循环       //三个"GG"
        //for(int i=0;i<str.length;i++)
        //    str[i] = "GG";

        //方式二：增强for循环
        for(String s:str)       //新对象s不会改变原有数据值
        {
            s = "GG";           //※增强for循环不能用于赋值!
        }

        //遍历
        for (int i = 0; i < str.length; i++)
        {
            System.out.println(str[i]);
        }
    }

    //String数组测试
    @Test
    public void test3()
    {
        String[] str = new String[5];
        for (int i = 0; i < str.length; i++)
        {
            System.out.println(str[i]);             //null
        }
    }
}
