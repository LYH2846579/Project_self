package com.atguigu.java.error_exception;

import org.junit.Test;

import java.sql.Date;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-08-07 9:04
 *
 * 常见的异常类型
 */
public class Exception_self extends Throwable
{

    @Test
    public void test1()
    {
        //ClassCastException    类型转换错误
        // java.lang.ClassCastException: java.sql.Date cannot be cast to java.lang.String
        Object o = new Date(2846579L);
        String s = (String)o;
    }

    @Test
    public void test2()
    {
        //ArrayIndexOutOfBoundsException
        //java.lang.ArrayIndexOutOfBoundsException: 10
        int[] array = new int[10];
        System.out.println(array[10]);
    }

    @Test
    public void test3()
    {
        //StringIndexOutOfBoundsException
        //java.lang.StringIndexOutOfBoundsException: String index out of range: 3
        String s = new String("123");
        System.out.println(s.charAt(3));
    }

    @Test
    public void test4()
    {
        //NullPointerException
        //java.lang.NullPointerException
        int[] array = new int[10];
        array = null;
        System.out.println(array[1]);
    }

    @Test
    public void test5()
    {
        //ArithmeticException
        //java.lang.ArithmeticException: / by zero
        int a = 10;
        int b = 0;
        System.out.println(a/b);
    }

    @Test
    public void test6()
    {
        //NumberFormatException
        //java.lang.NumberFormatException: For input string: "123a"
        String s = "123a";
        int a = Integer.parseInt(s);
        System.out.println(a);
    }

    @Test
    public void test7()
    {
        //InputMismatchException

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个整数");
        int num = scan.nextInt();                   //Test中不能读取数字?
        //int n = "123";
        System.out.println(num);
        scan.close();
    }
}
