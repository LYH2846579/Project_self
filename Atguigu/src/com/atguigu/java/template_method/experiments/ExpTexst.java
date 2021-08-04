package com.atguigu.java.template_method.experiments;

import org.junit.Test;

import java.util.Calendar;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-08-03 20:13
 */
public class ExpTexst
{
    public static void main(String[] args) {
        test1();
    }

//    @Test
    public static void test1()
    {
        PayrollSystem p = new PayrollSystem();
        p.emp[0] = new SalariedEmployee("jone","0002", new MyDate(2021,8,4),2000);
        p.emp[1] = new SalariedEmployee("Tom","00003",new MyDate(2020,8,4),3000);
        p.emp[2] = new HourlyEmployee("jurry","00004",new MyDate(2001,8,4),167,12);
        print(p);
    }

    public static void print(PayrollSystem p)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入一个月份:");
        int month = scan.nextInt();
        //System.out.println(p.emp.length);

        for(int i=0;i<3;i++)                //小心空指针异常
        {
            Employee e = p.emp[i];
            e.toString();

            if(e instanceof HourlyEmployee)
            {
                if(e.getBirthday().getMonth() == month)
                {
                    System.out.println(" wage:"+e.earnings()+100);
                }
            }
            else if(e instanceof SalariedEmployee)                      //使用多态之后，无需进行类别检测
            {
                if(e.getBirthday().getMonth() == month)
                {
                    System.out.println(" monthlySalary:"+((int)e.earnings()+100));
                }
            }
        }
    }
}
