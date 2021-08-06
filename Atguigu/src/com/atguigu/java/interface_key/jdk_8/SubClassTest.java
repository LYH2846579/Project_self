package com.atguigu.java.interface_key.jdk_8;

/**
 * @author LYHstart
 * @create 2021-08-05 10:58
 */
public class SubClassTest
{
    public static void main(String[] args)
    {
        SubClass s = new SubClass();
        //① 接口中定义的静态方法，只能通过接口来调用
        CompareA.method1();
        //② 接口中定义的默认方法，可以通过对象来调用
        s.method2();
        s.method3();
        //子类对父类的默认方法进行重写
        //在没有重写的情况下，默认调用父类中同名同参数的方法
        //知识点4:如果实现类实现了多个接口,而这多个接口中定义了同名同参数的默认方法，
        // 那么在实现类没有重写此方法的情况下,报错。-->接口冲突。
        //这就需要我们必须在实现类中重写此方法
        s.myMethod();
    }
}


//继承的父类中与接口中有重名方法method3
class SubClass extends SuperClass implements CompareA
{
    //重写接口默认方法2
    public void method2()
    {
        System.out.println("SubClass:深圳");
    }
    //重写接口默认方法3
    public void method3()
    {
        System.out.println("SubClass:香港");
    }

    public void myMethod()
    {
        //调用自定义的重写方法
        method3();
        //调用父类声明的方法
        super.method3();
        //调用接口中默认的方法    //※不可以用接口直接调用
        CompareA.super.method3();
    }
}