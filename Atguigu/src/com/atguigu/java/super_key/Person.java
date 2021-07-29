package com.atguigu.java.super_key;

/**
 * @author LYHstart
 * @create 2021-07-27 14:36
 * spuer关键字使用说明,在子类中重写父类方法之后将父类方法覆盖，使用super调用父类方法
 */
public class Person
{
    String name;
    int age;
    int id; //身份证号

    public Person()
    {

    }
    public Person(String name)
    {
        this.name = name;
    }
    public Person(String name, int age,int id)
    {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public void eat()
    {
        System.out.println("人，吃饭");
    }
    public void walk()
    {
        System.out.println("人，走路");
    }
}
