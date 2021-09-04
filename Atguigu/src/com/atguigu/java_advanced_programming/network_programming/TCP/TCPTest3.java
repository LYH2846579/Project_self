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
 * @create 2021-09-04 9:09
 *
 * //在客户端与服务器之间实现更多的交互(尝试)
 *
 * 附：
 *  ① 在一次输出之后，若使用socket.shutdownOutput指令，会使得socket输出流彻底关闭，不得再次输出!
 *  ② 若在输出之后未使用该指令，则造成"死锁现象"
 */
public class TCPTest3
{
    @Test
    public void client() throws IOException
    {
        //Inet
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        //socket
        Socket socket = new Socket(inet,8899);

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //向服务器发送第一条消息
        os.write("你好，服务器!".getBytes());
        os.flush();
        socket.shutdownOutput();

        //接收来自服务器的消息
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        //将接收的消息输出
        System.out.println(baos.toString());

        //再次发送消息
        os.write("请求响应!".getBytes());   //Cannot send after socket shutdown: socket write error！
        os.flush();
        socket.shutdownOutput();

        //再次接收消息
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        byte[] buffer1 = new byte[8];
        int read1;
        while((read1 = is.read(buffer1)) != -1)
        {
            baos1.write(buffer1,0,read1);
        }
        //将接收的消息输出
        System.out.println(baos1.toString());

        //关闭套接字
        socket.close();

    }

    @Test
    public void server() throws IOException
    {
        //ServerSocket
        ServerSocket ss = new ServerSocket(8899);
        //Socket
        Socket socket = ss.accept();

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //接收来自客户端的消息
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        //输出
        System.out.println(baos.toString());

        //向客户端发送消息
        os.write("你好，客户端，这里是服务器!".getBytes());
        os.flush();
        //未关闭流  ->  关闭流
        socket.getOutputStream();

        //再次接收消息
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        byte[] buffer1 = new byte[8];
        int read1;
        while((read1 = is.read(buffer1)) != -1)
        {
            baos1.write(buffer1,0,read1);
        }
        //将接收的消息输出
        System.out.println(baos1.toString());

        //再次发送消息
        os.write("请求得到响应!".getBytes());
        os.flush();



        //关闭套接字
        socket.close();
    }

}
