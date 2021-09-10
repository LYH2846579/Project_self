package stack;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-10 19:37
 */
public class StackTest
{
    @Test   //测试数组栈
    public void test1()
    {
        StackArray<Integer> sa = new StackArray<>(3);

        sa.push(1);
        sa.push(2);
        sa.push(3);
        sa.push(4);

        System.out.println("sa.getTop():"+sa.getTop());
        sa.pop();
        sa.pop();
        sa.pop();
        sa.pop();
        sa.print();
    }

    @Test   //链表栈测试
    public void test2()
    {
        StackLinkedList<Integer> sl = new StackLinkedList<>();
        sl.push(1);
        sl.push(2);
        sl.push(3);

        System.out.println(sl.getTop());
        sl.pop();
        System.out.println(sl.getTop());
        sl.pop();
        System.out.println(sl.getTop());
        sl.pop();
        sl.pop();

    }

    @Test   //使用链栈实现简易计算器   -> 实现整数运算即可
    public void test3()
    {
        //创建运算序列
        String str = new String("3+2*6-2");
        //创建两个栈 -> 数字栈和符号栈
        StackLinkedList<Integer> numStack = new StackLinkedList();
        StackLinkedList<Character> operStack = new StackLinkedList<>();
        //不妨使用数组转换字符串进行扫描
        char[] chars = str.toCharArray();
        //依次进行扫描
        for(char c:chars)
        {
            //进入判断是符号还是数字   ->如何判断? 两位数咋操作? 如何断定是否为运算符? 正则嘛?
            //if()
        }
    }
}
