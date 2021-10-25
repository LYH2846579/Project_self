package Review.network_programming;

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
 * @create 2021-10-25 22:58
 *
 * 针对于TCP传输的练习
 *
 * 使用到socket、ServerSocket、byteArrayOutputStream
 */
public class TcpTest
{
    @Test
    public void client() throws IOException
    {
        //获取ip
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        //创建套接字
        Socket socket = new Socket(inet,8899);

        OutputStream os = socket.getOutputStream();
        os.write("你好,这里是客户端!".getBytes());

        //关闭资源
        os.close();
        socket.close();
    }

    @Test
    public void server() throws IOException
    {
        //创建服务器端的套接字
        ServerSocket ss = new ServerSocket(8899);

        //接收套接字 -> 来自客户端的消息     socket中包含客户端信息
        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();

        //不建议使用
        /*byte[] bytes = new byte[5];
        int read = 0;
        while((read = is.read(bytes)) != -1)
        {

        }*/

        //类内部有缓冲区存储数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[5];
        int read;
        while((read = is.read(buffer)) != -1)
        {
            baos.write(buffer,0,read);
        }
        System.out.println(baos.toString());

        baos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
