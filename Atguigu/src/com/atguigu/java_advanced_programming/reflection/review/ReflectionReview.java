package com.atguigu.java_advanced_programming.reflection.review;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author LYHstart
 * @create 2021-09-05 9:37
 *
 * 对前一天学习的JAVA反射机制进行复习
 */
public class ReflectionReview
{
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        //联系创造运行时对象过程
        String name = "com.atguigu.java_advanced_programming.reflection.review.Person";
        Class clazz = Class.forName(name);  //ClassNotFoundException

        //使用构造器创造对象
        //调用指定参数结构的构造器，生成Constructor实例
        Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);   //NoSuchMethodException

        //创建对象
        Person p1 = (Person) cons.newInstance("Tom",12);
        System.out.println(p1);
    }

    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException
    {
        //尝试调用一些私有化元素
        String Path = "com.atguigu.java_advanced_programming.reflection.review.Person";
        Class clazz = Class.forName(Path);

        //调用私有化构造器
        //Constructor cons = clazz.getConstructor(String.class);  //!
        Constructor cons = clazz.getDeclaredConstructor(String.class);//※
        //设置权限
        cons.setAccessible(true);
        //实例化对象
        Person jerry = (Person) cons.newInstance("Jerry");
        System.out.println(jerry);

        //获取私有化属性
        Field name = clazz.getDeclaredField("name");//String
        //修改权限
        name.setAccessible(true);
        //修改属性
        name.set(jerry,"Jack");
        System.out.println(jerry);

        //获取私有化方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        showNation.invoke(jerry,"中国");      //方法调用
    }
}
