package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-08-29 22:16
 *
 * 字符流不能用于文件传输，需要使用字节流进行实现
 *
 * I/O流处理套路
 * ① 创建对应的File类对象，指明处理的文件路径(三种实例化方式)
 * ② 创建对应的字节流对象(图片+视频)或者字符流对象(txt)      ->  异常先抛出throws
 * ③ 进行文件读写处理 ->(一般使用字节或者字符数组进行处理) int read; while((read = fr.read(cbuf))!=-1) ...
 * ④ 关闭流对象  ->   异常抛出
 * ⑤ 删除抛出异常，使用try-catch-finally进行处理，保证流资源正常关闭
 */
public class ByteReadWriteTest
{
    @Test   //使用字符流FileReader / FileWriter
    public void test1()
    {
        FileReader fr = null;
        FileWriter fw = null;
        try
        {
            //创建File类对象
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Tony.jpg");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\Tony1.jpg");

            //创建读取数据流及写入数据流
            fr = new FileReader(file1);
            fw = new FileWriter(file2);

            //读取、写入操作
            char[] cbuf = new char[5];
            int read = 0;
            while((read = (fr.read(cbuf))) != -1)
            {
                fw.write(new String(cbuf,0,read));  //随读随写
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭流资源
            try
            {
                fr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            //※由于try-catch会将异常处理掉，因此即使发生异常，以下代码也会正常执行
            try
            {
                fw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //被字符流处理过的图片是否可以还原?
    //Of course Not

    @Test   //尝试使用字节流进行传输   ->   成功传输!
    public void test2()
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            //创建File类对象
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Tony.jpg");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\Tony2.jpg");

            //字节流对象
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            //读取数据、处理数据、输出数据
            byte[] bbuf = new byte[5];
            int read;
            while((read = (fis.read(bbuf))) != -1)
            {
                fos.write(bbuf,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭流资源
            try
            {
                fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
