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

    @Test   //使用链栈实现简易计算器   -> 实现一位整数运算即可!
    public void test3()
    {
        //创建运算序列
        String str = new String("3+2*6-9");
        //创建两个栈 -> 数字栈和符号栈
        StackLinkedList<Integer> numStack = new StackLinkedList();
        StackLinkedList<Character> operStack = new StackLinkedList<>();
        //不妨使用数组转换字符串进行扫描
        char[] chars = str.toCharArray();
        //依次进行扫描
        for(char c:chars)
        {
            //进入判断是符号还是数字   ->如何判断? 两位数咋操作? 如何断定是否为运算符? 正则嘛?
            //尝试强转?     ->  考虑一个问题，char类型变量转换为int类型变量根本不可能产生ClassCastException异常!
            int i = (int)c;
            //若是处于48~57区间 -> 压入数字栈
            if(i >= 48 && i<= 57)
            {
                i = i-48;
                numStack.push(i);
            }
            else
            {
                //恢复为字符   ->   压入符号栈
                c = (char)i;
                //若符号栈空 -> 直接入栈
                if(operStack.isEmpty())
                    operStack.push(c);
                //否则进入优先级判断
                else
                {
                    int pri1 = priority(c);
                    //获取符号栈顶元素
                    Character top = operStack.getTop();
                    int pri2 = priority(top);
                    //若优先级小于或等于top的优先级
                    if(pri1 <= pri2)
                    {
                        //弹出元素进行运算，并将结果压入数栈
                        int num1 = numStack.getTop();
                        numStack.pop();
                        int num2 = numStack.getTop();
                        numStack.pop();
                        char oper = operStack.getTop();
                        operStack.pop();
                        //后出栈元素在前
                        int ans = calculate(num1,num2,oper);
                        //压入数栈
                        numStack.push(ans);

                        //千万别忘了取出的符号进栈!!!
                        operStack.push(c);
                    }
                    else
                    {
                        //操作符直接进栈
                        operStack.push(c);
                    }
                }
            }

            //直接输出看看情况
            //System.out.println(c);  //toCharArray()会将每一个字符解析成为一个单独的字节
        }
        //退出循环后，依次将数字栈、符号栈中的元素取出，并计算
        while(!numStack.isEmpty() && !operStack.isEmpty())
        {
            //弹出元素进行运算，并将结果压入数栈
            int num1 = numStack.getTop();
            numStack.pop();
            int num2 = numStack.getTop();
            numStack.pop();
            char oper = operStack.getTop();
            operStack.pop();
            //后出栈元素在前
            int ans = calculate(num1,num2,oper);
            //压入数栈
            numStack.push(ans);
        }


        //最终结果保存在数栈中
        System.out.println("运算结果为:"+numStack.getTop());
    }


    //实现多位数字计算              ->  实现了多位数运算!
    @Test
    public void test5()
    {
        //创建运算式
        String str = new String("+36+2*7-100");
        //创建数字栈和符号栈
        StackLinkedList<Integer> numStack = new StackLinkedList<>();
        StackLinkedList<Character> operStack = new StackLinkedList<>();
        //创建数字存储数组
        int[] array = new int[8];       //存放8位整数
        int index = 0;
        //将运算式转换为数组进行处理
        char[] chars = str.toCharArray();
        //遍历运算
        for(char c:chars)
        {
            //将字符转换为数字处理
            int i = (int)c;
            if(i >=48 && i<=57)
            {
                //若为数字->首先存入数字数组
                array[index++] = (i-48);
                //先暂时不存入数字栈
            }
            else
            {
                //将int转换为char
                c = (char)i;
                //若为符号，首先分析数字数组中是否有数据
                if(index != 0)
                {
                    int num = 0;
                    int flag = 0;           //count就报错是吧!
                    //将数字依次取出
                    index--;
                    for (int j = index; j >= 0; j--)      //从有效数据开始读取
                    {
                        int sci = (int)Math.pow(10,flag);
                        flag += 1;
                        num += array[j]*(sci);
                    }
                    //重置index
                    index = 0;
                    //将数字压入数字栈
                    numStack.push(num);
                }
                //else  不可以使用else ->    会漏掉元素!
                {
                    if(numStack.isEmpty())
                    {
                        //若第一个元素为符号 ->  0进数栈
                        numStack.push(0);
                    }
                    //数字数组无数据
                    //判断符号栈是否为空
                    if(operStack.isEmpty())
                        operStack.push(c);
                    else
                    {
                        int pri1 = priority(c);
                        //获取符号栈顶元素
                        Character top = operStack.getTop();
                        int pri2 = priority(top);
                        //若优先级小于或等于top的优先级
                        if(pri1 <= pri2)
                        {
                            //弹出元素进行运算，并将结果压入数栈
                            int num1 = numStack.getTop();
                            numStack.pop();
                            int num2 = numStack.getTop();
                            numStack.pop();
                            char oper = operStack.getTop();
                            operStack.pop();
                            //后出栈元素在前
                            int ans = calculate(num1,num2,oper);
                            //压入数栈
                            numStack.push(ans);

                            //千万别忘了取出的符号进栈!!!
                            operStack.push(c);
                        }
                        else
                        {
                            //操作符直接进栈
                            operStack.push(c);
                        }
                    }
                }
            }
        }
        //分析array中是否有剩余元素!      //否则会出现空指针异常!
        if(index != 0)
        {
            int num = 0;
            int flag = 0;           //count就报错是吧!
            //将数字依次取出
            index--;
            for (int j = index; j >= 0; j--)      //从有效数据开始读取
            {
                int sci = (int)Math.pow(10,flag);
                flag += 1;
                num += array[j]*(sci);
            }
            //重置index
            index = 0;
            //将数字压入数字栈
            numStack.push(num);
        }
        //退出循环后，依次将数字栈、符号栈中的元素取出，并计算
        while(!numStack.isEmpty() && !operStack.isEmpty())
        {
            //弹出元素进行运算，并将结果压入数栈
            int num1 = numStack.getTop();
            numStack.pop();
            int num2 = numStack.getTop();
            numStack.pop();
            char oper = operStack.getTop();
            operStack.pop();
            //后出栈元素在前
            int ans = calculate(num1,num2,oper);
            //压入数栈
            numStack.push(ans);
        }

        //最终结果保存在数栈中
        System.out.println("运算结果为:"+numStack.getTop());
    }


    //Math测试
    @Test
    public void test6()
    {
        System.out.println(Math.pow(10,0));
    }




    //以下，辅助运算方法

    //优先级判定
    public int priority(char c)
    {
        if(c == '*' || c == '/')
            return 1;
        else if(c == '+' || c== '-')
            return 0;
        else
            return -1;      //假定只有四种运算符
    }

    //计算
    public int calculate(int num1,int num2,char c)
    {
        int ans = 0;
        switch (c)
        {
            case '*':
                ans = num2*num1;
                break;
            case '/':
                ans = num2/num1;
                break;
            case '+':
                ans = num2+num1;
                break;
            case '-':
                ans = num2-num1;
                break;
            default:
                throw new RuntimeException("运算符异常!");
        }
        return ans;
    }

    @Test   //数字ASCII码输出
    public void test4()
    {
        //0~9  ->   49~57
        char c = '1';
        System.out.println((int)c);
        c = '9';
        System.out.println((int)c);

    }
}
