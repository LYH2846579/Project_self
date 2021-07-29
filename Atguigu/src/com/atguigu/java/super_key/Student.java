package com.atguigu.java.super_key;

/**
 * @author LYHstart
 * @create 2021-07-27 14:37
 */
public class Student extends Person
{
    String major;
    int id;//学号

    public Student() {
    }

    public Student(String major,int id)
    {
        this.major = major;
        this.id = id;
    }
    public Student(String major,String name,int age,int id1,int id2)
    {
        //super调用构造器
        super(name,age,id1);
        this.major = major;
        this.id = id2;
    }

    @Override
    public void eat()
    {
        System.out.println("学生，多补充营养");
    }
    //私有方法
    public void study()
    {
        System.out.println("学生，学习知识");
    }
    public void show()
    {
        //super调用父类属性
        System.out.println("name = " + this.name+"  age = " + super.age);
    }
    public void print()
    {
        //super调用父类属性
        System.out.println("super.id = "+super.id+"  this.id = "+this.id);
        //super调用父类方法
        this.eat();
        super.eat();
    }
}
