package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-09-01 10:52
 *
 * RandomAccessFile的使用  (使用byte[])
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，也可以作为一个输出流   test1
 */
public class RandomAccessFileTest
{
    @Test
    public void test1()
    {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try
        {
            //RandomAccessFile
            raf1 = new RandomAccessFile("F:\\Java\\Project_self\\Atguigu\\Tony.jpg","r");
            raf2 = new RandomAccessFile("F:\\Java\\Project_self\\Atguigu\\Tony-Random.jpg","rw");

            //处理对象
            byte[] buffer = new byte[1024];
            int read;
            while((read = raf1.read(buffer)) != -1)
            {
                raf2.write(buffer,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭流对象
            if(raf1 != null)
            {
                try
                {
                    raf1.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(raf2 != null)
            {
                try
                {
                    raf2.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test   //对文件进行写操作
    public void test2() throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile("F:\\Java\\Project_self\\Atguigu\\hi.txt","rw");

        raf.seek(3);    //将指针调到角标为3的位置
        raf.write("xyz".getBytes());    //覆盖写入abcxyzghijk

        raf.close();
    }

    @Test   //当然可以读入内存直接进行操作
    public void test3() throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile("F:\\Java\\Project_self\\Atguigu\\hi.txt","rw");

        //实现在abc之后插入xyz的操作
        //byte[] buffer = new byte[1024];   //无法确定后续大小
        raf.seek(3);
        //读取数组
        byte[] buffer = new byte[8];
        //存储列表
        Collection<Byte> list = new LinkedList<Byte>();
        int read;
        while((read = raf.read(buffer)) != -1)
        {
            for (int i = 0; i < read; i++)
            {
                list.add(buffer[i]);
            }
        }
        //修改游标
        raf.seek(3);
        //写入数据
        raf.write("xyz".getBytes());
        //修改游标
        //raf.seek(6);
        //覆盖数据
        for (int i = 0; i < list.size(); i++)
        {
            byte b = ((LinkedList<Byte>) list).get(i);
            raf.write(b);
        }                       //abcxyzdefghijk

        //关闭
        raf.close();
    }
}
