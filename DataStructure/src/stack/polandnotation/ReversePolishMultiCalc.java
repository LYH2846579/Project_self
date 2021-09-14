package stack.polandnotation;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author LYHstart
 * @create 2021-09-14 14:46
 *
 * 将逆波兰表达式计算器进行完整的实现
 *
 * 功能：
 * 1. 支持 + - * / ()
 * 2. 多位数，支持小数
 * 3. 兼容处理，过滤掉任何空白字符，包括空格、制表符、换页符
 *
 * 附：①栈的遍历是从栈底向栈顶遍历!!!   ->for()
 *      -> 因此使用while(!stack.empty()) 效果更佳!
 *    ②这里考虑的是将原有的字符串以" "进行分割，当然也可以不进行分割，详细实现参考StackTest中对应多位数实现的过程!
 */
public class ReversePolishMultiCalc
{
    @Test   //逆波兰表达式实体
    public void Calculate()
    {
        //1.字符串格式处理
        String str = "1 + ( ( 2 + 3 )\t * 4 )\n   - 5";
        str = Stringfilter(str);
        //System.out.println(str);        //经过处理之后:1 + ( ( 2 + 3 ) * 4 ) - 5

        //2.字符串分隔 -> 使用链表进行存储
        String[] split = str.split(" ");
        LinkedList<String> list = ArrayToList(split);

        //3.输出处理之后的字符串  ->  处理为中缀表达式形式
        //for(String s:list)
        //{
        //    System.out.println(s);
        //}

        //4.从中缀表达式转换为后缀表达式
        Stack<String> stack = ExpressionConversion(list);
        //System.out.println(stack.peek());
        //System.out.println("*********");
        //一定要注意!!!      -> 栈的遍历是从栈底向栈顶遍历!!!         ※
        //for(String s:stack)
        //    System.out.println(s);

        //5.将栈转换为链表形式
        LinkedList<String> postfixList = new LinkedList<>();
        while(!stack.empty())
        {
            postfixList.add(stack.pop());
        }

        //将链表输出
        //for(String s:postfixList)
        //    System.out.println(s);

        //6.对后缀表达式进行计算
        String ans = postfixCal(postfixList);
        System.out.println("运算结果为:"+ans);
    }

    //将获取到的字符串进行文本处理
    public String Stringfilter(String str)
    {
        //过滤制表符和换页符 -> 全部换成空格
        String regStr1 = "\n|\t";
        String s = str.replaceAll(regStr1, " ");
        //将多余的空格过滤
        String regStr2 = " +";
        s = s.replaceAll(regStr2," ");
        return s;
    }

    //将字符串数组转换为链表
    public LinkedList<String> ArrayToList(String[] split)
    {
        LinkedList<String> list = new LinkedList<>();
        for(String s:split)
            list.add(s);
        return list;
    }

    //将中缀表达式转换为后缀表达式
    public Stack<String> ExpressionConversion(LinkedList<String> list)
    {
        //创建符号栈和中间结果存储栈
        Stack<String> operStack = new Stack<>();
        Stack<String> ansStack = new Stack<>();

        //依次扫描中缀表达式链表中的每一个元素
        for(String s:list)
        {
            if(s.matches("\\d+"))
            {
                //若为数字，直接进入结果栈
                ansStack.push(s);
            }
            else if(s.matches("\\+|-|\\*|\\/"))
            {
                //若为四则运算附中的一种
                //1.判断符号栈是否为空
                if(operStack.empty())
                {
                    //若符号栈为空 -> 直接进栈
                    operStack.push(s);
                }
                else if(operStack.peek().matches("\\("))
                {
                    //若符号栈栈顶元素为左括号 -> 直接进栈
                    operStack.push(s);
                }
                else    //开始比较优先级
                {
                    while(true)
                    {
                        //增加判空机制与左括号校验
                        if(operStack.empty() || operStack.peek().matches("\\("))
                        {
                            operStack.push(s);
                            break;
                        }

                        int num1 = getPriority(s);
                        int num2 = getPriority(operStack.peek());
                        //若新运算符优先级高 ->  进栈
                        if(num1 > num2)
                        {
                            operStack.push(s);
                            break;
                        }
                        else
                        {
                            //若小于等于栈顶运算符的优先级 -> 将栈顶运算符弹出并压入结果栈中,继续与下一个运算符作比较
                            ansStack.push(operStack.pop());
                        }
                    }
                }
            }
            else if(s.matches("\\("))
            {
                //若为左括号 -> 直接进入符号栈
                operStack.push(s);
            }
            else if(s.matches("\\)"))
            {
                //若为右括号 -> 依次将符号栈的元素弹出
                while(true)
                {
                    String pop = operStack.pop();
                    if(pop.matches("\\("))
                        break;
                    else
                        ansStack.push(pop);
                }
            }
        }
        //在将中缀表达式扫描完成之后 -> 将符号栈中的所有元素依次弹出并压入结果栈中
        while(!operStack.empty())
        {
            ansStack.push(operStack.pop());
        }
        //将结果栈中的元素依次弹出压入符号栈中(执行逆序操作),将符号栈返回
        while(!ansStack.empty())
        {
            operStack.push(ansStack.pop());
        }
        return operStack;
    }

    //获取优先级
    public int getPriority(String s)
    {
        if(s.matches("\\*|\\/"))
            return 1;
        else if(s.matches("\\+|-"))
            return 0;
        else
            throw new RuntimeException("运算符异常!");
    }

    //后缀表达式计算
    public String postfixCal(LinkedList<String> list)
    {
        //1.创建一个计算栈
        Stack<String> stack = new Stack<>();
        //依次遍历表达式
        for(String s:list)
        {
            //识别出为操作数 ：包含整数、小数、整数、负数        小数|1以内的小数|整数
            if(s.matches("[\\+-]?[1-9]\\d*(\\.)?\\d+|[\\+-]?0\\.(\\d)+|[\\+-]?\\d+"))
                stack.push(s);
            else if(s.matches("\\+|\\-|\\*|\\/"))
            {

                //若满足为四则运算符
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());

                switch(s)
                {
                    case "+":
                        stack.push(Double.toString(num2+num1));
                        break;
                    case "-":
                        stack.push(Double.toString(num2-num1));
                        break;
                    case "*":
                        stack.push(Double.toString(num2*num1));
                        break;
                    case "/":
                        stack.push(Double.toString(num2/num1));
                        break;
                    default:
                        throw new RuntimeException("运算符异常!");
                }
            }
        }
        //遍历结束之后
        return stack.pop();
    }
}
