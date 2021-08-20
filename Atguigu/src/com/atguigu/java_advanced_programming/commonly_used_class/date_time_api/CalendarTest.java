package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LYHstart
 * @create 2021-08-17 22:47
 *
 * Calendar日历类(抽象类)的使用
 */
public class CalendarTest
{
    @Test
    public void test1()
    {
        //1.实例化
        //方式一：创建其子类(GregorianCalendar)的对象
        //方式二:调用其静态方法getInstance()            //直接类调用静态方法
        Calendar calendar = Calendar.getInstance();
        //System.out.println(calendar.getClass());  //仍为GregorianCalendar对象

        //get()方法
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));    //return int;

        //set()方法
        calendar.set(Calendar.DAY_OF_MONTH,4);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));    //设置天数

        //add()方法
        calendar.add(Calendar.DAY_OF_MONTH,3);      //加上三天
        calendar.add(Calendar.DAY_OF_MONTH,-3);     //减去三天

        //getTime()方法               //获取到的为java.util.Date
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime()方法
        calendar.setTime(new Date());       //这里将calendar重新赋值
        System.out.println(calendar.getTime());
    }

    //练习:2021-08-19是一年中的第几天?
    @Test
    public void test2()
    {
        //①将"2021-08-19转换为java.util.Date类"  ->  String->Date
        //使用SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String转化
        String s = "2021-08-19";
        Date date = null;
        try     //处理转换异常
        {
            date = sdf.parse(s);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        //②将java.util.Date类实例化Calendar对象
        Calendar calendar = Calendar.getInstance();         //类调用静态方法获取对象
        calendar.setTime(date);
        int days = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(days);
    }
}







