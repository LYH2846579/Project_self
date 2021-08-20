package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LYHstart
 * @create 2021-08-19 16:53
 *
 * JDK8.0之后新增API：包含有java.time包
 * 时间与日期：
 *      ① LocalDate
 *      ② LocalTime
 *      ③ LocalDateTime
 */
public class LocalDateTimeTest
{
    //时间与日期API练习
    @Test
    public void test1()
    {
        //now():获取当前的日期、时间、日期+时间    //※静态方法!
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);      //2021-08-19
        System.out.println(localTime);      //16:57:18.139
        System.out.println(localDateTime);  //2021-08-19T16:57:18.139

        //of():设置指定的年、月、日、时、分、秒，没有偏移量!
        LocalDateTime localDateTime1 = LocalDateTime.of(2021,8,19,17,00,00);
        System.out.println(localDateTime1);

        System.out.println("********************");
        //getXxx():获取相关属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        System.out.println("********************");
        //withXxx():设置相关属性      ->  不可变性
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(4);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime.withHour(4);       //设置为4小时
        System.out.println(localDateTime);
        System.out.println(localDateTime3);

        //不可变性
        //plus
        LocalDateTime localDateTime4 = localDateTime.plusHours(4);      //加4小时
        System.out.println(localDateTime);
        System.out.println(localDateTime4);

        //minus
        LocalDateTime localDateTime5 = localDateTime.minusHours(4);      //加4小时
        System.out.println(localDateTime);
        System.out.println(localDateTime5);
    }
}