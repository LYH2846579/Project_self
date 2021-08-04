package com.atguigu.java.wrapper_class;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author LYHstart
 * @create 2021-07-30 12:15
 * 包装类和String、基本数据类型之间的转换练习
 */
public class Exercise
{
    Vector v = new Vector();
    int num;    //记录输入的数据
    int max=Integer.MIN_VALUE;
    Object o;
    int temp;   //保存取出的数据
    int dif; //计算差值

    public static void main(String[] args)
    {
        Exercise e = new Exercise();
        e.input();
        e.output();
    }

    public void input()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学生成绩:");
        while(true)
        {
            if(scan.hasNextInt())
            {
                num = scan.nextInt();
                if(num > max)
                    max = num;
                v.add(num);
            }
            else
                break;
        }
        scan.close();
    }
    public void output()
    {
        int length = v.size();
        for(int i=0;i<length;i++)
        {
            o = v.elementAt(i);
            temp = (int)o;
            dif = max - temp;
            if(dif <= 10)
                System.out.println("第"+i+"名同学的成绩为:"+temp+"等级为:A");
            else if(dif >10 && dif<=20)
                System.out.println("第"+i+"名同学的成绩为:"+temp+"等级为:B");
            else if(dif >20 && dif<=30)
                System.out.println("第"+i+"名同学的成绩为:"+temp+"等级为:C");
            else
                System.out.println("第"+i+"名同学的成绩为:"+temp+"等级为:D");
        }
    }
}
