package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-08-31 8:24
 *
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流
 *  InputStreamReader:将一个字节的输入流转换为字符的输入流
 *  OutputStreamWriter:将一个字符的输出流转换为字节的输出流
 *
 * 2.作用：提供字节流与字符流之间的转换
 *
 * 3.解码：字节、字节数组 --> 字符数组、字符串
 *   解码：字符数组、字符串 --> 字节、字节数组
 *
 * 4.字符集
 *  isr = new InputStreamReader(fis,"UTF-8");
 *  可以使用转换流处理字符集之间的转换问题
 *
 * 附：※这里使用转换流处理使得在字符层面进行加密操作(ASCII加密)  实验成功!
 */
public class ByteToChar
{
    @Test   //实现字节流到字符流的转换
    public void test1()
    {
        InputStreamReader isr = null;
        try
        {
            //使用字节流读取文件中的数据，转为控制台输出 (使用转换流处理)
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Hello.txt");

            //流
            FileInputStream fis = new FileInputStream(file1);

            //转换流
            //isr = new InputStreamReader(fis);   //使用默认的字符集转换
            isr = new InputStreamReader(fis,"UTF-8");   //指定字符集转换(与文件保存时字符集相同)

            //输出到控制台
            char[] cbuf = new char[10];
            int read;
            while((read = isr.read(cbuf)) != -1)
            {
                //增强for
                for(char c:cbuf)
                {
                    System.out.print(c);    //char数组可以识别"\n"
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(isr != null)
                    isr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test   //转换流的综合使用  ->  加密算法    //异常处理暂用throws
    public void test2() throws IOException
    {
        //读取Hello.txt转存为hi1.txt
        File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Hello.txt");
        File file2 = new File("F:\\Java\\Project_self\\Atguigu\\hi1.txt");

        //字节流 (节点流)
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        //转换流处理
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");

        //数据读取与输出
        char[] cbuf = new char[10];
        int read;
        while((read = isr.read(cbuf)) != -1)
        {
            //加密操作
            for (int i = 0; i < read; i++)
            {
                cbuf[i] = (char)((int)cbuf[i] + 7);
            }
            //数据输出
            osw.write(cbuf,0,read);
        }
        System.out.println("数据加密成功");

        //流资源关闭
        if(isr != null)
            isr.close();
        if(osw != null)
            osw.close();
    }

    @Test   //转换流解密     //成功!
    public void test3() throws IOException
    {
        //读取hi1.txt解密为hi2.txt
        File file1 = new File("F:\\Java\\Project_self\\Atguigu\\hi1.txt");
        File file2 = new File("F:\\Java\\Project_self\\Atguigu\\hi2.txt");

        //字节流 (节点流)
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        //转换流处理
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");

        //数据读取与输出
        char[] cbuf = new char[10];
        int read;
        while((read = isr.read(cbuf)) != -1)
        {
            //加密操作
            for (int i = 0; i < read; i++)
            {
                cbuf[i] = (char)((int)cbuf[i] - 7);
            }
            //数据输出
            osw.write(cbuf,0,read);
        }
        System.out.println("数据解密成功!");

        //流资源关闭
        if(isr != null)
            isr.close();
        if(osw != null)
            osw.close();
    }
}



















