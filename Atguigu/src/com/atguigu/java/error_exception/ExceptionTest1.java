package com.atguigu.java.error_exception;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author LYHstart
 * @create 2021-08-07 9:56
 *
 * try-catch-finally处理异常
 * 处理异常的过程：处理过程中需要防止空指针异常等再次出现
 *              一般在编译过程中对其进行处理，即处理编译过程中出现的异常，运行时异常一般不做处理
 */
public class ExceptionTest1
{
    @Test
    public void test1()
    {
        String str = "123";
        str = "abc";

        Integer i1 = 456;
        String s = i1.toString();
        int i2 = Integer.valueOf(i1);               //包装类与基本数据类型转换
        System.out.println(i2);
        System.out.println("********************");
        System.out.println(s.charAt(2));            //读取字符串特定位置字符
        System.out.println("********************");
        int i3 = Integer.parseInt(s);               //字符串转换为基本数据类型
        Integer i4 = Integer.parseInt(s);           //由于自动装箱与拆箱的实现，可以使得包装类与基本数据类型随意转换
        System.out.println(i3);

        try{
            int num = Integer.parseInt(str);
            System.out.println("hello -------- 1");
        }
        catch (NumberFormatException e)
        {
            System.out.println("出现数值转换异常！");
            //System.out.println(e.getMessage());   -> For input string: "abc"
            //e.printStackTrace();
            // -> at com.atguigu.java.error_exception.ExceptionTest1.test1(ExceptionTest1.java:19)
        }
        System.out.println("hello -------- 2");

    }

    @Test
    public void test2()
    {
        FileInputStream fis = null;                 //处理未初始化异常
        try{
            File file = new File("hello.txt");
            fis = new FileInputStream(file);

            int data = fis.read();
            while(data != -1)
            {
                System.out.println((char)data);
                data = fis.read();
            }
        }catch (FileNotFoundException e)            //处理文件不存在异常
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fis != null)             //预防空指针异常
                    fis.close();            //防止流资源造成内存泄露
            } catch (IOException e)         //处理IO异常
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3()
    {

    }
}
