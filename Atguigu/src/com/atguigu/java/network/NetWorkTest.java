package com.atguigu.java.network;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-05 9:09
 *
 * 接口的应用，代理模式
 */
public class NetWorkTest
{
    @Test
    public void test1()
    {
        Server s = new Server();
        ProxyServer p = new ProxyServer(s);
        p.browse();
    }
}

interface NetWork
{
    public void browse();
}

class Server implements NetWork
{

    @Override
    public void browse() {
        System.out.println("真实的服务器访问网络");
    }
}

class ProxyServer implements NetWork
{
    //代理服务器无法直接访问网络，借助真实服务器访问
    private NetWork netWork;

    //构造器
    public ProxyServer(NetWork netWork)
    {
        this.netWork = netWork;
    }

    private void check()
    {
        System.out.println("联网之前的检查工作");
    }

    @Override
    public void browse() {
        //联网前检查
        check();
        //代理使用真实服务器访问网络
        this.netWork.browse();
    }
}
