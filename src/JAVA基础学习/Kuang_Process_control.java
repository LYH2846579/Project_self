package JAVA基础学习;
//对JAVA流程控制进行学习

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-07-25 8:10
 */
public class Kuang_Process_control
{
    public static void main(String[] args)
    {
        //增强For循环
        int[] numbers = {10,02,30,40,50};

        //普通循环
        for(int i=0;i<5;i++)
        {
            System.out.println(numbers[i]);
        }
        System.out.println("**********************************");
        //增强循环
        for(int j:numbers)
        {
            System.out.println(j);
        }

        /*
        //打印九九乘法表
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<=i;j++)
            {
                if(j==i)
                    System.out.println(i+"×"+j+"="+i*j);
                else
                    System.out.print(i+"×"+j+"="+i*j+"\t");
            }
        }

        //For语句
        //输出0-1000之间可以被5整除的数，且每三个进行一次换行
        int flag = 0;
        for (int i = 1; i < 1000; i++)
        {
            if(i%5==0 && flag%3==0)
            {
                System.out.println(i);
                flag++;
            }
            else if(i%5==0 && flag%3!=0)
            {
                System.out.print(i+"\t");
                flag++;
            }
            else
            {
                //不做任何处理
            }
        }



        //case语句    JDK7新特性:匹配字符串
        String name = "亱華";
        switch(name)
        {
            case "kuang":
                System.out.println("Kuang");
                break;                          //※ 一定要添加break来防止case穿透
            case "亱華":
                System.out.println("亱華");
                break;
            default:
                System.out.println("UnKnown");
                break;
        }

        //Scanner类
        //Shift+Tab     缩行
        //反编译程序     Project structure下project compiler output找打文件用IDEA打开
        //程序判断语句
        Scanner scan = new Scanner(System.in);
        if(scan.hasNextLine())
        {
            String str = scan.nextLine();
            if(str.equals("Hello"))                         //※ equals
            {
                System.out.println(str);
            }
            else
                System.out.println("End");

        }

        //判断是否有对应整数输入
        Scanner scan = new Scanner(System.in);
        if(scan.hasNextInt())
        {
            int i = scan.nextInt();
            System.out.println("输入的整数为:"+i);
        }
        else
            System.out.println("输入的不是整数!");
        scan.close();

        //Scanner 对象 在读取之前可以使用hasNext()判断是否还有输入的数据
        Scanner scan = new Scanner(System.in);
        if(scan.hasNext())
        {
            //next() -> 以空格作为分隔符    nextline() -> 以回车作为分隔符
            //String str = scan.next();                                   //hello world -> hello
            String str = scan.nextLine();                                 //hello world -> hello world
            System.out.println("输出的内容为:"+str);
        }
        //※ 凡是属于IO流的类若不关闭会占用资源，因此在使用完成之后将其关闭
        scan.close();
        */
    }
}
