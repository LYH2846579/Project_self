package com.atguigu.java_advanced_programming.thread_control.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LYHstart
 * @create 2021-08-14 9:17
 *
 *  ※balabala.var     ->  快速创建对象
 */
public class Executors_ThreadPool
{
    public static void main(String[] args)
    {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //execute可以执行实现Runnable接口的实现类对象的run()方法，且以多线程方式执行
        service.execute(new NumberThread());
        //submit不仅可以执行实现Runnable接口的实现类对象的run()方法，还可以执行实现Callable接口的实现类方法
        service.submit(new NumberThread());     //可以有返回值

        //关闭线程池
        service.shutdown();
    }
}

class NumberThread implements Runnable
{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++)
        {
            if(i%2 == 0)
            {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
