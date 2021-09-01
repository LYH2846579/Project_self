package com.atguigu.java_advanced_programming.network_programming;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author LYHstart
 * @create 2021-09-01 22:14
 *
 * IP地址及端口号
 */
public class InetAddressTest
{
    @Test
    public void test1()
    {
        try
        {
            //实例化InetAddress对象
            //File file = new File("Hello.txt");
            InetAddress inet1 = InetAddress.getByName("192.168.17.1");
            System.out.println(inet1);
            //需要DNS解析
            InetAddress inet2 = InetAddress.getByName("www.atguigu.com");
            System.out.println(inet2);
            //获取本地IP
            InetAddress inet3 = InetAddress.getLocalHost();
            System.out.println(inet3);

            //方法调用
            System.out.println(inet2.getHostName());
            System.out.println(inet2.getHostAddress());

        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}
