package com.atguigu.java_advanced_programming.reflection.proxy;

import sun.applet.Main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;

/**
 * @author LYHstart
 * @create 2021-09-05 18:51
 *
 * 动态代理举例
 *
 * 附：1.代理模式要求有一个接口与代理类、被代理类的存在
 *    2.这里动态代理类的含义：使得代理类动态化
 */
interface Human{
    String getBelief();

    void eat(String food);
}

//被代理类
class SuperMan implements Human
{
    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}

/*
要想实现动态代理，需要解决的问题?
问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象
问题二：当通过代理类的对象调用方法时，如何动态的取调用被代理类中的同名方法

 */
//解决问题一
class ProxyFactory
{
    //调用此方法，返回一个代理类对象          //被代理类对象
    public static Object getProxyInstance(Object obj)
    {
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);  //传入被代理类对象

        //参数： 参数1(类加载器)     参数2(类接口)      参数3(...) <- 解决问题二
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler
{
    private Object obj; //需要被代理类的对象进行赋值

    public void bind(Object obj)
    {
        this.obj = obj;
    }
    //当我们通过代理类的对象，调用方法a时，就会自动调用如下的方法:invoke()
    //将被代理类要执行的方法a的功能声明在invoke()方法中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method：即为代理类对象调用的方法，此方法也就作为被代理类对象要调用的方法
        //obj：被代理类对象
        Object returnValue = method.invoke(obj,args);//代理类对象调用被代理类对象的方法
        //上述方法的返回值作为当前类invoke()的返回值
        return returnValue;
    }
}


public class ProxyTest
{
    public static void main(String[] args) {

        //创建被代理类对象
        SuperMan superMan = new SuperMan();

        //创建代理类对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //通过代理类对象调用方法
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("四川麻辣烫");
    }
}
