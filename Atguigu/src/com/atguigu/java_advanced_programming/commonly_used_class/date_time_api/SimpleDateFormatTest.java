package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author LYHstart
 * @create 2021-08-17 21:50
 *
 * 格式化：日期 -> 文本  Date->String
 * 解析：文本 -> 日期    String->Date
 *
 * SimpleDateFormat为java.text包下一个标准类    格式化与解析均不是静态函数 -> 需要实例化对象
 *
 */
public class SimpleDateFormatTest
{
    @Test
    public void test1()
    {
        //默认格式转化
        //实例化SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat();
        //格式化：日期 -> 字符串
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        //解析：字符串 -> 日期
        String str = "19-12-18 上午11:43";
        Date date1 = null;
        //处理未识别转换格式的异常
        try
        {
            date1 = sdf.parse(str);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println(date1);

        //指定格式转化
        //实例化                                             //月份大写、分钟小写
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        Date date2 = new Date();
        String str1 = sdf1.format(date2);
        System.out.println(str1);
        //解析
        String str2 = "2021-08-17 10:06:19";
        Date date3 = null;
        try
        {
            date3 = sdf1.parse(str2);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println(date3);
    }

    //练习一：将字符串"2020-09-08"转换为java.sql.Date
    @Test
    public void test2()
    {
        //实例化SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "2020-09-08";
        Date date = null;
        try
        {
            //默认转化为java.util.Date
            date = sdf.parse(str);
            System.out.println(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        //将java.util.Date转化为java.sql.Date
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println("date1.getClass():"+date1.getClass());
        System.out.println(date1);
    }

    //练习二："三天打鱼两天晒网" 自1990-01-01起 xxxx-xx-xx 打渔?晒网?
    @Test
    public void test3() throws ParseException   //抛出异常
    {
        //前提准备
        String str1 = "1990-01-01";
        String str2 = "1990-01-03";
        //计算毫秒差 以java.util.Date实现
        //Date date1 = new Date(str1);      //@Deprecated不推荐使用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(str1);
        Date date2 = sdf.parse(str2);
        long diff = date2.getTime()-date1.getTime();
        //将毫秒级转换为天数
        long day = diff/86400000;       //※1ms = 0.001s
        long check = day%5;
        if(check==0 || check==1 || check==2)
        {
            System.out.println("打渔!");
        }
        else
            System.out.println("晒网!");
    }

}


