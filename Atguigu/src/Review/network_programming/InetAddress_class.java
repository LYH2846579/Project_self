package Review.network_programming;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author LYHstart
 * @create 2021-10-25 22:50
 *
 * 对于网络编程的复习
 * 端口号和IP地址组合得到的一个网络套接字:SOCKET
 */
public class InetAddress_class
{
    @Test
    public void client() throws UnknownHostException
    {
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());

        InetAddress name = InetAddress.getByName("www.baidu.com");
        System.out.println(name.toString());
    }

    @Test
    public void server()
    {

    }
}
