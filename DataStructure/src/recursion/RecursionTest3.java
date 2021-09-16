package recursion;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-16 15:37
 *
 * 八皇后问题实现
 * 说明:理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题.
 * arr[8]= {0 ,4,7,5,2,6,1,3}//对应arr下标表示第几行，即第几个皇后，
 * arr[i]=val , val表示第i+1个皇后，放在第i+1行的第val+1列
 */
public class RecursionTest3
{
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
    int[] arr = new int[max];
    //统计数据
    static int count = 0;

    public static void main(String[] args) {
        //测试
        RecursionTest3 queen8 = new RecursionTest3();
        queen8.check(0);
        System.out.println("总计:"+count);
    }

    //编写一个方法，放置第n个皇后
    private void check(int n)
    {
        if(n == max)    //放置
        {
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        //特别注意：check是每一次递归时，进入到check中都有for(int i=0; i < max; i++)
        //因此会有回溯产生
        for (int i = 0; i < max; i++)
        {
            //先把当前这个皇后n，放到该行的第一列
            arr[n] = i;
            //判断当前皇后放到第i位置的时候，是否存在冲突
            if(judge(n))
            {
                //若不冲突，继续向后放置
                check(n+1);
            }
            //若冲突，就执行arr[n] = i;  -> 继续后移遍历
        }
    }

    //查看我们放置的第n个皇后，检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n     表示第n个皇后
     * @return
     */
    private boolean judge(int n)
    {
        for (int i = 0; i < n; i++)
        {
            //说明
            //1.arr[i] == arr[n] -> 表示是否位于同一列
            //2.Math.abs(n-i) == Math.abs(arr[n]-arr[i]) -> 是否位于同一对角线上
            //附：    横向距离  ==  纵向距离
            if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i]))
                return false;
        }
        return true;
    }


    //将当前存储的遍历结果输出
    private void print()
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
        count++;
    }

}
