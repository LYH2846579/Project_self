package com.atguigu.java.block;

/**
 * @author LYHstart
 * @create 2021-08-01 11:13
 *
 * 代码块:
 * 类的四大成员之一：代码块
 * 1.代码块作用:用来初始化类、对象
 * 2.只能使用static修饰
 *      静态代码块     vs      动态代码块
 *     static {...}             {...}
 *
 * 3. 静态代码块
 *      >内部可以有输出语句
 *      >随着类的加载而执行,且只执行一次(除非类被重新加载)
 *      >作用：可以在加载类的时候对类属性进行初始化
 *
 * 4. 非静态代码块
 *      >内部可以有输出语句
 *      >随着对象的创建而执行
 *      >每创建一个对象就执行一次
 *      >作用：可以在创建对象时对对象的属性进行初始化
 */
public class BlockTest
{
    public static void main(String[] args) {
        String desc = Person.desc;          //当使用到类属性或方法的时候，加载类
        Person p1 = new Person();
        Person p2 = new Person();
    }
}

class Person
{
    //属性
    String name;
    int age;
    static String desc = "我是一个人";

    //构造器
    public Person()
    {

    }
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    //代码块
    //静态代码块
    static
    {
        System.out.println("Hello static!");
    }
    //非静态代码块
    {
        System.out.println("Hello block");
        //初始化属性
        this.age = 1;
    }


    //方法
    public void eat()
    {
        System.out.println("吃饭");
    }

    @Override
    public String toString()
    {
        return "Person{" + "name='" + name + '\'' + ", age=" + age +'}';
    }
}
