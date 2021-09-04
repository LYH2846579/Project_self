package com.atguigu.java_advanced_programming.network_programming.TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-09-04 9:35
 *
 * 在客户端发送一个文件，服务器端进行接收，并保存在本地
 * TCPTest1_1为自己编写的 -> Successful!
 * 思路:
 *  在客户端创建FileInputStream  ->  将需要传输的文件导入内存之中
 *  使用Collection动态存储文件   -> LinkedList
 *  将Collection转换为Array     -> 使用OutputStream传输byte[]
 *
 *  在服务器端开设FileOutputStream -> 将经过传输的文件写到本地
 *  接收数据的时候可以采用ByteArrayOutputStream -> 动态扩展
 *  再将baos对象转换为数组baos.toByteArray()
 *  利用fos将数组写出到本地
 *
 * 康师傅写法：
 * 客户端边读边写 ->  fis 与 os同步
 * 服务器端边读边写 -> is与fos同步
 *
 * //缓冲流加速?
 */
public class TCPTest1_1
{
    @Test
    public void client() throws IOException
    {
        //1.创建Socket
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet,9090);

        //2.流资源加载
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //3.传输文件加载
        //File
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\TCP\\Tony.jpg");
        //流资源 -> .jpg（字节流）
        FileInputStream fis = new FileInputStream(file);
        //读入数据 -> 使用集合保存!
        Collection<Byte> list = new LinkedList<Byte>();
        byte[] buffer = new byte[1024];
        int read;
        while((read = fis.read(buffer)) != -1)
        {
            //addAll?  晕晕
            //LinkedList<Byte> temp = Arrays.asList((Byte)(buffer));
            //list.addAll();

            //以下是严重错误!  [范围!]
            //for(byte temp:buffer)
            //    list.add(temp);

            //正确方法
            for (int i = 0; i < read; i++)
            {
                list.add(buffer[i]);
            }
        }
        //将集合转换为数组
        byte[] bytes = new byte[list.size()];
        int i = 0;
        for(byte b:list)
            bytes[i++] = b;

        //将读取的内容输出
        os.write(bytes);
        os.flush();
        socket.shutdownOutput();

        //资源关闭
        socket.close();

    }

    @Test
    public void server() throws IOException
    {
        //ServerSocket
        ServerSocket ss = new ServerSocket(9090);

        //Socket
        Socket socket = ss.accept();

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //读取数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        //输出
        //System.out.println(baos.toString());
        byte[] bytes = baos.toByteArray();

        //转换为字节流保存
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\TCP\\Tony1.jpg");

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(bytes);

        //流资源关闭
        socket.close();
        fos.close();

    }

    //自己写的太繁琐复杂! ※原因是在不关闭流资源的情况之下可以一直写!  康师傅的果然厉害了!
    @Test
    public void client1() throws IOException
    {
        //创建Socket
        //1.创建Socket
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet,9090);

        //2.流资源加载
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //3.File
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\TCP\\Tony.jpg");
        //流资源 -> .jpg（字节流）
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int read;
        while((read = fis.read(buffer)) != -1)
        {
            //os支持边读边写!
            os.write(buffer,0,read);
        }

        //流资源关闭
        socket.close();
        fis.close();
    }

    @Test
    public void server1() throws IOException
    {
        //ServerSocket
        ServerSocket ss = new ServerSocket(9090);

        //Socket
        Socket socket = ss.accept();

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //读取数据到本地
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\TCP\\Tony2.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            fos.write(buffer,0,read);
        }

        //close
        socket.close();
        fos.close();
    }

    //速度太慢? -> 使用缓冲流加速  -> Successful!
    @Test
    public void client2() throws IOException
    {
        //Socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //缓冲流 + 文件流
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\TCP\\Tony.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        //尝试
        BufferedOutputStream bos = new BufferedOutputStream(os);  //!!!

        //读取写入数据
        byte[] buffer = new byte[8];
        int read;
        while((read = bis.read(buffer)) != -1)
        {
            bos.write(buffer,0,read);
            //bos.flush();      //手动强制减速
        }

        //关闭资源
        bis.close();
        bos.close();
        socket.close();     //一定注意关闭顺序!

    }

    @Test
    public void server2() throws IOException
    {
        //ServerSocket
        ServerSocket ss = new ServerSocket(9090);

        //Socket
        Socket socket = ss.accept();

        //stream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //读取数据到本地
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\network_programming\\Tony3.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //！
        BufferedInputStream bis = new BufferedInputStream(is);
        //读写数据
        byte[] buffer = new byte[8];
        int read;
        while((read = bis.read(buffer)) != -1)
        {
            bos.write(buffer,0,read);
            //bos.flush();      //手动强制减速
        }

        //关闭资源
        bis.close();
        bos.close();
        socket.close();
    }

}
