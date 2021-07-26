package JAVA基础学习;
//JAVA方法

import sun.swing.plaf.synth.DefaultSynthStyle;

import java.util.Arrays;

/**
 * @author LYHstart
 * @create 2021-07-25 13:30
 */
public class Kuang_method
{
    public static void main(String[] args)
    {
        //稀疏数组压缩与还原
        //原数组   //11*11大小，1代表黑棋子，2代表白棋子，0代表无
        int[][] array1 = new int[11][11];
        array1[1][2] = 1;
        array1[2][3] = 2;
        //遍历数组
        for (int[] ints : array1)
        {
            for (int anInt : ints)
            {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        //有效数据遍历
        int sum = 0;
        for (int[] ints : array1)
        {
            for (int anInt : ints)
            {
                if(anInt != 0)
                    sum++;
            }
        }
        //稀疏数组建立        array2[0]=行 + 列 + 值
        int[][] array2 = new int[sum+1][3];
        array2[0][0] = array1.length;
        //System.out.println(array2[0][0]);
        array2[0][1] = array1[0].length;
        //System.out.println(array2[0][1]);
        array2[0][2] = sum;
        int count = 0;
        for(int i=0;i<array1.length;i++)
        {
            for (int j = 0; j < array1[0].length; j++)
            {
                if(array1[i][j] != 0)
                {
                    count++;
                    array2[count][0] = i;
                    array2[count][1] = j;
                    array2[count][2] = array1[i][j];
                }
            }
        }
        //稀疏数组打印
        System.out.println("************************");
        for (int[] ints : array2)
        {
            for (int anInt : ints)
            {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        //稀疏数组恢复
        int[][] array3 = new int[array2[0][0]][array2[0][1]];
        for(int i=1;i<array2.length;i++)
        {
            array3[array2[i][0]][array2[i][1]] = array2[i][2];
        }
        //有效数据遍历
        System.out.println("************************");
        for (int[] ints : array3)
        {
            for (int anInt : ints)
            {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
    }


    /*
    //数组    反转
    public static void main(String[] args)
    {
        int[] arrays = {1,2,3,4,5};
        arrays = reverse(arrays);
        for (int array : arrays)
        {
            System.out.println(array);
        }
    }

    public static int[] reverse(int[] arrays)
    {
        int[] result = new int[arrays.length];
        //方法一
        for(int i=0,j=arrays.length-1;i<arrays.length;i++,j--)
        {
            result[i] = arrays[j];
        }
        //方法二
        for(int i=0;i<arrays.length;i++)
        {
            result[i] = arrays[arrays.length-i-1];      //一定注意下标关系
        }
        return result;
    }


    //递归求解阶乘
    public static void main(String[] args)
    {
        Kuang_method k = new Kuang_method();
        System.out.println(k.test(5));
    }
    //递归计算
    public int test(int n)
    {
        if(n == 1)
            return 1;
        else
            return n*test(n-1);
    }


    //设计方法原则:保持方法的原子性，仅完成一个功能
    public static void main(String[] args)
    {
        //直接参数调用
        printMax(34,15,463,1.1,546,2);
        //数组调用
        printMax(new double[]{1,2,3,4.5});
    }

    //可变参数
    public static void printMax(double ... numbers)
    {
        if(numbers.length == 0)
        {
            System.out.println("No arguments passed");
            return;
        }

        double result = numbers[0];

        //寻找最大值
        for(int i=1;i<numbers.length;i++)
        {
            if(numbers[i] > result)
                result = numbers[i];
        }
        System.out.println("The max value is "+result);


    }
    */
}
