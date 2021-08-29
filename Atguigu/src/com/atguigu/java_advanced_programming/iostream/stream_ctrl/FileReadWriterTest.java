package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
}









