package com.atguigu.java.permission_modifier;

/**
 * @author LYHstart
 * @create 2021-07-27 11:45
 * 四种不同的权限修饰符
 */
public class OrderTest
{
    public static void main(String[] args) {
        Order o = new Order();

        //直接访问变量
        o.orderPublic = 1;
        o.orderProtected = 2;
        o.orderDefault = 3;

        //方法访问
        o.methodDefault();
        o.methodProtected();
        o.methodPublic();
    }
}
