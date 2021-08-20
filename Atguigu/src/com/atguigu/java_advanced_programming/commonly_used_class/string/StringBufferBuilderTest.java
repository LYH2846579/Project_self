package com.atguigu.java_advanced_programming.commonly_used_class.string;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LYHstart
 * @create 2021-08-16 23:28
 *
 * String、StringBuffer、StringBuilder三者异同
 * String:不可变的字符序列;底层使用char[]存储
 * StringBuffer:可变的字符序列;线程安全的，效率低;底层使用char[]存储
 * stringBuilder:可变的字符序列;jdk5.0新增的，线程不安全的，效率高;底层使用char[]存储
 *
 * [1]sb1.length()与sb1.capacity()不同
 * [2]扩容问题：创建时会在原有基础上增加16字节字符长度 （当然可以指定长度创建）
 *            若超过原有容量，会再创建一个新的数组(容量为原有容量*2+2)，将原有数组复制进新数组
 *
 *  对String、StringBuffer、StringBuilder性能对比进行测试
 *  涉及到计时问题※
 */
public class StringBufferBuilderTest
{
    @Test
    public void test1()
    {
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');       //无返回值，在原数据上直接修改
        sb1.append('z');
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer();          //数据存储：char[] value;
        System.out.println(sb2.length());       //显示已用长度        //return count
        System.out.println(sb2.capacity());     //显示开辟空间大小     //value.length;

        System.out.println(Integer.MAX_VALUE-8);

        //StringBuffer方法
        StringBuffer sb3 = new StringBuffer("123");
        sb3.append("456");
        sb3.delete(3,6);        //左闭右开区间  自动元素前移
        sb3.replace(0,1,"8");   //左闭右开区间 自动元素后移
        sb3.insert(2,"10");     //以数组形式存储
        sb3.reverse();          //逆置

        sb3.indexOf("10");
        String sub = null; //sb3.substring(0,sb3.capacity());     //报错! StringIndexOutOfBoundsException
        //System.out.println(sub+",length:"+sub.length());      //报错! NullPointException
        sub = sb3.substring(0,sb3.length());
        System.out.println(sub+",length:"+sub.length());
    }

    //String、StringBuffer、StringBuilder性能对比
    @Test
    public void test2()
    {
        //初始设置
        long startTime = 0L;
        long endTime = 0L;
        String s = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //开始对比
        //buffer
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++)
        {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Buffer:"+(endTime-startTime));
        //builder
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++)
        {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Builder:"+(endTime-startTime));
        //String
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++)
        {
            s = s + String.valueOf(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("String:"+(endTime-startTime));
    }
}
