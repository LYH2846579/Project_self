package com.atguigu.java.polymorphism;

/**
 * @author LYHstart
 * @create 2021-07-28 11:16
 * 多态仅仅适用于方法，并不适用于属性（属性编译运行只看左侧引用类型）
 * 方法编译看左边，运行看右边
 * 多态性：虚拟方法调用
 *
 */
public class AnimalTest
{
    public static void main(String[] args)
    {
        Dog d = new Dog();
        Cat c = new Cat();
        func(d);
        func(c);

    }

    public static void func(Animal a)
    {
        a.eat();
        a.shout();
    }
}

class Animal
{
    public void eat()
    {
        System.out.println("动物，吃");
    }
    public void shout()
    {
        System.out.println("动物，叫");
    }
}

class Dog extends Animal
{
    public void eat()
    {
        System.out.println("狗吃骨头");
    }
    public void shout()
    {
        System.out.println("汪!汪!汪!");
    }
}
class Cat extends Animal
{
    public void eat()
    {
        System.out.println("猫吃鱼");
    }
    public void shout()
    {
        System.out.println("喵!喵!喵!");
    }
}
