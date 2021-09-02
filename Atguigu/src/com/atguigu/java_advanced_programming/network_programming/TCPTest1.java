package com.atguigu.java_advanced_programming.network_programming;

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
 * @create 2021-09-02 20:25
 *
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务器将数据显示在控制台上
 *
 * //客户端
 *  ① 创建服务器IP地址InetAddress对象    InetAddress inet = InetAddress.getByName("127.0.0.1");
 *  ② 创建套接字对象 IP+port            Socket socket = new Socket(inet,8899);
 *  ③ 加载流资源(输出流)                OutputStream os = socket.getOutputStream;
 *  ④ 使用流资源处理                    os.Write("balabala");
 *  ⑤ 流资源关闭                       os.close;     socket.close;
 *
 *  //服务器端
 *  ① 创建ServerSocket对象      ServerSocket ss = new ServerSocket(port);
 *  ② 创建Socket对象            Socket socket = ss.accept();
 *  ③ 创建接收流(输入流)         InputStream is = socket.getInputStream;
 *  ④ 接受流处理                ByteArrayOutputStream baos = new ByteArrayOutputStream();
 *
 *  附：使用ByteArrayOutputStream接收，创建buffer数组 -> baos.write(buffer);
 */
public class TCPTest1
{
    //client
    @Test
    public void client()
    {
        Socket socket = null;
        OutputStream os = null;
        try
        {
            //目的IP地址                    //InetAddress类不能使用new
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            //套接字socket     //IP + port
            socket = new Socket(inet, 8899);

            //流资源
            os = socket.getOutputStream();
            os.write("你好，我是客户端".getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //资源关闭
            try
            {
                if(os != null)
                    os.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(socket != null)
                    socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    //server
    @Test
    public void server() throws IOException
    {
        //创建SocketServer对象
        ServerSocket ss = new ServerSocket(8899);
        //创建Socket对象
        Socket socket = ss.accept();
        //创建InputStream流
        InputStream is = socket.getInputStream();

        //读取数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }

        System.out.println(baos.toString());
        //输出来源IP
        System.out.println(socket.getInetAddress().getHostAddress());

    }
}
