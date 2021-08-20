package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * @author LYHstart
 * @create 2021-08-20 10:03
 *
 * 作用：对LocalDate、LocalTime、LocalDateTime进行解析
 * 附：三者都调用静态方法进行实例化
 *    LocalDateTime localDateTime = LocalDateTime.now();
 *    LocalDateTime localDateTime1 = LocalDateTime.of(2021,8,20,0,0,0);
 *
 * 回忆:SimpleDateFormat标准类转化Date类   ->  java.util.Date类
 *
 *
 */
public class DateTimeFormatterTest
{
    @Test
    public void test1()
    {
        //实例化对象
        //方式一：预定义标准格式:ISO_LOCAL_DATE_TIME;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期 -> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str = dateTimeFormatter.format(localDateTime);
        System.out.println(localDateTime);      //2021-08-20T10:10:33.704   java.util.Date
        System.out.println(str);                //2021-08-20T10:10:33.704   String

        //解析:字符串 -> 日期
        TemporalAccessor parse = dateTimeFormatter.parse("2021-08-20T10:10:33.704");
        System.out.println(parse);              //{},ISO resolved to 2021-08-20T10:10:33.704


        //方式二：本地化相关的格式:ofLocalizedDateTime(FormatStyle.LONG)
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        //格式化
        String str1 = formatter.format(localDateTime);
        System.out.println(str1);               //2021年8月20日 上午10时18分46秒

        //方式三：自定义格式:ofPattern("yyyy-MM-dd hh:mm:ss");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format = dateTimeFormatter1.format(localDateTime);
        System.out.println(format);

        //解析                                                    //必须是:  03:52:09!
        TemporalAccessor parse1 = dateTimeFormatter1.parse("2021-08-20 03:52:09");
        System.out.println(parse1);
    }
}
