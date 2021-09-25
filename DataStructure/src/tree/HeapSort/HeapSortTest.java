package tree.HeapSort;

import org.junit.Test;


/**
 * @author LYHstart
 * @create 2021-09-25 8:30
 */
public class HeapSortTest
{
    @Test
    public void test()
    {
        int[] arr= {4,6,8,5,9,10};

        ArrayTree1 ArrayTree1 = new ArrayTree1(arr);

        HeapSort heapSort = new HeapSort(ArrayTree1);

        heapSort.sort();

        heapSort.getArray().print();
    }

    public static void main(String[] args) {
        int[] arr= {4,6,8,5,9};

        ArrayTree1 ArrayTree1 = new ArrayTree1(arr);

        HeapSort heapSort = new HeapSort(ArrayTree1);

        heapSort.sort();

        heapSort.getArray().print();
    }
}


//实现堆排序
class HeapSort
{
    private ArrayTree1 array;
    private ArrayTree1 temp;

    public HeapSort() {
    }
    public HeapSort(ArrayTree1 array) {
        this.array = array;
        this.temp = new ArrayTree1(array.getLength());
    }
    
    //排序
    public void sort()
    {
        //将原数组进行拷贝
        for (int i = 0; i < array.getLength(); i++)
        {
            temp.getArray()[i] = array.getArray()[i];
        }

        //找到第一个非叶子节点
        int curpos = array.getLength()/2-1;
        //从这一个节点开始到根节点依次调整
        while(curpos >= 0)
        {
            siftDown(temp,temp.getLength(),curpos);
            //调整完成之后，调整下一个结点
            curpos--;
        }

        //此时小根堆已经建立
        for (int i = 0; i < array.getLength(); i++)
        {
            array.getArray()[i] = RemoveMinKey(temp,temp.getLength()-i-1);
        }
    }

    private int RemoveMinKey(ArrayTree1 temp,int n) {
        int key = temp.getArray()[0];
        temp.getArray()[0] = temp.getArray()[n];
        siftDown(temp,n,0);
        return key;
    }

    //构建大根堆             //数组，第一个非叶子节点
    public void siftDown(ArrayTree1 temp, int n, int curpos)
    {
        //注意数组存储数的特性(这里使用顺序存储的方式)
        int i = curpos;
        int j = 2*i+1;  //寻找到此结点的左子树
        while(j < n)         //为了在递归实现深层调整的时候不陷入死循环
        {
            //倘若该非叶子节点存在右子树且右子树比左子树小
            if(j<n-1 && temp.getArray()[j]>temp.getArray()[j+1])
                j++;
            //如果该父结点比其最小的叶子节点还要小
            if(temp.getArray()[i] <= temp.getArray()[j])
                break;
            else
            {
                //进行调整
                int num = temp.getArray()[i];
                temp.getArray()[i] = temp.getArray()[j];
                temp.getArray()[j] = num;
                //调整完成之后，递归处理子节点
                i = j;
                j = 2*i+1;
            }
        }
    }

    public ArrayTree1 getArray() {
        return array;
    }
}


//树存储数组
class ArrayTree1
{
    private int[] array;
    private int length;

    public ArrayTree1(int[] array) {
        this.array = array;
        this.length = array.length;
    }
    public ArrayTree1(int length) {
        //由于这里仅对整型元素进行处理，所以直接设定为int类型
        array = new int[length];
        this.length = length;
    }

    public void print()
    {
        for(int i:array)
            System.out.print(i+" ");
    }

    public int getLength() {
        return length;
    }
    public int[] getArray()
    {
        return array;
    }
}
