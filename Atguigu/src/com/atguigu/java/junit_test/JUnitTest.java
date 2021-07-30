package com.atguigu.java.junit_test;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-07-30 10:50
 * //单元测试方法
 * //@Test执行自动导包import org.junit.Test;
 */
public class JUnitTest
{
    @Test
    public void test_toString()
    {
        Shark s = new Shark("Tom",7);
        System.out.println(s.toString());
    }
    class Shark
    {
        String name;
        int age;

        public Shark(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
