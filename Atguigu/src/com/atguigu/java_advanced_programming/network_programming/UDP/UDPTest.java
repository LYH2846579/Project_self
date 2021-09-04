package com.atguigu.java_advanced_programming.network_programming.UDP;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author LYHstart
 * @create 2021-09-04 11:01
 *
 * 使用UDP进行网络通信
 * 流程:DatagramSocket 和 DatagramPacket
 */
public class UDPTest
{
    @Test
    public void sender() throws IOException
    {
        //不指定IP和端口
        DatagramSocket ds = new DatagramSocket();
        //在Packet中指定
        byte[] buffer = "这是使用UDP协议传输的字符串!".getBytes();
        DatagramPacket dp = new DatagramPacket(buffer,0,buffer.length, InetAddress.getByName("127.0.0.1"),9090);

        //发送数据报
        ds.send(dp);

        //资源关闭
        ds.close();
    }

    @Test
    public void receiver() throws IOException
    {
        //创建DatagramSocket和DatagramPacket对象
        DatagramSocket ds = new DatagramSocket(9090);   //接收端指明端口号

        byte[] buffer = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buffer,buffer.length);

        //接收数据
        ds.receive(dp);
        String str = new String(dp.getData(),0,dp.getLength());
        System.out.println(str);

        //关闭资源
        ds.close();
    }
}
