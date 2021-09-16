package recursion;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-16 10:26
 */
public class RecursionTest1
{
    @Test
    public void test1()
    {
        //计算1~100的总和
        //int sum = sum(100);
        //System.out.println(sum);

        //求阶乘
        System.out.println(factority(6));
    }

    //递归方法  ->  求和
    public int sum(int n)
    {
        if(n > 1)
            return n+sum(n-1);
        else
            return 1;
    }

    //递归方法  ->  阶乘
    public long factority(int n)
    {
        if(n == 1)
            return 1;
        else
            return factority(n-1)*n;
    }
}
