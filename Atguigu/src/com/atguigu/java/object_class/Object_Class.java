package com.atguigu.java.object_class;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author LYHstart
 * @create 2021-07-29 11:37
 * Object方法调用
 *
 * Object 类中equals()方法定义
 * public boolean equals(Object obj)
 *{
 *     return (this == obj)
 *}
 * 说明:Object类中定义的equals()和==的作用是相同的，比较两个对象的地址值是否相同，即两个引用是否指向同一个对象
 *
 *附：像String、DATE、File、包装类等都重写了Object类中的equals()方法，重写以后，比较的不是两个引用的地址是否相同，
 * 而是比较另个对象的"实体内容"是否相同
 *
 *
 */
public class Object_Class
{
    public static void main(String[] args)
    {
        Order o = new Order();
        //输出父类          默认为java.lang.Object
        System.out.println(o.getClass().getSuperclass());

        //Object方法调用
        //Order o1 = (Order)o.clone();      //???


    }
}

class Order
{

}
