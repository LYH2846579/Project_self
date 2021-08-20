package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.util.Date;

/**
 * @author LYHstart
 * @create 2021-08-17 11:32
 *
 * JDK8.0之前的时间和日期API
 * System.currentTimeMillis();  //获取从1970.01.01 00:00至今的毫秒数
 */
public class DateTimeTest
{
    @Test
    public void test1()
    {
        long currentTime = 0L;
        currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
    }

    @Test   //Date
    public void test2()
    {
        //空参构造器
        Date d = new Date();
        System.out.println(d.toString());       //输出日期+时间+时区
        System.out.println(d.getTime());        //与currentTimeMillis()类似

        //含参构造器         //毫秒数
        java.util.Date d1 = new Date(System.currentTimeMillis());
        System.out.println(d1.toString());  //Tue Aug 17 11:43:24 CST 2021

        //创建java.sql.Date对象
        java.sql.Date d2 = new java.sql.Date(1629171464648L);
        System.out.println(d2.toString()); //2021-08-17

        //对象转换
        //①强制类型转换
        java.util.Date d3 = new java.sql.Date(1629171464648L);
        if(d3 instanceof java.sql.Date)
        {
            java.sql.Date d4 = (java.sql.Date)d3;
            System.out.println(d4.toString());
        }
        else
            System.out.println("类型不匹配!");
        //②跨类型转换 -> 非多态
        java.util.Date d5 = new Date();
        java.sql.Date d6 = new java.sql.Date(d5.getTime());     //利用毫秒数作为构造器对象
        System.out.println(d6.toString());
    }

    //面试题1
    @Test
    public void test3()
    {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);     //将null转换为"null"

        System.out.println(sb.length());    //4

        System.out.println(sb);     //"null"

        StringBuffer sb1 = new StringBuffer(str);  //NullPointerException异常
        System.out.println(sb1);    //...
    }
}
