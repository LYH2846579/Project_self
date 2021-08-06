package com.atguigu.java.inner_class;

/**
 * @author LYHstart
 * @create 2021-08-06 9:46
 */
public class Outer
{
    private int s = 1111;
    public class Inner
    {
        private int s = 2222;
        public void mb(int s)
        {
            System.out.println("S:"+s);
            System.out.println("this.s:"+this.s);
            System.out.println("cthis.s:"+Outer.this.s);
        }
    }

    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.Inner b = o.new Inner();
        b.mb(333);
    }


    //局部内部类
    //返回一个实现了Comparable接口类的对象
    public Comparable getComparable()
    {
        //方式一
        class MyComparable implements Comparable
        {

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        }
        return new MyComparable();
    }
    public Comparable getComparable1()
    {
        //方式二   实现类的匿名类的匿名对象
        return new Comparable()
        {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

    }
}
