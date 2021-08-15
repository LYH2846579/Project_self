package com.atguigu.java_advanced_programming.thread_control.callable_interface;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author LYHstart
 * @create 2021-08-14 7:49
 *
 * callable实现多线程步骤:
 * (1)类实现Callable接口
 * (2)在实现类中重写Call方法，实现具体需要的功能
 * (3)实例化实现类的对象
 * (4)将此对象作为参数传递到FutureTask构造器中创建FutureTask对象
 * (5)将FutureTask对象作为参数传递到Thread类构造器中实例化对象，调用start()方法
 * (6)使用FutureTask对象的get()方法获取返回值
 */
public class CallableTest
{
    public static void main(String[] args)
    {
        /*                                                //是多线程嘛!    ↓
        NubThread n = new NubThread();            //直接使用callable接口实现类的实例化对象调用call()方法
        try                                       //由于会抛出异常，需要使用try-catch进行处理
        {
            Object o = n.call();                  //※必须使用Object类对象作为接受
            if(o instanceof Integer)
                System.out.println("sum:"+(int)o);
            else
                System.out.println("数据转换错误");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        */
        //借助FutureTask类
        NubThread n = new NubThread();
        FutureTask f = new FutureTask(n);
        //再次借助Thread类
        Thread t1 = new Thread(f,"线程一");
        Thread t2 = new Thread(f,"线程二");
        t1.start();
        t2.start();
        //get()方法需处理异常
        try
        {
            Object o = f.get();
            if(o instanceof Integer)
                System.out.println("sum:"+(int)o);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

    }


}

class NubThread implements Callable
{
    @Override
    public Object call() throws Exception {
        //遍历一百以内的所有素数，并返回所有素数的和
        int sum = 0;
        for (int i = 2; i < 1000; i++)
        {
            boolean flag = true;
            for (int j = i-1; j > 1; j--)
            {
                if(i%j == 0)
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum += i;
            }
        }
        return sum;         //返回值为Integer类型，设计自动拆箱与装箱
    }
}
