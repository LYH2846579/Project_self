package com.atguigu.java.static_key;

/**
 * @author LYHstart
 * @create 2021-08-01 9:37
 * 单例设计模式
 */
public class Singleton_design
{
    public static void main(String[] args)
    {
        Singleton_1 s1 = Singleton_1.getInstance();
        Singleton_1 s2 = Singleton_1.getInstance();
        System.out.println(s1 == s2);
    }
}

//饿汉式设计模式   无参
class Singleton_1
{
    //1.私有化构造器
    private Singleton_1()
    {

    }

    //2.内部提供一个当前类的实例
    //4.此实例也必须静态化!
    private static Singleton_1 sin = new Singleton_1();            //在类内部声明一个变量，作为类的属性

    //3.提供公共的静态方法，返回当前类党的对象
    public static Singleton_1 getInstance()
    {
        return  sin;
    }
}

//懒汉式设计模式   有参          //存在线程不安全问题
class Singleton_2
{
    //类属性
    String name;
    int age;

    //1.私有构造器
    private Singleton_2(String name,int age)
    {
        this.name = name;
        this.age = age;
    }

    //2.内部对象创建      //初始为null
    private static Singleton_2 sin = null;

    //3.提供公共的静态方法，返回当前的对象
    public Singleton_2 getInstance(String name,int age)
    {
        if(sin == null)
            sin = new Singleton_2(name,age);
        return sin;
    }
}


















//单例模式复习        //饿汉式
class Testton_1
{
    //1.私有化构造器
    private Testton_1()
    {

    }

    //2.提供实例化对象属性
    static Testton_1 t1 = new Testton_1();

    //3.返回实例化对象
    public static Testton_1 getInstance()
    {
        return t1;
    }
}

//单例模式练习        //懒汉式
class Testton_2
{
    //私有属性
    private String name;
    private int age;

    //私有化构造器    带参构造器
    private Testton_2(String name,int age)
    {
        this.name = name;
        this.age = age;
    }

    //创建类属性所拥有的对象
    static Testton_2 t2 = null;


    //公有化方法返回对象
    public static Testton_2 getInstance(String name,int age)
    {
        if(t2 == null)
        {
            t2 = new Testton_2(name,age);

        }
        return t2;

    }
}














