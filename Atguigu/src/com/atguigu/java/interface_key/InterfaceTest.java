package com.atguigu.java.interface_key;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-04 10:54
 *
 *           接口
 *  ①接口中不可以定义构造器，意味着接口不可以进行实例化
 *  ②JAVA开发中，接口通道让类去实现（implements)的方式来使用
 *  ③若实现类覆盖了接口中所有的抽象方法，则此实现类可以实例化
 *
 *  JAVA类可以实现多接口    -->  弥补了JAVA单继承的局限性
 *
 *
 */
public class InterfaceTest
{
    @Test
    public void test1()
    {
        System.out.println(Flyable.MAX_SPEED);
        System.out.println(Flyable.MIN_SPEED);

        plane p = new plane();
        p.fly();
    }

    @Test   //多态性体现
    public void test2()
    {
        //创建接口的非匿名实现类的非匿名对象
        Flash f = new Flash();
        computer c = new computer();
        c.transferData(f);
        System.out.println("*********************");
        //创建接口的非匿名实现类的匿名对象
        c.transferData(new Printer());
        System.out.println("*********************");
        //创建接口的匿名实现类的匿名对象
        c.transferData(new USB(){                   //类似Lambda表达式

            @Override
            public void start() {
                System.out.println("匿名实现类开始工作");
            }

            @Override
            public void stop() {
                System.out.println("匿名实现类终止工作");
            }
        });                                         //分号一定不能忘记
        System.out.println("*********************");
        //创建接口的匿名实现类的非匿名对象
        USB phone = new USB()
        {
            @Override
            public void start() {
                System.out.println("匿名对象phone开始工作");
            }

            @Override
            public void stop() {
                System.out.println("匿名对象phone结束工作");
            }
        };
        c.transferData(phone);
    }
}

//可以飞行接口
interface Flyable
{
    //定义变量
    public static final int MAX_SPEED = 7900;
    //省略关键字定义方式
    int MIN_SPEED = 1;

    //定义抽象方法
    public abstract void fly();
    //省略定义方式
    void stop();
}

//attack
interface Attackable
{
    void attack();
}


//使用接口
class plane implements Flyable
{

    @Override
    public void fly() {
        System.out.println("通过引擎起飞");
    }

    @Override
    public void stop() {
        System.out.println("通过反推停止");
    }
}

//多接口
class Bullet implements Attackable,Flyable
{

    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void attack() {

    }
}



//多态性
class computer
{                   //使用接口实现多态性     调用实现类方法
    public void transferData(USB usb)
    {
        usb.start();
        System.out.println("数据传输中");
        usb.stop();
    }
}



interface USB
{
    //常量定义：长、宽、高、最大传输速度

    //方法
    void start();

    void stop();
}

//U盘
class Flash implements USB
{

    @Override
    public void start() {
        System.out.println("U盘开启数据传输");
    }

    @Override
    public void stop() {
        System.out.println("U盘关闭数据传输");
    }
}
//打印机
class Printer implements USB
{

    @Override
    public void start() {
        System.out.println("打印机开始打印");
    }

    @Override
    public void stop() {
        System.out.println("打印机终止打印");
    }
}
