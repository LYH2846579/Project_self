package com.atguigu.java_advanced_programming.commonly_used_class.exp_exercise;

import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author LYHstart
 * @create 2021-08-15 22:59
 */
public class Exercise
{
    @Test   //模拟一个trim方法，去除字符串两端的空格
    public void test1()
    {
        //trim方法调用
        String s1 = "   123  456   ";
        System.out.println(s1);
        System.out.println(s1.trim());
        //自定义trim方法
        System.out.println(trim_S(s1));
    }
    //自定义trim方法
    private String trim_S(String s)
    {
        if(s == null)
            return null;
        else
        {
            int i = 0;
            int j = s.length()-1;          //注意数组下标位置
            //消除左侧空格
            while(s.charAt(i) == ' ')      //字符类型
            {
                i++;
            }
            while(s.charAt(j) == ' ')
            {
                j--;
            }
            String temp = s.substring(i,j+1);   //截取为左闭右开区间
            return temp;
        }
    }

    //**********************************************************
    @Test   //将一个字符串进行反转。将字符串中指定部分进行反转。如"abcdefg"反转为"abfedcg"
    public void test2()
    {
        String s1 = "abcdefg";
        System.out.println(reverse(s1,2,5));
    }
    private String reverse(String s,int first,int last)
    {
        if(s == null)
            return null;
        else
        {
            if(first<0 || last >= s.length())
            {
                try
                {
                    throw new IndexException("反转范围错误!");
                } catch (IndexException e)
                {
                    e.printStackTrace();
                    return "反转范围错误!";
                }
            }
            else
            {
                //转换为数组进行逆置
                char[] c = s.toCharArray();
                char temp;
                while(first != last && first < last)
                {
                    temp = c[first];
                    c[first] = c[last];
                    c[last] = temp;
                    first++;
                    last--;
                }
                return new String(c);
            }
        }
    }
}

//自定义范围异常
class IndexException extends Exception
{
    static final long serialVersionUID = -1787516993124221717L;

    public IndexException() { }

    public IndexException(String message) {
        super(message);
    }
}

