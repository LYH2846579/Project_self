package com.atguigu.java.wrapper_class;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.lang.reflect.Type;

/**
 * @author LYHstart
 * @create 2021-07-30 11:15
 * 1.包装类的使用     ->          使用单元测试加以学习
 * 2.基本数据类型、包装类、String类型转换  ※
 */
public class WrapperTest
{
    //基本数据类型转换为包装类
    @Test
    public void test1()
    {
        int num1 = 10;
        //System.out.println(num1.);             //非对象!
        //int初始化Integer类
        Integer i1 = new Integer(num1);
        System.out.println("I1:"+i1.toString());
        //String初始化Integer类
        Integer i2 = new Integer("123");
        System.out.println(i2);                 //默认输出为Value

        //boolean
        Boolean b1 = new Boolean("TrUe");    //忽略大小写
        System.out.println(b1);
        Boolean b2 = new Boolean("True123");
        System.out.println(b2);

    }

    //包装类转化为基本数据类型:调用包装类的xxxValue()方法
    @Test
    public void test2()
    {
        Integer i1 = new Integer(123);
        int i = i1.intValue();
        System.out.println(i);
        System.out.println();
    }

    //JDK5.0    实现自动装箱与自动拆箱
    @Test
    public void test3()
    {
        //自动装箱
        Integer i1 = 1;
        System.out.println(i1.toString());
        //自动拆箱
        i1 += 2;                //这个过程中存在三次转换
        System.out.println(i1.toString());
    }

    //基本数据类型、包装类 ---> String类型
    @Test
    public void test4()
    {
        int num1 = 10;
        //方式1:连接运算
        String str1 = num1 + "";
        //方式2:调用String类的valueOf(XXX xxx)
        float f1 = 12.3f;
        String str2 = String.valueOf(f1);
        System.out.println(str2);
    }

    //String类型 ---> 基本数据类型、包装类
    @Test
    public void test5()
    {
        String str1 = "123";
        //不可以进行强制类型转换!      //不满足子父类关系
        int num2 = Integer.parseInt(str1);
        System.out.println(num2);

        String str2 = "True";
        boolean b1 = Boolean.parseBoolean(str2);
        System.out.println(b1);
    }
}
