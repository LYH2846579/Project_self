package regular_expression.homework;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 19:23
 *
 * 对URL进行解析，获取协议+域名+端口+文件名
 */
public class HomeWork03
{
    @Test
    public void test1()
    {
        //分组
        String str = "https://www.sohu.com:8080/abc/index.htm";

        String regStr = "^(https?)://([\\w]+\\.)+([\\w]+):(\\d+)+[\\/\\w+\\/]+([\\/]+[\\w]+[\\.][\\w]+)$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
        {
            System.out.println("协议:"+matcher.group(1));
            System.out.println("域名:"+matcher.group(2)+matcher.group(3));
            System.out.println("端口:"+matcher.group(4));
            System.out.println("文件名:"+matcher.group(5));
        }
    }

    @Test
    public void test2()
    {
        String str = "https://www.sohu.com:8080/abc/index.htm";

        String regStr = "^([a-zA-Z]+)://([a-zA-Z.]+):(\\d+)[\\w-/]*/([\\w.]+)$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches())
        {
            System.out.println("整体匹配:"+matcher.group(0));
            System.out.println("协议:"+matcher.group(1));
            System.out.println("域名:"+matcher.group(2));
            System.out.println("端口:"+matcher.group(3));
            System.out.println("文件名:"+matcher.group(4));
        }else
        {
            System.out.println("匹配不成功!");
        }
    }
}
