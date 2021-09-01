package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-08-31 21:47
 *
 * 1.标准的输入、输出流
 * 1.1
 * System.in:标准的输入流，默认从键盘输入
 * System.out:标准的输出流，默认从控制台输出
 * 1.2
 * System类的setIn(InputStream is) / setout(PrintStream ps)方式重新指定输入和输出的设备
 *
 * 1.3练习:
 * 从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
 * 直至当输入“e”或者“exit”时，退出程序。
 *
 * 方法一:使用Scanner实现，调用next()返回一个字符串
 * 方法二:使用System.in实现。System.in--->转换流--->BufferedReader的readLine
 *
 *
 * 2.打印流:PrintStream 和 PrintWriter
 * 2.1 提供了一系列重载的print()和println()
 * 2.2 练习
 *
 */
public class OtherStreamTest
{
    @Test
    public void test1()
    {
        //使用Scanner实现
        Scanner scan = new Scanner(System.in);
        String str;
        while(true)
        {
            System.out.println("请输入字符串:");
            str = scan.next();
            //预防空指针异常
            if("e".equalsIgnoreCase(str) || "exit".equalsIgnoreCase(str))
            {
                System.out.println("程序退出!");
                break;
            }
            else
            {
                System.out.println(str.toUpperCase());
            }
        }
    }

    @Test
    public void test2() throws IOException
    {
        //使用System.in实现
        //可以将System.in作为转换流InputStreamReader的参数
        //再将转换流作为BufferedReader的参数  转换流 -> 缓冲流

        //将键盘作为参数读取源
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        //读取数据并处理
        String str = null;
        while(true)
        {
            System.out.println("请输入一个字符串:");
            str = br.readLine();
            if("e".equalsIgnoreCase(str) || "exit".equalsIgnoreCase(str))
            {
                break;
            }
            else
                System.out.println(str.toUpperCase());
        }

        //关闭流资源
        br.close();
    }


    //打印流练习
    @Test
    public void test3()
    {
        PrintStream ps = null;
        try
        {
            //创建字节流
            FileOutputStream fos = new FileOutputStream("F:\\Java\\IO\\ASCII.txt");
            //创建打印输出流,设置为自动刷新模式(写入换行符或字节'\n'时都会刷新输出缓冲区)
            ps = new PrintStream(fos,true);
            //把标准输出流(控制台输出)改成文件
            if(ps != null)
                System.setOut(ps);

            //输出ASCII字符
            for (int i = 0; i < 255; i++)
            {
                System.out.print((char)i);
                if(i % 50 == 0)
                    System.out.println();   //每五十个数据换一行
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            //关闭流文件
            if(ps != null)
                ps.close();
        }
    }


    /*
    将内存中的字符串、基本数据类型的变量写出到文件中    //使用try-catch-finally处理
     */
    //文件流读写练习
    @Test
    public void test4() throws IOException
    {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("F:\\Java\\IO\\data.txt"));
        //2.
        dos.writeUTF("劉亱華");
        dos.flush();
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        //3.
        dos.close();
    }

    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。   //使用try-catch-finally处理
     */
    @Test
    public void test5() throws IOException
    {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("F:\\Java\\IO\\data.txt"));
        //2.
        String str = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        //3.
        System.out.println("Name:"+str);
        System.out.println("Age:"+age);
        System.out.println("IsMale:"+isMale);
    }
}
