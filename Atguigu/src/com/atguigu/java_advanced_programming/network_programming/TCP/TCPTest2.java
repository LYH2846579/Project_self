package com.atguigu.java_advanced_programming.network_programming.TCP;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author LYHstart
 * @create 2021-09-03 18:58
 *
 * TCP传输练习
 *
 * ※TCP是基于字节流的传输
 * TCP套接字编程总结:
 * client:
 * ① 创建服务器IP  ->   InetAddress inet = InetAddress.getByName("127.0.0.1");
 * ② 创建套接字 -> Socket socket = new Socket(inet,8899);    IP + port
 * ③ 使用套接字创建对应流资源
 *    InputStream is = socket.getInputStream;
 *    OutputStream os = socket.getOutputStream;
 * ④ 流资源使用
 *    输出流:
 *    os.write("你好，服务器!".getBytes());   //基于字节流
 *    os.flush();                           //强制刷新缓冲区
 *    输入流:
 *    [1] 创建ByteArrayOutputStream对象(处理接收数据)
 *    [2] byte[] buffer = new byte[8];
 *    [3] 读取数据
 *        while((read = is.read(buffer)) != -1)
 *        {
 *            baos.write(buffer,0,read);
 *        }
 *    [4] 读取数据输出
 *        baos.toString().sout
 * ⑤ 资源关闭
 *    socket.close();
 *
 *  server:
 *  ① 创建ServerSocket套接字 -> ServrSocket ss = new ServerSocket(8899);
 *  ② 创建Socket套接字 -> Socket socket = ss.accept();
 *  ③ 生成流对象(与clinet相似)
 *  ④ 处理数据(输入输出)
 *  ⑤ 流资源关闭
 */

public class TCPTest2
{

    //client
    @Test
    public void client() throws IOException
    {
        //InetAddress
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        //socket
        Socket socket = new Socket(inet,8899);

        //流资源加载 输入输出流
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //输入输出
        //输出 ->  向服务器端输出
        os.write("你好，服务器!".getBytes());
        os.flush();     //刷新缓冲区?
        socket.shutdownOutput();    //关闭输出

        //输入 -> 从服务器端接收数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        //获取内容输出
        System.out.println(baos.toString());
        System.out.println("收到来自"+socket.getInetAddress().getHostName()+"的消息");

        //关闭socket
        socket.close();
        //附：socket关闭意味着InputStream和OutputStream同时被关闭
    }


    //server
    @Test
    public void server() throws IOException
    {
        //ServerSocket                    //port
        ServerSocket ss = new ServerSocket(8899);

        //Socket        //accept接收
        Socket socket = ss.accept();

        //流资源加载
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //接收数据 -> 从客户端接收信息
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        //获取内容输出
        System.out.println(baos.toString());
        System.out.println("收到来自"+socket.getInetAddress().getHostName()+"的消息");

        //发送数据 -> 向客户端发送信息
        os.write("你好，客户端，这里是服务器!".getBytes());
        os.flush();

        //资源关闭
        socket.close();

    }
}
