package sort.sort_test;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-09-18 13:57
 */
public class SortTest
{
    @Test
    public void test()
    {
        int[] array = new int[1000000];
        //随机生成数
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) (Math.random()*100000);
        }
        Scanner scan = new Scanner(System.in);
        int select;

        //shellSort
        int[] dlta = {17,7,5,2,1};

        //mergeSort
        int[] temp = new int[array.length];

        while(true)
        {
            printMenu();
            select = scan.nextInt();
            if(select == 0)
            {
                System.out.println("欢迎下次使用!");
                System.exit(0);
            }
            switch (select)
            {
                case 1:
                    shellInsert(array,dlta);
                    //print(array);
                    break;
                case 2:
                    quickSort(array,0,array.length-1);
                    //print(array);
                    break;
                case 3:
                    mergeSort(array,temp,0,array.length-1);
                    //print(array);
                    break;
                case 5:
                    Time_test(array,dlta);
                    break;
                default:
                    System.out.println("选择错误,请重新选择!");
                    break;
            }
        }
    }

    public void printMenu()
    {
        System.out.println("**********排序算法测试模块**********");
        System.out.println("* [1] ShellSort  [2] QuickSort  *");
        System.out.println("* [3] MergeSort  [4]            *");
        System.out.println("* [5] Time_test  [0] exit       *");
        System.out.println("请输入您的选择:");
    }

    //希尔函数实现
    public void shellInsert(int[] array,int[] dlta)
    {
        for (int i = 0; i < dlta.length; i++)
        {
            shellSort(array,dlta[i]);
        }
    }
    public void shellSort(int[] array,int dk)
    {
        //分段直接插入排序
        for (int i = dk; i < array.length; i++)
        {
            if(array[i] < array[i-dk])
            {
                int temp = array[i];
                int j;
                for (j = i-dk; j >= 0 && (array[j]>temp) ; j-=dk)
                {
                    array[j+dk] = array[j];
                }
                array[j+dk] = temp;
            }
        }
    }

    //quickSort
    public void quickSort(int[] array,int low,int high)
    {
        if(low < high)
        {
            int pkloc = partition(array,low,high);
            quickSort(array,low,pkloc);
            quickSort(array,pkloc+1,high);
        }
    }
    public int partition(int[] array,int low,int high)
    {
        int temp = array[low];
        while(low < high)
        {
            while(low < high && array[high] >= temp)
                high--;
            array[low] = array[high];
            while(low < high && array[low] <= temp)
                low++;
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }


    //mergeSort
    public void mergeSort(int[] array,int[] temp,int left,int right)
    {
        if(left < right)
        {
            int mid = (left+right)/2;
            mergeSort(array,temp,left,mid);
            mergeSort(array,temp,mid+1,right);
            merge(array,temp,left,mid,right);
        }
    }
    public void merge(int[] array,int[] temp,int left,int mid,int right)
    {
        for (int i = left; i < right; i++)
        {
            temp[i] = array[i];
        }

        int s1 = left;
        int s2 = mid+1;
        int k = left;
        while(s1 <= mid && s2 <= right)
        {
            if(temp[s1] <= temp[s2])
                array[k++] = temp[s1++];
            else
                array[k++] = temp[s2++];
        }
        while(s1 <= mid)
            array[k++] = temp[s1++];
        while(s2 <= right)
            array[k++] = temp[s2++];
    }

    //整体时间测试
    public void Time_test(int[] array,int[] dlta)
    {
        //时间存储
        long begin = 0L;
        long end = 0L;

        //备份
        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            temp[i] = array[i];
        }

        //shellSort
        begin = System.currentTimeMillis();
        shellInsert(array,dlta);
        end = System.currentTimeMillis();
        System.out.println("ShellSort 耗时:"+(end-begin));

        //恢复原数组
        for (int i = 0; i < array.length; i++)
        {
            array[i] = temp[i];
        }

        //quickSort
        begin = System.currentTimeMillis();
        quickSort(array,0,array.length-1);
        end = System.currentTimeMillis();
        System.out.println("QuickSort 耗时:"+(end-begin));

        //恢复原数组
        for (int i = 0; i < array.length; i++)
        {
            array[i] = temp[i];
        }

        //mergeSort     ->      这里将temp作为临时数组传入
        begin = System.currentTimeMillis();
        mergeSort(array,temp,0,array.length-1);
        end = System.currentTimeMillis();
        System.out.println("MergeSort 耗时:"+(end-begin));

    }

    //输出检查
    public void print(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
