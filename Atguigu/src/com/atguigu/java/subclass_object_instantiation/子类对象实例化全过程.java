package com.atguigu.java.subclass_object_instantiation;

/**
 * @author LYHstart
 * @create 2021-07-28 8:08
 *
 * 1.从结果上来看：
 *      子类继承父类之后，就获取了父类中声明的属性和方法。
 *      创建子类对象，在堆空间中就会加载所有父类声明的属性
 *
 * 2.从过程上来看：
 *      当我们调用子类的构造器来创建子类对象时，一定会直接或间接调用其父类的构造器，进而调用父类的父类的构造器，
 *      直到调用了Java.lang.Object类中空参的构造器为止。正因为加载过所有父类的结构，所有才可以看到内存中有
 *      父类的结构，子类对象才可以考虑进行调用。
 *
 * 附：子类创建过程之中，仅创建了一个对象，即为new的子类对象。
 *
 *
 *
 */
public class 子类对象实例化全过程
{
}
