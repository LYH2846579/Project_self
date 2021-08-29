package com.atguigu.java_advanced_programming.iostream.file_ctrl;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-08-29 8:53
 *
 * 练习
 * 1.利用File构造器，new 一个文件目录file
 *  1)在其中创建多个文件和目录
 *  2)编写方法，实现删除file中指定文件的操作
 *
 * 2.判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
 *
 * 3.遍历指定目录所有文件名称，包括子文件目录中的文件。
 *  拓展1:并计算指定目录占用空间的大小
 *  拓展2:删除指定文件目录及其下的所有文件
 *
 *
 *  //※-Deditable.java.test.console=true
 *  在IDEA配置文件中(两个)均添加如上语句，可以解决单元测试控制台无法使用Scanner读取数据的问题!
 *
 */
public class File_Exp
{
    //练习1
    @Test
    public void test1()
    {
        //练习一
        File file = new File("F:\\Java\\IO\\io1");

        //new一个文件目录 -> 首先寻址是否存在
        File file1 = new File(file,"file");
        if(!file1.exists() || !file1.isDirectory()) //判断是否为文件夹!
        {
            file1.mkdir();
            System.out.println("文件夹创建成功!");
        }
        else
        {
            System.out.println("file已经存在!");
        }
    }

    @Test
    public void test2()
    {
        //练习一
        //创建多个文件和目录
        File file = new File("F:\\Java\\IO\\io1\\file");

        File file1 = new File(file,"dir1");
        File file2 = new File(file,"dir2");
        File file3 = new File(file,"file1");
        File file4 = new File(file,"file2");

        File[] f = new File[]{file1,file2,file3,file4};

        //循环创建
        for(int i=0;i<f.length;i++)
        {
            File filetemp = f[i];
            if(!filetemp.exists())
            {
                String name = filetemp.getName();
                if(name.indexOf("dir") != -1)
                {
                    filetemp.mkdir();
                    System.out.println(filetemp.getPath()+"创建成功");
                }
                else if(name.indexOf("file") != -1)
                {
                    try
                    {
                        filetemp.createNewFile();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        System.out.println("文件创建成功");
                    }
                }
                else
                    System.out.println("未识别类型");

            }
            else
            {
                System.out.println("文件已存在");
            }
        }
        /*
        F:\Java\IO\io1\file\dir1创建成功
        F:\Java\IO\io1\file\dir2创建成功
        文件创建成功
        文件创建成功
        */
    }

    @Test
    public void test3()
    {
        //练习一：
        //删除file中指定文件的操作
        File file = new File("F:\\Java\\IO\\io1\\file");

        String str = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入file文件夹下的文件名称:");
        str = scanner.next();

        File file1 = new File(file,str);
        if(file1.exists())
        {
            if(file1.isFile())
            {
                file1.delete();
                System.out.println("文件删除成功!");
            }
            else
            {
                System.out.println("文件类型不匹配!");
            }
        }
        else
        {
            System.out.println("该文件不存在!");
        }
    }


    //练习2
    @Test
    public void test4()
    {
        //判断文件目录下是否有后缀名为.jpg的文件，若有，则输出该文件名称
        File file = new File("F:\\Java\\IO\\io1\\file");

        String[] list = file.list();
        for(String temp:list)
        {
            if(temp.indexOf(".jpg") != -1)
                System.out.println(temp);       //Tony.jpg
        }
    }

    @Test
    public void test5()
    {
        //遍历指定目录所有文件名称，包括子文件目录中的文件
        File file = new File("F:\\Java\\IO\\io1\\file");

        //String[] list = file.list();
        File[] files = file.listFiles();

        //拓展一：并计算指定目录占用空间的大小
        long size = 0L;
        for(File filetemp:files)
        {
            size += filetemp.length();
        }                                               //只有Tony龟占有空间
        System.out.println("目录中所有文件大小:"+size);    //目录中所有文件大小:15264

    }

    @Test   //测试File类中获取文件大小的方法 -> 联系test5
    public void test()
    {
        File file = new File("F:\\Java\\IO\\io1\\file\\Tony.jpg");

        System.out.println(file.length());  //返回值为long:15264
    }

    @Test   //拓展二：删除指定文件目录及其下的所有文件
    public void test6()
    {
        File file = new File("F:\\Java\\IO\\io1\\file\\del");

        //如下删除操作不能删除目录
        if (file.exists())
        {
            boolean delete = file.delete();
            if(delete)
                System.out.println("目录删除成功!");
            else
                System.out.println("目录删除失败!");
        }
        else
            System.out.println("目录删除失败!");
    }

    @Test   //此种删除方式可以实现目录删除
    public void test7()
    {
        File file = new File("F:\\Java\\IO\\io1\\file\\del");

        //尝试使用File[]依次删除
        boolean flag = false;
        File[] files = file.listFiles();
        for(File filetemp:files)
        {
            flag = filetemp.delete();
        }
        flag = file.delete();
        //输出
        if(flag)
            System.out.println("目录删除成功");
        else
            System.out.println("目录删除失败");
    }

}
