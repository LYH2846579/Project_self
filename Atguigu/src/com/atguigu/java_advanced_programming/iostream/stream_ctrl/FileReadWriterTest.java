package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import com.atguigu.java.inner_class.Outer;
import org.junit.Test;

import java.io.*;
import java.util.logging.Level;

/**
 * @author LYHstart
 * @create 2021-08-29 14:57
 *
 * 一、流的分类
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类             节点流(或文件流)             缓冲流(处理流的一种)
 * InputStream         FileInputStream           BufferedInputStream
 * OutputStream        FileOutputStream          BufferedOutputStream
 * Reader              FileReader                BufferedReader
 * Writer              FileWriter                BufferedWriter
 */
public class FileReadWriterTest
{
    public static void main(String[] args)
    {                                                 //相较于当前工程
        File file = new File("Hello.txt");  //F:\Java\Project_self\Hello.txt

        System.out.println(file.getAbsolutePath());
    }


    /*
    将当前模块下Hello.txt文件读入程序之中，并输出到控制台

    附：
    ①read()的理解：返回读取的一个字符，若达到文件夹末尾，返回-1
    ②在I/O处理的过程中有着很多种异常出现情况，由于涉及到流的关闭操作，优先采取try-catch-finally进行处理
        需要大量的try-catch进行处理 -> 晕 -》IOException
    ③读入的文件一定要存在，否则就会报FileNotFoundException
     */
    @Test
    public void test1()
    {   //1.实例化File类的对象，指明要操作的文件
        File file = new File("Hello.txt");  //相较于当前Module↓

        //2.提供具体的流
        FileReader fr = null;
        try
        {
            fr = new FileReader(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //3.数据的读入
        //read():返回读入的一个字符。若达到文件末尾，返回-1

        //第一种读取方式
        int data = 0;       //read()的返回值为int  ->  强转为字符
        try
        {
            data = fr.read();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while(data != -1)
        {
            System.out.print((char) data);
            try
            {
                data = fr.read();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("********");

        //第二种读取方式   ->  语法上对于方式一的修改
        //int data1 = 0;
        FileReader fr1 = null;
        try
        {
            fr1 = new FileReader("Hello.txt");
            while((data = fr1.read()) != -1)
            {
                System.out.print((char)data);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            //4.流的关闭操作
            if(fr != null)
            {
                try
                {
                    fr.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(fr1 != null)
            {
                try
                {
                    fr1.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println(file.getAbsolutePath());  //F:\Java\Project_self\Atguigu\Hello.txt
    }

    @Test   //对read()操作升级：使用read()的重载方法
    public void test2()
    {
        FileReader fr = null;
        try
        {
            //1.File类的实例化
            File file = new File("F:\\Java\\Project_self\\Atguigu\\Hello.txt");

            //2.FileReader流的实例化
            fr = new FileReader(file);

            //3.数据的输入
            //read(char[] cbuf):返回每次读入cbuf数组中的字符个数。若达到文件末尾，返回-1
            char[] cbuf = new char[5];
            int read = 0;
            while((read = fr.read(cbuf)) != -1)
            {
                //第一种遍历方式：for
                //for(char c:cbuf)
                //  System.out.print(c);  //Hello,World!orl
                //for (int i = 0; i < read; i++)      //一定要小于read()方法读取的长度
                //{
                //    System.out.print(cbuf[i]);
                //}

                //第二种遍历方式
                //String str = new String(cbuf);
                //截断字符串
                //str = str.substring(0,read);
                //System.out.print(str);

                //第三种遍历方式
                String str = new String(cbuf,0, read);
                //输出
                System.out.print(str);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //FileReader流的关闭
            try
            {
                fr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
    从内存中写出数据到硬盘的文件里

    File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
    File对应的硬盘中的文件如果存在:
        如果流使用的构造器是:FileWriter(file,false) / FileWriter(file):对原有文件进行覆盖
        如果流使用的构造器是:FileWriter(file,true):不会对原有文件覆盖，而是在原文件基础上追加内容
     */
    @Test
    public void test3() throws IOException
    {
        //1.提供File类的对象，指出写出到的文件
        File file = new File("Hello.txt");

        //2.提供FileWriter的对象，用于数据的写出
        FileWriter fw = new FileWriter(file);
        //FileWriter fw = new FileWriter(file,true);    //在原有文件后追加

        //3.写出的操作
        fw.write("I have statement dream!");
        fw.write("you need statement dream too!");

        //4.流资源关闭操作
        fw.close();
    }

    /*
    将数据从文件中读取出来写入另一个文件之中
     */
    @Test   //复制txt文件       -> 耗时:117
    public void test4()
    {
        FileReader fr = null;
        FileWriter fw = null;
        try
        {
            //创建File类的对象
            File file1 = new File("Hello.txt");
            File file2 = new File("hi.txt");

            //创建流对象FileRead、FileWrite
            fr = new FileReader(file1);
            fw = new FileWriter(file2);

            //计时开始
            long begin = 0L;
            long end = 0L;
            begin = System.currentTimeMillis();

            //读取、处理、写入数据
            char[] cbuf = new char[5];
            int read;
            String s = "";
            while((read = fr.read(cbuf)) != -1)
            {
                fw.write(cbuf,0,read);
            }

            //计时结束
            end = System.currentTimeMillis();
            //输出计时
            System.out.println("耗时:"+(end-begin));

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(fr != null)
                    fr.close();
                if(fw != null)
                fw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}









