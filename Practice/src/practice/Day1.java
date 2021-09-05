package practice;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-05 20:23
 */
public class Day1
{
    @Test
    public void test1()
    {
        //try-catch-finally return finally中的代码在return之前、后执行?
        // return执行到中间的时候发现finally，则先不返回，把值暂存到本地栈，等finally运行之后，
        // 如果finally里有返回语句，那么以finally为主，否则才把本地栈里的返回值真的返回。
        fun1();

    }

    public int fun1()
    {
        try
        {
            int age = 17;
            return age;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.println("Hello");
            return -1;
        }
    }
}

