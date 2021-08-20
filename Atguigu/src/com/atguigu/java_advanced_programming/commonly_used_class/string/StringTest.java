package com.atguigu.java_advanced_programming.commonly_used_class.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author LYHstart
 * @create 2021-08-15 8:16
 *
 * 附：若想硬看String对象的地址值，可以使用Debug方法查看
 *
 * String
 * String:字符串，使用一对""引起来表示。
 * 1.String声明为final的，不可被继承
 * 2.String实现了Serializable接口:表示字符串是支持序列化的。
 *         实现了Comparable接口:表示String可以比较大小
 * 3.String内部定义了final char[] value用于存储字符串数据
 * 4.string:代表不可变的字符序列。简称:不可变性。
 *     体现:1.当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值
 *         2．当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值,不能使用原有的value进行赋值
 *         3．当调用string的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值
 * 5.通X字面量的方式(区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
 * 6.字符串常量池中是不会存储相同内容的字符串的。
 *
 *
 * String与基本数据类型、包装类、字符数组转换
 * Integer.parseInt(Str);
 * String.value(int)
 */
public class StringTest
{
    @Test
    public void test1()
    {
        //字面量赋值：在方法区的字符串常量池中会开辟字符常量
        String s1 = "123";
        String s2 = "123";

        System.out.println("s1 == s2:"+(s1 == s2));     //比较s1与s2的地址值

        s1 = "Hello";

        //String 类型默认toString方法不输出地址
        System.out.println(s1);
        System.out.println(s2);

        //强转为Object类型仍无法输出地址
        Object o1 = (Object) s1;
        Object o2 = (Object) s2;
        System.out.println(o1.toString());
        System.out.println(o2);

        //在堆空间中开辟对象
        String s3 = new String("123");
        System.out.println("s2 == s3:"+(s2 == s3));     //false

        //String的不可添加性
        String s4 = s2;
        s4 += "456";
        System.out.println(s2);     //123
        System.out.println(s4);     //123456
    }


    @Test   //字符串连接
    public void test2()
    {
        String s1 = "JavaEE";
        String s2 = "hadoop";

        String s3 = "JavaEEhadoop";
        String s4 = "JavaEE"+"hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "JavaEE" + s2;
        String s7 = s1 + s2;

        final String s8 = "JavaEE";         //定义在常量区
        final String s9 = "hadoop";
        final String s10 = s8 + s9;

        //变量相加
        System.out.println(s3 == s4);   //true
        System.out.println(s3 == s5);   //false
        System.out.println(s3 == s6);   //false
        System.out.println(s3 == s7);   //false
        System.out.println(s5 == s6);   //false
        System.out.println(s5 == s7);   //false
        System.out.println(s6 == s7);   //false

        //字符串常量
        System.out.println(s3 == s10);  //true
        System.out.println(s5 == s10);  //false

        //intern()方法
        String s11 = s5.intern();       //返回值存在于常量池中!
        System.out.println(s3 == s11);  //true

    }

    @Test
    public void test3()
    {
        int parseInt = 0;

        //String 与基本数据类型、包装类的转换
        String s1 = "123";
        //String -> int/Integer
        try
        {
            parseInt = Integer.parseInt(s1);
            System.out.println(parseInt);

            Integer integer = Integer.valueOf(s1);
            System.out.println(integer);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        //Integer -> String
        String s2 = String.valueOf(parseInt);
        System.out.println(s2);
    }

    @Test   //String与char[]转换
    public void test4()
    {
        //String ->  char[]         调用String.toCharArray()方法
        String s1 = "Hello123";
        char[] c1 = s1.toCharArray();
        for (int i = 0; i < c1.length; i++)
        {
            System.out.println(c1[i]);
        }

        //char[] -> String          调用String构造器即可
        String s2 = new String(c1);
        System.out.println(s2);
    }

    /*
    编码：String --> byte[] 调用String的getBytes()方法
    解码：byte[] --> String 调用String构造器

    编码：字符串 --> 字节 （看得懂 --> 看不懂的二进制数据）
    解码：字节  --> 字符串 (看不懂的二进制数据 --> 看得懂)
    */
    @Test       //String与byte[]转换
    public void test5()
    {
        //編碼
        String s1 = "abc123中国";
        byte[] b1 = s1.getBytes();  //使用默认编码集       UTF-8默认一个汉字三个字节
        System.out.println(Arrays.toString(b1));

        //以GBK形式解码需要处理异常(不支持该编码)
        byte[] b2 = null;               //定义必须在try-catch之外
        try
        {
            b2 = s1.getBytes("gbk");     //以GBK方式编码 默认一个汉字2个字节
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(b2));

        //解碼
        String s2 = new String(b1);
        System.out.println(s2);

        //GBK解码
        String s3 = new String(b2);     //错误解码      解码要求与编码字符集一致
        System.out.println(s3);

        //正确解码 -> 处理异常
        String s4 = null;
        try
        {
            s4 = new String(b2,"GBK");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        System.out.println(s4);
    }
}

