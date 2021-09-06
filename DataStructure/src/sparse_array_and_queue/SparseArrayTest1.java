package sparse_array_and_queue;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-06 20:12
 */
public class SparseArrayTest1
{
    @Test   //五子棋存档
    public void test1()
    {
        //创建原始只有两颗棋子的数组 -> 黑色为1，蓝色为2
        int[][] arr = new int[12][12];  //行列！
        arr[1][2] = 1;
        arr[2][3] = 2;

        //打印数组
        //System.out.println(arr.length);   //行
        //System.out.println(arr[0].length);//列
        //横着输出      //先行后列
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }

        //将数组压缩为稀疏数组
        int values = 0;
        //遍历寻找有效值
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if(arr[i][j] != 0)
                    values++;
            }
        }
        //1.如何存储稀疏数组? -> 使用二维数组存储(固定三列)
        int[][] sparseArray = new int[values+1][3];     //多出一行存储原始数组数据
        //存储原始数组大小
        sparseArray[0][0] = arr.length;
        sparseArray[0][1] = arr[0].length;
        sparseArray[0][2] = values;
        //控制sparseArray存储
        int m = 1;
        int n = 0;
        //将有效值存储
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if(arr[i][j] != 0)
                {
                    sparseArray[m][n++] = i;
                    sparseArray[m][n++] = j;
                    sparseArray[m++][n] = arr[i][j];
                    n = 0;
                }
            }
        }
        //输出sparseArray
        for (int i = 0; i < sparseArray.length; i++)
        {
            for (int j = 0; j < sparseArray[0].length; j++)
            {
                System.out.print(sparseArray[i][j]+"\t");
            }
            System.out.println();
        }


    }

    @Test   //测试JAVA数组行列
    public void test()
    {
        int[][] arr = new int[2][3];    //两行三列数组

        System.out.println(arr.length);
        System.out.println(arr[0].length);

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
