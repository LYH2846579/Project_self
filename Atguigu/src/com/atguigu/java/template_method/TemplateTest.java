package com.atguigu.java.template_method;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-02 8:43
 * 模板设计模式:将已变的地方声明为一个抽象的方法。在子类中进行实现
 */
public class TemplateTest
{
    @Test
    public void test1()
    {
        Template t = new subTemplate();
        t.spendTime();
    }
}

abstract class Template
{
    public void spendTime()
    {
        long start = System.currentTimeMillis();

        this.code();    //不确定的、易变的部分

        long end = System.currentTimeMillis();
    }


    public abstract void code();
}

class subTemplate extends Template
{

    @Override
    public void code() {

        //求1000以内的质数
        for(int i=2;i<=1000;i++)
        {
            boolean isFlag = true;
            for(int j=2;j<=Math.sqrt(i);j++)
            {
                if(i % j == 0)
                {
                    isFlag = false;
                    break;
                }
            }
            if(isFlag)
                System.out.println(i);

        }

    }
}