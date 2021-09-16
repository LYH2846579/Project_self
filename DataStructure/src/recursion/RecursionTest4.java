package recursion;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-16 17:16
 *
 * 算法复习
 */
public class RecursionTest4
{
    @Test   //实现迷宫问题
    public void test1()
    {
        int length = 8;
        int[][] array = getArray(length);
        /*
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }*/

        //调用寻找函数递归实现查找
        boolean road = findRoad(array, 1, 1);
        System.out.println(road);
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
    }

    //查找函数
    public boolean findRoad(int[][] map,int i,int j)
    {
        //若到达目的地，返回true
        if(map[6][6] == 2)
        {
            return true;
        }
        else
        {
            //若尚未访问过该结点
            if(map[i][j] == 0)
            {
                //声明该结点可访问
                map[i][j] = 2;
                if(findRoad(map,i+1,j))         //向下寻找
                {
                    return true;
                }
                else if(findRoad(map,i,j+1))    //向右寻找
                    return true;
                else if(findRoad(map,i-1,j))    //向上寻找
                    return true;
                else if(findRoad(map,i,j-1))    //向左寻找
                    return true;
                else
                {
                    map[i][j] = 3;      //标明此路不通
                    return false;
                }
            }
            else
            {
                //map[i][j] = 1|2|3; -> 访问过、墙、不通
                return false;
            }
        }
    }


    //创建一个二维迷宫
    public int[][] getArray(int n)
    {
        int[][] array = new int[n][n];

        //将两侧使用围墙围堵
        for (int i = 0; i < n; i++)
        {
            array[0][i] = 1;
            array[n-1][i] = 1;
            array[i][0] = 1;
            array[i][n-1] = 1;
        }
        //自定义围墙
        array[3][1] = 1;
        array[3][2] = 1;

        return array;
    }



    @Test
    public void test2()
    {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
    }
}


/**
 * 存储数组是关键：数组下标表示第几行，数组存储的内容表示位于第几列     ->  不存在位于同一行的情况
 */
//处理八皇后问题
class Queen8
{
    //皇后容量
    int max = 8;
    //创建位置存储数组
    int[] array = new int[max];
    //添加计数
    static int count;

    public static int getCount() {
        return count;
    }

    //添加皇后
    public void check(int n)
    {
        if(n == max)
        {
            count++;
            print();
            return;
        }
        else
        {
            for (int i = 0; i < max; i++)
            {
                array[n] = i;
                if(judge(n))
                    check(n+1);     //若不冲突，继续添加下一个皇后
            }
        }
    }

    //审查是否存在冲突
    public boolean judge(int n) //添加第n个皇后
    {
        for (int i = 0; i < n; i++)
        {
            //  位于同一列               位于同一个对角线上
            if(array[n] == array[i] | Math.abs(n-i) == Math.abs(array[n]-array[i]))
            {
                return false;
            }
        }
        return true;
    }


    //创建遍历函数，将存储在数组中的数据输出
    private void print()
    {
        for (int i = 0; i < max; i++)
        {
            System.out.print(array[i]+"\t");
        }
        System.out.println();
        System.out.println(count);
    }
}
