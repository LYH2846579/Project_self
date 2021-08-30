package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author LYHstart
 * @create 2021-08-29 22:16
 *
 * 字符流不能用于文件传输，需要使用字节流进行实现
 * FileInputStream
 * FileOutputStream
 *
 * I/O流处理套路
 * ① 创建对应的File类对象，指明处理的文件路径(三种实例化方式)
 * ② 创建对应的字节流对象(图片+视频)或者字符流对象(txt)      ->  异常先抛出throws
 * ③ 进行文件读写处理 ->(一般使用字节或者字符数组进行处理) int read; while((read = fr.read(cbuf))!=-1) ...
 * ④ 关闭流对象  ->   异常抛出
 * ⑤ 删除抛出异常，使用try-catch-finally进行处理，保证流资源正常关闭
 *
 * 附：char[] -> 转换为String进行输出
 *    byte[] -> 直接输出
 *    对于文本文件(.txt,.java,.c,.cpp)使用字符流处理
 *    对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)使用字节流处理
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

    //思考：FileInputStream可以读取文本文件吗? 可以使用FileOutputStream输出吗?
    //不可以!  IDEA执行时不会进入while循环中!直接退出
    @Test
    public void test3()
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            //创建File对象                      //Windows不区分大小写!
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Hello.txt");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\hello.txt"); //!!!

            //创建Read、Write流对象
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            //读、写
            byte[] bbuf = new byte[5];
            int read = 0;
            while((read = (fis.read(bbuf))) != -1)
            {
                fos.write(bbuf,0,read);
                System.out.println(Arrays.toString(bbuf));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try             //Found duplicated code in -> 警告代码重复!
            {
                if(fis != null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            if(fos != null)
            {
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

    @Test   //视频文件传输    -->耗时:20148
    public void test4()
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            //File对象的创建
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Switzerland.mp4");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\copy.mp4");

            //创建字节流对象
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            //计时开始
            long begin = 0L;
            long end = 0L;
            begin = System.currentTimeMillis();

            //读取、处理、输出
            byte[] buffer = new byte[100];          //需要大容量数组进行转存
            int read;
            while((read = fis.read(buffer)) != -1)
            {
                fos.write(buffer,0,read);
                //System.out.println(Arrays.toString(buffer));
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
                if(fis != null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(fos != null)
                    fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test5()
    {
        //copy方法测试
        String srcPath = new String("F:\\Java\\Project_self\\Atguigu\\Switzerland.mp4");
        String destPath = new String("F:\\Java\\Project_self\\Atguigu\\copy.mp4");
        boolean copy = copy(srcPath, destPath, 1000);
        if(copy)
            System.out.println("copy测试成功!");    //copy测试成功!
        else
            System.out.println("copy测试失败");
    }


    //包装copy方法  //字节流处理     //原路径 + 目的路径 + 大小
    public boolean copy(String srcPath,String destPath,int size)
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try
        {
            //创建File类对象
            File file1 = new File(srcPath);
            File file2 = new File(destPath);

            //实例化流对象
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            //输入输出处理
            byte[] buffer = new byte[size];
            int read;
            while((read = fis.read(buffer)) != -1)
            {
                fos.write(buffer,0,read);
            }
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            //流资源关闭
            try
            {
                if(fis != null)
                    fis.close();
                if(fos != null)
                    fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
