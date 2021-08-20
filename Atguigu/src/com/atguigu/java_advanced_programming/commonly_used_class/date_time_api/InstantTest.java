package com.atguigu.java_advanced_programming.commonly_used_class.date_time_api;

import org.junit.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author LYHstart
 * @create 2021-08-20 9:30
 */
public class InstantTest
{
    @Test
    public void test1()
    {
        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();    //调取静态方法
        System.out.println(instant);        //2021-08-20T01:34:52.589Z

        //对应时区转换
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));    //东八区时间
        System.out.println(offsetDateTime); //2021-08-20T09:34:52.589+08:00

        //toEpochMilli():获取自1970年1月1日0时0分0秒(UTC)开始的毫秒数
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        //使用毫秒数造Instant对象
        Instant instant1 = Instant.ofEpochMilli(milli);
        System.out.println(instant1);

        //练习
        Instant instant2 = Instant.ofEpochMilli(instant.toEpochMilli());    //为何offsetDateTime无法调用ofEpochMilli方法?
        System.out.println(instant2);
    }
}
