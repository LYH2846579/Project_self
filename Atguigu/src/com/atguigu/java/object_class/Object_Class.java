package com.atguigu.java.object_class;


import com.sun.org.apache.xpath.internal.operations.Equals;

import java.sql.Date;
import java.util.Objects;

/**
 * @author LYHstart
 * @create 2021-07-29 11:37
 *        == 与 equals()
 *
 * Object方法调用
 *
 *1. Object 类中equals()方法定义
 *   public boolean equals(Object obj)
 *  {
 *       return (this == obj)
 *  }
 *  说明:Object类中定义的equals()和==的作用是相同的，比较两个对象的地址值是否相同，即两个引用是否指向同一个对象
 *
 *2. 附：像String、DATE、File、包装类等都重写了Object类中的equals()方法，重写以后，比较的不是两个引用的地址是否相同，
 *   而是比较另个对象的"实体内容"是否相同
 *
 *3. 通常情况下，使用自定义类调用equals()方法，是想进行对象内容比较，因此需要对Object类中equals()方法重写
 *
 *4. 附：比较引用数据类型一定调用equals()方法!                ※
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

        //对于基本数据类型
        int i1 = 1;
        double d1 = 1.0;
        System.out.println("i1 == d1:"+(i1==d1));       //ture
        //float == int
        float f1 = 1.0f;
        System.out.println("i1 == f1:"+(i1==f1));       //ture
        //float == double
        System.out.println("f1 == d1:"+(f1==d1));       //ture
        //char
        char c1 = 65;
        System.out.println("c1 == 65:"+(c1==65));       //ture
        char c2 = 'A';
        System.out.println("c1 == c2:"+(c1==c2));       //ture

        //引用数据类型
        //类
        Customer cust1 = new Customer("Tom",12);
        Customer cust2 = new Customer("Tom",12);
        System.out.println("cust1 == cust2:"+(cust1 == cust2));             //false
        System.out.println("cust1.equals(cust2):"+cust1.equals(cust2));     //false
        //String
        String str1 = new String("atguigu");
        String str2 = new String("atguigu");
        System.out.println("str1 == str2:"+(str1 == str2));                 //false
        System.out.println("str1.equals(str2):"+str1.equals(str2));         //true      String类中重写了equals方法
        //Date
        Date date1 = new Date(3245678989L);
        Date date2 = new Date(3245678989L);
        System.out.println("date1 == date2:"+(date1 == date2));             //true      Date类中重写了equals方法
    }
}

class Order
{

}

class Customer
{
    String name;
    int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //自动生成equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;           //强制类型转换
        return age == customer.age &&
                Objects.equals(name, customer.name);
    }

    //重写equals()
    //@Override
    public boolean equals(Customer cust) {
        //优化判断      ->      是否为同一个对象
        if(this == cust)
            return true;
//        if(this.age == cust.age && this.name == cust.name)            //※注意!String为引用数据类型!
        //优化判断      ->      是否为同一个类型
        if(cust instanceof Customer)
        {
//            if(this.age == cust.age && this.name.equals(cust.name))         //!!!
//                return true;
//            else
//                return false;
            return (this.age == cust.age && this.name.equals(cust.name));     //再优化
        }
        else
            return false;
    }

    //重写toString()
    @Override
    public String toString() {
        return "[name:"+name+",age:"+age+"]";
    }
}
