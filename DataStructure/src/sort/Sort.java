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
