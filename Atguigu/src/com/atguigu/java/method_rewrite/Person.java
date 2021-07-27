package com.atguigu.java.method_rewrite;

/**
 * @author LYHstart
 * @create 2021-07-27 9:02
 */
public class Person
{
    String name;
    int age;

    public Person()
    {

    }
    public Person(String name,int age)
    {
        this.name = name;
        this.age = age;
    }
    public void eat()
    {
        System.out.println("吃饭");
    }
    public void walk(int distance)
    {
        System.out.println("走路，走了"+distance+"公里");
    }
}
