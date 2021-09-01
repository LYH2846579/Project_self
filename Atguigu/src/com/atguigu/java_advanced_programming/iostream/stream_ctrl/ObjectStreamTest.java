package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-09-01 9:51
 *
 * 对象流
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本 数据类型 或 对象 的数据流.
 *        可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来
 */
public class ObjectStreamTest
{
    /*
    序列化过程：将内存中的Java对象保存到磁盘中或者通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test   //序列化
    public void test1()
    {
        ObjectOutputStream oos = null;
        try
        {
            //File
            File file = new File("F:\\Java\\IO\\Object.dat");

            //节点流
            FileOutputStream fos = new FileOutputStream(file);

            //对象流
            oos = new ObjectOutputStream(fos);

            //处理
            oos.writeObject(new String("Hello,World!"));


        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(oos != null)
                    //流资源关闭
                    oos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test   //反序列化  ->  成功！
    public void test2()
    {
        ObjectInputStream ois = null;
        try
        {
            //File
            File file = new File("F:\\Java\\IO\\Object.dat");

            //结点流
            FileInputStream fis = new FileInputStream(file);

            //对象流
            ois = new ObjectInputStream(fis);

            //读取
            Object o = ois.readObject();
            if(o instanceof String)
            {
                String str = (String) o;
                System.out.println(str);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(ois != null)
                    ois.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
