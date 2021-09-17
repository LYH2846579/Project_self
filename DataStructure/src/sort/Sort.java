package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author LYHstart
 * @create 2021-09-16 19:48
 */
public class Sort
{
    @Test
    public void popSort()
    {
        ArraySort arraysort = new ArraySort(8);
        int length = arraysort.length;
        int[] array = arraysort.array;

        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length-i-1; j++)        //一定小心范围!
            {
                if(array[j] > array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        System.out.println(arraysort.toString());
    }

    @Test
    public void straightInsertionSortSort()
    {
        ArraySort arraysort = new ArraySort(8);
        int length = arraysort.length;
        int[] array = arraysort.array;

        for (int i = 1; i < length; i++)
        {
            int j;
            //若值小于上一个元素
            if(array[i] < array[i-1])
            {
                int temp = array[i];
                for (j = i; j > 0 && (array[j-1] >= temp); j--) //仅仅一个判断条件不对!!!晕晕
                {
                    array[j] = array[j-1];
                }
                array[j] = temp;
            }
        }

        System.out.println(arraysort.toString());
    }

    @Test
    public void selectSort()
    {
        ArraySort arraysort = new ArraySort(16);
        int length = arraysort.length;
        int[] array = arraysort.array;

        for (int i = 0; i < length; i++)
        {
            int min = array[i];     //记录最小元素值 -> 初始化即为array[i]
            int flag = i;           //记录最小元素位置
            //每次寻找全列最小的元素，第i次放置在第i个位置上
            for (int j = i; j < length; j++)
            {
                if(array[j] < min)
                {
                    min = array[j];
                    flag = j;
                }
            }
            //扫描完毕后得到当前序列的最小值   ->  放置在合适的位置上
            if(flag != i)   //若存在更小值，替换
            {
                int temp = array[i];
                array[i] = min;
                array[flag] = temp;
            }
        }
        System.out.println(arraysort.toString());
    }

    @Test
    public void shellSortTest()
    {
        ArraySort arraysort = new ArraySort(16);
        int[] dlta = {5,3,2,1};
        int length = dlta.length;

        shellInsert(arraysort,dlta,length);

        System.out.println(arraysort.toString());
    }

    //第一个方法，划分梯度，分层次调用插入程序
    public void shellInsert(ArraySort arraySort,int[] dlta,int length)
    {
        for (int i = 0; i < length; i++)
        {
            shellSort(arraySort,dlta[i]);
        }
    }
    //第二个方法，执行插入排序
    public void shellSort(ArraySort arraySort,int dk)
    {
        int[] array = arraySort.array;
        int length = arraySort.length;
        for (int i = dk; i < length; i++)
        {
            //倘若比上一位置元素小
            if(array[i-dk] > array[i])
            {
                //开始扫描
                int temp = array[i];
                int j;
                for (j = i-dk; j >= 0 && (array[j] > temp) ; j-=dk)
                {
                    array[j+dk] = array[j];
                }
                //退出时将temp放入数组中
                array[j+dk] = temp;
            }
        }
    }


    @Test
    public void quickSortTest()
    {
        ArraySort arraysort = new ArraySort(16);

        quickSort(arraysort,0,arraysort.length-1);  //注意high范围!

        System.out.println(arraysort.toString());
    }

    //排序函数
    public void quickSort(ArraySort arraySort,int low,int high)
    {
        if(low < high)
        {
            int pkloc = partiton(arraySort, low, high);
            quickSort(arraySort,low,pkloc);
            quickSort(arraySort,pkloc+1,high);
        }
    }
    //定位函数
    public int partiton(ArraySort arraySort,int low,int high)
    {
        int[] array = arraySort.array;
        int length = arraySort.length;

        int temp = array[low];
        while(low < high)
        {
            while(low < high && array[high] >= temp)
                high--;
            array[low] = array[high];
            while(low < high && array[low] < temp)
                low++;
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }


    @Test
    public void mergeSortTest()
    {
        ArraySort arraySort = new ArraySort(16);
        ArraySort temp = new ArraySort(16);

        mergeSort(arraySort,temp,0,arraySort.length-1);

        System.out.println(arraySort.toString());
    }
    //1.划分函数
    public void mergeSort(ArraySort arraySort,ArraySort temp,int left,int right)
    {
        if(left >= right)
            return;
        int mid = (left+right)/2;
        mergeSort(arraySort,temp,left,mid);
        mergeSort(arraySort,temp,mid+1,right);
        merge(arraySort,temp,left,mid,right);

    }
    //2.归并函数
    public void merge(ArraySort arraySort,ArraySort temp,int left,int mid,int right)
    {
        int[] array = arraySort.array;
        int length = arraySort.length;

        //拷贝
        for (int i = 0; i < length; i++)
        {
            temp.array[i] = array[i];
        }
        //开始归并
        int s1 = left;
        int s2 = mid+1;
        int k = left;
        while(s1 <= mid && s2<= right)
        {
            if(temp.array[s1] <= temp.array[s2])
                array[k++] = temp.array[s1++];
            else
                array[k++] = temp.array[s2++];
        }
        while(s1 <= mid)
            array[k++] = temp.array[s1++];
        while(s2<= right)
            array[k++] = temp.array[s2++];
    }
}

class ArraySort
{
    int[] array;
    int length;

    public ArraySort() {
    }
    public ArraySort(int length) {
        this.length = length;
        array = new int[length];
        for (int i = 0; i < length; i++)
        {
            array[i] = (int) (Math.random()*77);
        }
    }

    @Override
    public String toString() {
        return "ArraySort{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
