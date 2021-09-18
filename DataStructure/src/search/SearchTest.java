package search;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-09-18 19:44
 */
public class SearchTest
{
    //二分查找方法
    @Test
    public void binarySearch()
    {
        SearchArray searchArray = new SearchArray(20);
        int[] array = searchArray.array;
        int length = searchArray.length;

        System.out.println("请输入要查询的数据:");
        Scanner scan = new Scanner(System.in);
        int temp = scan.nextInt();

        //开始查询
        //1.将序列排序   ->  前提是保证为有序序列
        Arrays.sort(array);
        int left = 0;
        int right = array.length-1;
        int mid = (left+right)/2;

        boolean flag = false;
        while(left <= right)
        {
            if(array[mid] == temp)
            {
                flag = true;
                break;
            }
            else if(array[mid] > temp)   //若中间值偏大
                right = mid-1;
            else
                left = mid + 1;

            mid = (left+right)/2;
        }
        if(flag)
            System.out.println("存在元素:"+temp);
        else
            System.out.println("不存在元素："+temp);

        //将数组打印出来，进行校验
        searchArray.print();
    }

    @Test
    public void BinarySearchPlus()
    {
        SearchArray searchArray = new SearchArray(20);
        int[] array = searchArray.array;
        int length = searchArray.length;

        System.out.println("请输入要查询的数据:");
        Scanner scan = new Scanner(System.in);
        int temp = scan.nextInt();

        //开始查询
        //1.将序列排序   ->  前提是保证为有序序列
        Arrays.sort(array);
        int left = 0;
        int right = array.length-1;
        int mid = left + (temp-array[left])/(array[right]-array[left])*(right-left);

        boolean flag = false;
        while(left <= right)
        {
            //此判断条件必须要有 ->  否则会出现数组越界等情况(temp给出的值很离谱的时候)
            if(left > right || temp < array[0] || temp >array[array.length-1])
                break;

            if(array[mid] == temp)
            {
                flag = true;
                break;
            }
            else if(array[mid] > temp)   //若中间值偏大
                right = mid-1;
            else
                left = mid + 1;

            mid = left + (temp-array[left])/(array[right]-array[left])*(right-left);
        }
        if(flag)
            System.out.println("存在元素:"+temp);
        else
            System.out.println("不存在元素："+temp);

        //将数组打印出来，进行校验
        searchArray.print();
    }


}


class SearchArray
{
    int[] array;
    int length;

    public SearchArray() {
    }

    public SearchArray(int length) {
        this.length = length;
        this.array = new int[length];
        //赋值
        for (int i = 0; i < length; i++)
        {
            array[i] = (int) (Math.random()*length);
        }
    }

    public void print()
    {
        for (int i = 0; i < length; i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}