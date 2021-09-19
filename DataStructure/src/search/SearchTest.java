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
                flag = true;            //此行是为了消除重复提示所用
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

    @Test
    public void fibSearchTest()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入所要查询的值:");
        int i = scan.nextInt();

        //创建原数组序列
        int[] array = {1,8,10,89,1000,1234};

        int index = fibSearch(array, i);
        if(index != -1)
            System.out.println("位置为:"+index);
        else
            System.out.println("该元素不存在!");
    }

    //生成斐波那契数列
    public int[] fib(int size)
    {
        int[] f = new int[size];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < size; i++)
        {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    //编写查找算法

    /**
     *
     * @param a    数组
     * @param key  key存放需要查找的代码
     * @return     返回对应的下标，若没有则返回-1
     */
    public int fibSearch(int[] a,int key)
    {
        int low = 0;
        int high = a.length-1;
        int k = 0;              //表示斐波那契分隔数值的下标
        int mid = 0;            //存放mid
        int[] f = fib(20); //获取斐波那契数列
        //获取斐波那契分割值的数值下标
        while(high > f[k] - 1)
            k++;
        //由于f[k]的值有可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //拷贝数组 -> 不足的部分使用0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        //将填充的0使用最后一位元素进行代替
        for (int i = high+1; i < temp.length; i++)
        {
            temp[i] = temp[high];
        }

        //寻找key
        while(low <= high)
        {
            mid = low + f[k-1] -1;          //这里需要看PPT上的图解
            if(key < temp[mid])             //在前半部分进行查找
            {
                high = mid - 1;
                k--;
                //f[k-1] = f[k-2] + f[k-3] - 1
            }
            else if(key > temp[mid])
            {
                low = mid + 1;
                k-=2;
            }
            else
            {
                if(mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        //若未查找到
        return -1;
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