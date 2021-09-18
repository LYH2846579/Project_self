package sort;

import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-09-18 15:47
 *
 * 实现基数排序方式
 */
public class RadixSort
{
    @Test
    public void test()
    {
        //int[] array = {53,18,27,69,18,2,1,0,9,3,69,157,1024,1024172590};

        //超大数据量测试
        int[] array = new int[10000000];        //基数排序 耗时:11489
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) (Math.random()*500000);
        }

        //计数
        long time = System.currentTimeMillis();

        //数组+链表
        LinkedList[] listArray = new LinkedList[10];
        //创建10个链表，由数组进行保存
        for (int i = 0; i < 10; i++)
        {
            listArray[i] = new LinkedList<Integer>();
        }

        //确定数组中的最大元素
        int max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if(array[i] > max)
                max = array[i];
        }
        //计算遍历的次数
        int times = 0;          //使用count计数容易出现问题!!!
        while(max >= 10)
        {
            max /= 10;
            times++;
        }

        //开始实现分组
        //存储数量级
        int flag = 1;
        for (int i = 0; i < times+1; i++)       //注意范围,是times+1
        {
            //存储原数组下标
            int num = 0;

            //遍历，将数据加入到链表中
            for (int j = 0; j < array.length; j++)
            {
                int tag = (array[j] / flag) % 10;
                //将元素添加到链表中
                listArray[tag].add((Integer)array[j]);
            }

            //从第0位开始遍历，将元素从链表中取出
            for (int j = 0; j < listArray.length; j++)
            {
                LinkedList<Integer> list = listArray[j];
                int length = list.size();
                if(length != 0)
                {
                    Object[] array1 = list.toArray();
                    //将链表转换为的数组中的元素全部放入原数组中
                    for (int k = 0; k < length; k++,num++)
                    {
                        array[num] = (int)array1[k];
                    }
                }
                //记得将链表清空
                listArray[j].clear();
            }

            //将审查位数提升
            flag *=10;
        }

        System.out.println("基数排序 耗时:"+(System.currentTimeMillis()-time));
        //将数组中的元素输出
        //for (int i = 0; i < array.length; i++)
        //{
        //    System.out.print(array[i]+" ");
        //}
    }

}

