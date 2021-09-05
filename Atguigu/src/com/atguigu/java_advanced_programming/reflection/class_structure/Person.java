package com.atguigu.java_advanced_programming.reflection.class_structure;

/**
 * @author LYHstart
 * @create 2021-09-05 10:01
 */
@MyAnnotation(value = "Hi")
public class Person extends Creature<String> implements Comparable,MyInterface
{
    private String name;
    int age;
    public int id;

    public Person(){}

    @MyAnnotation(value = "Constructor")
    private Person(String name)
    {
        this.name = name;
    }

    Person(String name,int age)
    {
        this.name = name;
        this.age = age;
    }

    private String show(String nation)
    {
        System.out.println("我的国籍是:"+nation);
        return nation;
    }

    @MyAnnotation(value = "Method")
    public String display(String interests) throws RuntimeException,ClassNotFoundException
    {
        return interests;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }
}
