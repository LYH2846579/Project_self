package com.atguigu.java_advanced_programming.commonly_used_class.string.exp_exercise;

import org.junit.Test;

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
        System.out.println(reverse(s1,2,5));    //必须时刻注意范围
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
                    //e.printStackTrace();
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
    //**********************************************************
    //获取一个字符串在另一个字符串中出现的次数
    @Test    //如获取"ab"在"abkkcadkabkebfkabkskab"中出现的次数       //以indexOf方法作为依托实现
    public void test3()
    {
        String s = "abkkcadkabkebfkabkskab";
        String sub = "ab";
        int count = countSubString(s,sub);
        if(count == -1)
            System.out.println(sub+"字符串出现的次数为零");
        else
            System.out.println(sub+"字符串出现的次数为"+count);

    }
    private int countSubString(String s,String sub)     //使用indexOf查询子字符串出现的索引
    {
        int count = 0;
        int index = -1;
        if(sub.length() > s.length())
            return -1;
        else
        {
            index = s.indexOf(sub);
            if(index != -1)
            {
                count++;
                while(index != -1 && index < s.length())
                {
                    index = s.indexOf(sub,index+1);
                    //增加中检机制
                    if(index == -1)
                        break;
                    count++;
                }
                return count;
            }
            else
            {
                return -1;
            }
        }
    }

    //**********************************************************
    //获取两个字符串中最大相同子串。
        // 如str1 = "abcwerthelloyuiodef";str2 = "cvhellobnm"
    //提示：将短的那个串进行长度依次递减的子串与较长的串比较
    @Test
    public void test4()
    {
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String s = bruteForce(str1,str2);
        System.out.println("最大相同子串为:"+s);
    }
    private String bruteForce(String str1,String str2)  //暴力匹配算法        //学习KMP算法
    {
        int index = -1;
        if(str1.length()==0 || str2.length()==0)
            return null;
        else
        {
            if(str1.length() < str2.length())
            {
                String temp = str1;
                str1 = str2;
                str2 = temp;
            }
            //寻找最大子串    //转换为char[]处理?
            //char[] c1 = str1.toCharArray();
            //char[] c2 = str2.toCharArray();
            //index = Arrays.binarySearch(c1,c2[0]);
            //对str2进行扫描     //使用indexOf匹配字符串
            String temp = null;
            String maxString = "";
            for (int i = 0; i < str2.length(); i++)
            {
                for (int j = i; j < str2.length(); j++)
                {
                    temp = str2.substring(i,j);
                    index = str1.indexOf(temp);
                    if(index != -1)
                    {
                        if(temp.length() > maxString.length())
                            maxString = temp;
                    }
                    else
                        break;
                }
            }
            return maxString;
        }
    }

    //**********************************************************
    /*
    对字符串中字符进行自然顺序排序
    提示:
        1) 字符串编程字符数组
        2) 对数组排序，选择，冒泡。Array.sort();
        3) 将排序好的数组变成字符串
     */
    @Test   //这里对数组进行排序         //泛型编程? 尚需学习
    public void test5()
    {
        String s1 = "gfedbca";
        System.out.println(popSort(s1.toCharArray()));
    }
    private String popSort(char[] c)
    {
        char temp;
        for (int i = 0; i < c.length; i++)
        {
            for (int j = 0; j < c.length-i-1; j++)
            {
                if(c[j] > c[j+1])
                {
                    temp = c[j];
                    c[j] = c[j+1];
                    c[j+1] = temp;
                }
            }
        }
        return new String(c);
    }

}


//自定义范围异常
class IndexException extends Exception
{
    //标识号
    static final long serialVersionUID = -1787516993124221717L;

    //构造器
    public IndexException() { }
    public IndexException(String message) {
        super(message);
    }
}

