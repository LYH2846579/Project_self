package regular_expression.homework;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-13 18:02
 *
 * 2.要求验证是不是整数或者小数Homework02.java
 * 提示:这个题要考虑正数和负数
 * 比如: 123 -345 34.89 -87.9 -0.01 0.45等
 */
public class HomeWork02
{
    @Test   //字符串
    public void test1()
    {                                               //0  ?
        String str = "123 -345 34.89 -87.9 -0.01 0.45 0.00 0 ";
        //将原本的字符串进行分割
        String[] split = str.split(" ");
        //遍历校验是否是整数或者小数
        for(String s:split)
        {
            //System.out.println(s);
            if(s.matches("\\d+"))
            {
                System.out.println(s+"是正整数");
            }
            else if(s.matches("\\d+(\\.)+\\d+"))
            {
                System.out.println(s+"是正小数");
            }
            else if(s.matches("-\\d+"))
            {
                System.out.println(s+"是负整数");
            }
            else if(s.matches("-\\d+(\\.)+\\d+"))
                System.out.println(s+"是负小数");
            else
                System.out.println("无法识别该对象!");
        }
    }

    @Test
    public void test2()
    {
        String str = "123 -345 34.89 -87.9 -0.01 0.45 0.00 0 ";
        //将原本的字符串进行分割
        String[] split = str.split(" ");

        //正则表达式         匹配正负数                   匹配绝对值在1以内的正负小数
        String regStr = "^[-+]?([1-9]\\d*)+(\\.\\d+)?$";

        //遍历校验是否是整数或者小数
        for(String s:split)
        {
            if(s.matches(regStr))
                System.out.println("匹配成功!");
            else
            {
                if(s.matches("^[-+]?0.\\d+$"))
                    System.out.println("匹配成功!");
                else
                    System.out.println("匹配失败!");
            }
        }
    }

    @Test
    public void test3()
    {
        String str = "123 -345 34.89 -87.9 -0.01 0.45 0.00 0 ";
        //将原本的字符串进行分割
        String[] split = str.split(" ");

        //正则表达式
        String regStr = "^[-+]?([1-9]\\d*|0)(\\.\\d+)?$";

        for(String s:split)
        {
            if(s.matches(regStr))
                System.out.println("匹配成功!");
            else
                System.out.println("匹配失败!");
        }
    }
}
