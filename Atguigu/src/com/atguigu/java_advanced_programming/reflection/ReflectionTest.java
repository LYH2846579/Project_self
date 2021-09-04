package com.atguigu.java_advanced_programming.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author LYHstart
 * @create 2021-09-04 16:13
 *
 * 反射所带来的问题:
 * ① 使用new还是reflection? -> 建议使用new (当创建的对象不确定的时候，采取反射形式创建)
 * ② 反射机制与之前面向对象的封装性矛盾吗？
 *    不矛盾
 */
public class ReflectionTest
{
    @Test //在反射之前，对于Person的操作
    public void test1()
    {
        //1.创建Person类对象
        Person p1 = new Person("Tom",12);

        //2.通过对象调用其内部属性、方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //如：name,showNation()及私有的构造器
    }

    @Test  //使用反射之后，对于Person的操作
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException
    {
        Class clazz = Person.class;
        //1.通过反射，创建Person类对象
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom",12);
        Person p = (Person)obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性、方法
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //通过反射，可以调用私有结构
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1.toString());

        //调用私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");

        //调用私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String str = (String) showNation.invoke(p1, "中国");//返回值与原方法相同
        System.out.println(str);
    }

    @Test   //获取Class的实例的方式
    public void test3() throws ClassNotFoundException
    {
        //方式一:调用运行时类属性: .class
        Class<Person> clazz1 = Person.class;   //指出泛型
        System.out.println(clazz1);
        //方式二:通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class<? extends Person> clazz2 = p1.getClass();     //泛型 标识符?
        System.out.println(clazz2);
        //方式三:调用Class的静态方法: forName(String classPath); //完整路径 ※
        Class clazz3 = Class.forName("com.atguigu.java_advanced_programming.reflection.Person"); //reference
        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        //判断   -> 加载到内存中的运行时类，会缓存一定时间，可以使用不同方法获取
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四:使用类的加载器
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atguigu.java_advanced_programming.reflection.Person");
        System.out.println(clazz4);

    }
}

class Person
{
    private String name;
    public int age;

    public Person() {
    }
    //私有单参数构造器
    private Person(String name) {
        this.name = name;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void show()
    {
        System.out.println("你好，我是一个人");
    }
    private String showNation(String nation)
    {
        System.out.println("我是"+nation+"人");
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
