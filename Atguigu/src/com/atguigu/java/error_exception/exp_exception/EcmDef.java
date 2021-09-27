package com.atguigu.java.error_exception.exp_exception;

import java.util.InputMismatchException;

/**
 * @author LYHstart
 * @create 2021-08-07 13:39
 */
public class EcmDef
{
    public static void main(String[] args)
    {
//        args[0] = "12";
//        args[1] = "2";
        try
        {
//            int statement = Integer.parseInt(args[0]);
//            int b = Integer.parseInt(args[1]);
            int a = 12;
            int b = -4;
            try
            {
                if(a<0 || b<0)
                {
                    throw new EcDef("不可以输入负数");
                }
            }
            catch (EcDef e)
            {
                System.out.println(e.getMessage());
            }
            //执行相除
            double c = ecm(a,b);
            if(c >= 0)
                System.out.println("相除的结果为:"+c);
            else
                System.out.println("计算出现异常");
        }catch (NullPointerException e)
        {
            System.out.println("空指针异常!");
        }catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("数组下标越界!疑似参数缺失");
        }catch (InputMismatchException e)
        {
            System.out.println("穿入数据不匹配!疑似不是整型");
        }
    }

    public static double ecm(int a,int b)
    {
        try
        {
            double c = a/b;
            return c;
        }catch (ArithmeticException e)
        {
            System.out.println("出现算数错误!疑似除数为零");
            return -1;
        }
    }
}

class EcDef extends Exception
{
    static final long serialVersionUID = -33875117171711717L;

    public EcDef() {
    }

    public EcDef(String message) {
        super(message);
    }
}
