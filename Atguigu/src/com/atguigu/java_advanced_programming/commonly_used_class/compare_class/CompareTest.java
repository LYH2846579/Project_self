package com.atguigu.java_advanced_programming.commonly_used_class.compare_class;

import com.atguigu.java.error_exception.MyException;
import com.sun.javafx.image.BytePixelSetter;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author LYHstart
 * @create 2021-08-20 10:50
 *
 *  一、说明:Java中的对象，正常情况下，只能进行比较: == 或 !=。不能使用 > 或 < 的
 *          但是在开发场景中，我们需要对多个对象进行排序，言外之意，就需要比较对象的大小。
 *          如何实现?使用两个接口中的任何一个: Comparable或 Comparator
 *
 *  二、Comparable接口的使用       自然排序
 *
 *  三、Comparator接口使用         定制排序
 *
 *  四、Comparable接口与Comparator的使用的对比:
 *      Comparable接口的方式一旦一定，保证Comparable接口实现类的对象在任何位置都可以比较大小
 *      Comparator接口属于临时性的比较。
 *
 */
public class CompareTest
{
    //Comparable接口的使用举例:    //※自然排序
    /*
    1.像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方法
    2.像String、包装类重写compareTo()方法以后，进行了从小到大的排列
    3．重写compareTo(obj)的规则:
        如果当前对象this大于形参对象obj，则返回正整数；
        如果当前对象this小于形参对象obj，则返回负整数；
        如果当前对象this等于形参对象obj，则返回零。
    4.对于自定义类数组进行排序，可以实现Comparable接口并重写CompareTo方法进行比较实现
     */
    @Test
    public void test1()
    {
        String[] arr = new String[]{"AA","CC","BB","MM","JJ","GG","DD"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //自然排序
    @Test
    public void test2()
    {
        //重写自定义类Compare方法 -> 实现Comparable接口
        Goods g1 = new Goods("雷神",200);
        Goods g2 = new Goods("雷蛇",300);
        Goods g3 = new Goods("罗技",500);
        Goods g4 = new Goods("联想",200);

        Object o = new Object();
        Object o1 ;
        System.out.println(g1.compareTo(g4));
        System.out.println(g1.compareTo(o));
        //System.out.println(g1.compareTo(o1));
    }

    //定制排序
    /*
    Comparator接口的使用:定制排序
    1.背景:
    当元素的类型没有实现java.Lang.Comparable接口而又不方便修改代码
    ,或者实现了java.Lang.Comparable接口的排序规则不适合当前的操作,
    那么可以考虑使用comparator的对象来排序
    2.重写compare(object o1, object o2)方法，比较o1和o2的大小:
      如果方法返回正整数，则表示o1大于o2;
      如果返回0，表示相等;
      返回负整数，表示o1小于o2。
     */
    @Test
    public void test3()
    {
        String[] arr = new String[]{"AA","CC","BB","MM","JJ","GG","DD"};
        //匿名实现类
        Arrays.sort(arr, new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String)
                {
                    String s1 = (String)o1;
                    String s2 = (String)o2;
                    return -s1.compareTo(s2);       //从大到小排序
                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });      //一定注意分号

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4()
    {
        Goods g1 = new Goods("雷神",200);
        Goods g2 = new Goods("雷蛇",300);
        Goods g3 = new Goods("罗技",500);
        Goods g4 = new Goods("联想",200);

        //实现Comparator接口
        Goods[] g = new Goods[4];
        g[0] = g1;
        g[1] = g2;
        g[2] = g3;
        g[3] = g4;
        Arrays.sort(g, new Comparator<Goods>()
        {
            @Override
            public int compare(Goods o1, Goods o2) {
                return Double.compare(o1.getPrice(),o2.getPrice());
            }
        });
        System.out.println(Arrays.toString(g));
    }
}

//商品类实现Comparable接口
class Goods implements Comparable
{
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        try
        {
            if(o == null)
            {
                throw new MyException("对象异常!");
            }
            else if(o instanceof Goods)
            {
                Goods g = (Goods) o;
                if(this.getPrice() < g.getPrice())
                    return -1;
                else if(this.getPrice() > g.getPrice())
                    return 1;
                else
                    return 0;
            }
            else
            {
                System.out.println("对象不匹配");
                return Integer.MIN_VALUE;
            }
        }catch (MyException e)
        {
            System.out.println(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }
}
//自定义异常类
class MyExceptionGood extends Exception
{
    static final long serialVersionUID = -3387516993124171717L;

    public MyExceptionGood() {
    }

    public MyExceptionGood(String message) {
        super(message);
    }
}





