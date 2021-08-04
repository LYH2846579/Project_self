package com.atguigu.java.anonymous_class;

/**
 * @author LYHstart
 * @create 2021-08-02 7:58
 * 匿名类、匿名对象
 */
public class PersonTest
{
    public static void main(String[] args)
    {
        //匿名的对象
        method(new Student());

        //非匿名的类非匿名的对象
        Worker worker = new Worker();
        method1(worker);    //多态

        //非匿名的类匿名对象
        method1(new Worker());

        //创建了一匿名子类的对象       只用一次
        Person p = new Person()
        {
            @Override
            void eat() {

            }
            @Override
            void walk() {
            }
        };                  //※注意分号

        method1(p);

    }

    //方法
    public static void method(Student s)
    {
        System.out.println("学生要好好吃饭");
    }
    public static void method1(Person p)
    {
        System.out.println("员工认真工作");
    }

}

abstract class Person
{
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    abstract void eat();
    abstract void walk();
}

class Worker extends Person
{

    @Override
    void eat() {
    }

    @Override
    void walk() {
    }
}

class Student extends Person
{

    @Override
    void eat() {

    }

    @Override
    void walk() {

    }
}
