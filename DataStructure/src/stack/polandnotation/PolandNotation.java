package stack.polandnotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author LYHstart
 * @create 2021-09-11 15:58
 *
 * 将扫描转换为对于list的遍历操作
 *
 * 附：① 由于list中存储的为字符串,而字符串不可以强转为字符进而判断ASCII,因此这里采用正则表达式进行匹配
 *    ② 拿出一部分时间来学习正则表达式!!!
 *    ③ 正则表达式学习完成   2021.9.13
 */
public class PolandNotation
{
    public static void main(String[] args)
    {
        //先定义一个逆波兰表达式
        //(3+4)*5-6     =>  3 4 + 5 * 6 -
        //为了方便，在逆波兰表达式中使用空格将数字与符号分隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路：
        //1.先将"3 4 + 5 * 6 -" => 放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        //System.out.println("rpnList:"+rpnList);

        //创建堆栈
        Stack<String> stack = new Stack<>();
        for(String s:rpnList)
        {
            //这里使用正则表达式来取出数据
            if(s.matches("\\d+"))   //匹配是多位数
            {
                //入栈
                stack.push(s);
            }
            else    //符号
            {
                //pop出两个数进行计算，再将结果入栈    ->注意出栈顺序与计算顺序
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int ans = 0;
                if(s.equals("+"))
                {
                    ans = num1+num2;
                }else if(s.equals("-"))
                {
                    ans = num2-num1;
                }else if(s.equals("*"))
                {
                    ans = num2*num1;
                }
                else if(s.equals("/"))
                {
                    ans = num2/num1;
                }
                else
                    throw new RuntimeException("运算符异常!");

                //将结果入栈
                stack.push(Integer.toString(ans));
            }
        }
        //将结果输出
        System.out.println("Ans:"+stack.peek());
    }

    public static List<String> getListString(String suffixExpression)
    {
        //将suffixExpression分隔
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ele:split)
            list.add(ele);
        return list;
    }


    //将以上代码复习一下 -> 重写逆波兰表达式的计算器
    @Test
    public void test1()
    {
        //提供一个逆波兰表达式
        //(3+4)*5-6     =>  3 4 + 5 * 6 -
        //为了方便，在逆波兰表达式中使用空格将数字与符号分隔开
        String str = "3 4 + 5 * 6 -";       //支持多位数扩展

        //将str拆分，使用链表结构进行存储
        String[] split = str.split(" ");    //将原字符串使用空格进行分割

        //将字符串数组转换为链表进行存储(便于遍历)
        LinkedList<String> list = arrayToList(split);

        //遍历list进行计算
        //1.创建计算器辅助堆栈
        Stack<String> stack = new Stack<>();
        for(String s:list)
        {
            //根据对应的元素不同进行分析
            if(s.matches("\\d+"))
            {
                //若对应的字符串为数字
                stack.push(s);
            }else
            {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if(s.matches("\\+"))
                {
                    //若对应的符号为+
                    stack.push(String.valueOf(num2+num1));
                }else if(s.matches("-"))
                {
                    //若对应的符号为-
                    stack.push(String.valueOf(num2-num1));
                }else if(s.matches("\\*"))
                {
                    //若对应的符号为*
                    stack.push(String.valueOf(num2*num1));
                }else if(s.matches("/"))
                {
                    //若对应的符号为/
                    stack.push(String.valueOf(num2/num1));
                }else
                    throw new RuntimeException("符号异常!");
            }
        }
        System.out.println("结果为:"+stack.pop());


    }

    //编写数组转换链表函数
    public LinkedList<String> arrayToList(String[] split)
    {
        //创建新的链表
        LinkedList<String> list = new LinkedList<>();
        for(String s:split)
        {
            list.add(s);
        }
        return list;
    }
}












