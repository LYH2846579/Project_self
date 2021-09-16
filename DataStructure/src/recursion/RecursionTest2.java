package recursion;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-16 11:06
 *
 * 迷宫问题
 * 从左上角位置查询到右下角位置
 */
public class RecursionTest2
{
    @Test
    public void test1()
    {
        //生成迷宫
        int length = 8;
        int[][] array = createArray(length);
        /*
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
        */

        //寻找路径
        boolean road = findRoad(array, 1, 1);
        System.out.println(road);

        //打印路径
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1. map表示地图
     * 2.i,j表示从地图的哪个位置开始出发（1,1)
     * 3.如果小球能到map[6][5]位置,则说明通路找到。
     * 4.约定:当map[i][j]为0表示该点没有走过当为1表示墙﹔2表示通路可以走﹔3表示该点已经走过，但是走不通
     * 5.在走迷宫时，需要确定一个策略(方法)下->右->上->左，如果该点走不通，再回溯
     *
     * @param map   地图数组
     * @param i     横坐标
     * @param j     纵坐标
     * @return      返回是否能找到通路
     */
    public boolean findRoad(int[][] map, int i, int j)
    {
        //递归退出条件
        if(map[6][6] == 2)
        {
            return true;
        }
        else
        {
            if(map[i][j] == 0)                     //未曾走过该路径
            {
                map[i][j] = 2;                     //该路可以走
                if(findRoad(map,i+1,j))         //向下寻找
                {
                    return true;
                }else if(findRoad(map,i,j+1))    //向右寻找
                {
                    return true;
                }else if(findRoad(map,i-1,j))    //向上寻找
                {
                    return true;
                }else if(findRoad(map,i,j-1))    //向左寻找
                {
                    return true;
                }
                else
                {
                    map[i][j] = 3;                      //此路不通,经此结点四向均不可以到达目的地点
                    return false;
                }
            }
            else            //若map[i][j]!=0         1\2\3
            {
                return false;
            }
        }

    }

    public int[][] createArray(int n)
    {
        //创建数组
        int[][] array = new int[n][n];
        //实现围墙围堵
        for (int i = 0; i < n; i++)
        {
            array[0][i] = 1;
            array[n-1][i] = 1;
        }
        for (int i = 0; i < n; i++)
        {
            array[i][0] = 1;
            array[i][n-1] = 1;
        }
        //内部凸起实现
        array[3][1] = 1;
        array[3][2] = 1;
        //array[3][3] = 1;
        //array[3][4] = 1;
        //array[3][5] = 1;
        //array[3][6] = 1;

        array[6][5] = 1;
        array[4][5] = 1;

        //array[5][3] = 1;
        array[5][4] = 1;
        array[5][5] = 1;

        array[4][5] = 1;

        //返回生成的数组
        return array;
    }
}
