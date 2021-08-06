package com.atguigu.java.inner_class;

/**
 * @author LYHstart
 * @create 2021-08-06 9:15
 *
 * 内部类 inner class
 */
public class InnerClassTest
{
    //实例化内部类    静态
    Person.Dog dog = new Person.Dog();

    //实例化内部类    非静态
    //Person.Bird bird = new Person.Bird();    //×
    Person p = new Person();
    //p.Bird bird = new Person.Bird();
    //Person.Bird bird = new p.Bird();
    Person.Bird bird = p.new Bird();
    //bird.eat();
    //bird.sing();          死活无法调用!         //主类必须声明为public ???

}

class Person
{
    String name;
    int age;

    public static void eat()
    {
        System.out.println("人吃饭");
    }
    public void talk()
    {
        System.out.println("人说话");
    }

    //成员内部类
    static class Dog
    {
        String name;
        int age;

        public void eat()
        {
            System.out.println("狗吃狗粮");
            //调用外部类方法
            eat();
            Person.eat();
        }
    }

    class Bird
    {
        String name;
        int age;

        public Bird() {
        }

        public void eat()
        {
            System.out.println("鸟吃小虫");
            //调用外部类方法
            talk();
            Person.this.talk();                 //※
        }
        public void sing()
        {
            System.out.println("lalalala");
        }
    }

}