package com.atguigu.java.static_key;

/**
 * @author LYHstart
 * @create 2021-08-01 8:32
 *
 * static关键字的使用
 *
 * 1.static是静态的
 * 2.static可以修饰属性和方法，不可以修饰构造器
 * 3.使用static修饰属性，静态变量(或类变量)
 *      3.1实例变量:我们创建了类的多个对象，每个对象都独立的拥有-套类中的非静态属性。当修改其中一个对象中的
 *                 非静态属性时，不会导致其他对象中同样的属性值的修改。
 *         静态变量:我们创建了类的多个对象，多个对象共享同一一个静态变量。当通过某一个对象修改静态变量时，会导致
 *                 其他对象调用此静态变量时，是修改过了的。
 *
 *      3.2 static修饰属性的其他说明:
 *          ①静态变量随着类的加载而加载。可以通过"类.静态变量"的方式进行调用
 *          ②静态变量的加载要早于对象的创建。
 *          ③由于类只会加载一-次，则静态变量在内存中也只会存在一份:存在方法区的静态域中。
 *
 *      3.3 静态属性举例:System.out; Math.PI;
 */
public class StaticTest
{
    public static void main(String[] args)
    {
        Chinese c1 = new Chinese();
        c1.name = "姚明";
        c1.age = 40;
        c1.nation = "Chinese";


        Chinese c2 = new Chinese();
        c2.name = "马龙";
        c2.age = 30;
    }
}

class Chinese
{
    String name;
    int age;

    static String nation;
}