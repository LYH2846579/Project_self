package com.atguigu.java_advanced_programming.thread_control.exp_product;

/**
 * @author LYHstart
 * @create 2021-08-13 11:53
 *
 * 线程通信的应用:经典例题:生产者/消费者问题
 * 生产者(Productor)将产品交给店员(CLerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产;如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 * 分析:
 *  1.是否是多线程问题?是，生产者线程,消费者线程
 *  2．是否有共享数据?是，店员（或产品）
 *  3．如何解决线程的安全问题?同步机制,有三种方法
 *  4．是否涉及线程的通信?是
 *
 *
 * 附：这里在Clerk类中对方法进行实现，在Productor和Customer中实现Runnable接口实现
 *    公用一个Clerk对象
 */
public class ProductTest
{
    public static void main(String[] args) {
        //实例化店员对象
        Clerk clerk = new Clerk();
        //实例化生产者和消费者
        Productor p = new Productor(clerk);
        Customer c = new Customer(clerk);
        //实例化Thread对象
        Thread t1 = new Thread(p,"生产者一号");
        Thread t2 = new Thread(c,"消费者二号");

        t1.start();
        t2.start();
    }
}

class Clerk
{
    private int productNum = 0;

    public synchronized void addProduct() {
        if(productNum < 20)
        {
            //释放
            notify();
            productNum++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+productNum+"号产品");
        }
        else
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void minusProduct() {
        if(productNum > 0)
        {
            //释放
            notify();
            System.out.println(Thread.currentThread().getName()+"购买了第"+productNum+"号产品");
            productNum--;
        }
        else
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Productor implements Runnable
{
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true)
        {
            //阻塞
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

class Customer implements Runnable
{
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true)
        {
            //阻塞
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            clerk.minusProduct();
        }
    }
}
