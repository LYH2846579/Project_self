package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-08-30 15:08
 *
 * 处理流之一：缓冲流的使用
 *
 * 1、缓冲流
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2、作用：提供流的读取、写入速度
 *    原理：内部提供了一个大小为8192字节的缓冲区
 */
public class BufferedTest
{
    @Test
    public void test1() throws IOException
    {
        //采用视频赋值来验证缓冲流的速度   ->  不妨采用计时器计时   -->耗时:409

        //File
        File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Switzerland.mp4");
        File file2 = new File("F:\\Java\\Project_self\\Atguigu\\BufferedInOutputStream.mp4");

        //造节点流对象
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        //Buffered...Stream -> 将节点流对象作为参数
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //计时开始
        long begin = 0L;
        long end = 0L;
        begin = System.currentTimeMillis();

        //操作数据(读取+输出)
        byte[] buffer = new byte[100];
        int read;
        while((read = bis.read(buffer)) != -1)
        {
            bos.write(buffer,0,read);

            //刷新缓冲区
            //bos.flush();
        }

        //计时结束
        end = System.currentTimeMillis();
        //输出计时
        System.out.println("耗时:"+(end-begin));

        //关闭流资源 -> 要求：先关闭外层流，再关闭内层流
        bos.close();
        bis.close();
        //说明：关闭外层流的同时，内层流也会自动进行关闭。可以省略如下两行代码
        //fos.close();
        //fis.close();
    }

    @Test   //BufferedRead & BufferedWrite测试    ->时间测试->耗时:92
    public void test2() throws IOException
    {
        //File
        File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Hello.txt");
        File file2 = new File("F:\\Java\\Project_self\\Atguigu\\BufferedRead.txt");

        //FileReader & Writer
        FileReader fr = new FileReader(file1);
        FileWriter fw = new FileWriter(file2);

        //创建BufferedReader & Writer
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);

        //计时开始
        long begin = 0L;
        long end = 0L;
        begin = System.currentTimeMillis();

        //读取、操作、写出数据
        //第一种方式读取
        char[] cbuf = new char[10];
        int read;
        while((read = br.read(cbuf)) != -1)
        {
            bw.write(cbuf,0,read);
        }
        //第二种方式读取
        String data;
        while((data = br.readLine()) != null)
        {
            //方法一:
            bw.write(data+"\n");    //默认无换行符

            //方法二:
            bw.write(data);
            bw.newLine();
        }


        //计时结束
        end = System.currentTimeMillis();
        //输出计时
        System.out.println("耗时:"+(end-begin));

        //流资源关闭
        br.close();
        bw.close();
    }
}
