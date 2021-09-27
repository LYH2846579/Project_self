package regular_expression.homework;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-13 16:44
 *
 * 1.验证电子邮件格式是否合法Homework01java
 *
 * 规定电子邮件规则为
 * 1.只能有一个@
 * 2. @前面是用户名,可以是a-z A-Z 0-9 -字符
 * 3. @后面是域名，并且域名只能是英文字母，比如sohu.com或者tsinghua.org.cn
 * 4.写出对应的正则表达式，验证输入的字符串是否为满足规则
 */
public class HomeWork01
{
    @Test
    public void test1()
    {
        //String str = "19819660666@suhu.com";
        //String str = "19819660666@tsinghua.org.cn";
        //String str = "1981966wsz6@.cn";
        String str = "1981966wsz6@org.";

        String regStr = "^([\\w_]+)\\@([a-z]+)([a-z.]+)([a-z]+)$";
        String regStr1 = "[\\w-]+@([statement-zA-Z]+\\.)+([statement-zA-Z])+";

        boolean matches = str.matches(regStr);
        if(matches)
            System.out.println("符合规则");
        else
            System.out.println("不符合规则");
    }

    @Test   //整体匹配
    public void test2()
    {
        String str = "1981966wsz6@org.cn";

        String regStr = "[\\w-]+@[statement-zA-Z]+\\.+[statement-zA-Z]+";

        //???
    }
}
