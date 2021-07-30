package com.atguigu.java.object_class;

import java.sql.Date;

/**
 * @author LYHstart
 * @create 2021-07-30 10:04
 */
public class tostring_method
{
    public static void main(String[] args) {
        Customer cust1 = new Customer("Tom",17);
        System.out.println(cust1.toString());           //com.atguigu.java.object_class.Customer@1b6d3586
        System.out.println(cust1);                  //附：println本质调用toString函数!

        //String重写toString函数
        String s = new String("LYH");
        System.out.println(s);
        //Date重写toString函数
        Date d = new Date(216123466893L);
        System.out.println(d);
    }
}
