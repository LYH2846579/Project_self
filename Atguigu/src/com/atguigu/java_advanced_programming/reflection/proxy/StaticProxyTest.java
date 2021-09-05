package com.atguigu.java_advanced_programming.reflection.proxy;

import java.net.Proxy;

/**
 * @author LYHstart
 * @create 2021-09-05 18:41
 *
 * 静态代理举例
 *
 * 特点：代理类和被代理类在编译期间，就确定下来了
 */

interface ClothFactory
{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory
{
    //私有化被代理类对象      //用被代理类对象进行实例化
    private ClothFactory factory;
    //构造器
    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些收尾工作");
    }
}

class NikeClothFactory implements ClothFactory
{
    @Override
    public void produceCloth() {
        System.out.println("Nike生产了一批运动服");
    }
}

//静态代理模式举例
public class StaticProxyTest
{
    public static void main(String[] args) {
        //1.创建被代理类对象
        NikeClothFactory nike = new NikeClothFactory();

        //2.创建代理类对象     //当然也可以使用接口进行指向
        ProxyClothFactory proxyCF = new ProxyClothFactory(nike);
        //ClothFactory cf = new ProxyClothFactory(nike);

        proxyCF.produceCloth();
    }
}


