package tree.HeapSort;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-25 14:55
 *
 * 这里尝试使用自己的思路对堆排序进行分析
 */
public class AnalysisHeapSortTest
{
    @Test
    public void test()
    {
        int[] arr = {0,9,8,7,6,5,4,3,2,1};

        ArrayTree2 arrayTree2 = new ArrayTree2(arr);

        HeapSort1 heapSort1 = new HeapSort1(arrayTree2);

        heapSort1.sort();

        //将排序结果输出
        heapSort1.getAt2().print();

    }
}

class HeapSort1
{
    private ArrayTree2 at2;
    private ArrayTree2 heap;

    public HeapSort1() {
    }
    public HeapSort1(ArrayTree2 at2) {
        this.at2 = at2;
        this.heap = new ArrayTree2(at2.getLength());
    }

    //排序入口
    public void sort()
    {
        //首先将原数组中的元素复制到辅助数组中，在辅助数组heap中创建小根堆
        for (int i = 0; i < at2.getLength(); i++)
        {
            heap.getArray()[i] = at2.getArray()[i];
        }

        //开始构建小根堆
        int curpos = at2.getLength()/2-1;
        while(curpos >= 0)
        {
            //开始进行小根堆调整
            siftDown(heap,heap.getLength(),curpos);
            curpos--;
        }

        //在进行完如上步骤之后，第一个小根堆已经调整完成，接下来不断取出元素填充
        for (int i = 0; i < heap.getLength(); i++)
        {
            //                          已经构建好的小根堆,取出当前循环中有效数据的位数
            at2.getArray()[i] = RemoveMinKey(heap,heap.getLength()-i-1);
        }
    }

    private void siftDown(ArrayTree2 heap, int length, int curpos)
    {
        int i = curpos;
        int j = curpos*2+1;
        //当该结点的左子节点小于有效数据长度值时 -> 进入循环
        while(j < length)
        {
            //判断是否存在右子树。倘若右子树存在且值小于左子树的值 -> 指向左子树
            if(j < length-1 && heap.getArray()[j]>heap.getArray()[j+1])
                j++;

            //添加如下一行代码就是为了防止重复代码提醒 -> 没啥作用
            int k = 0;

            //判断是否小于其左右子树中最小值
            if(heap.getArray()[i] <= heap.getArray()[j])
                break;
            else
            {
                //当不小于时，进行调整
                int temp = heap.getArray()[i];
                heap.getArray()[i] = heap.getArray()[j];
                heap.getArray()[j] = temp;
                //调整完毕之后，要再递归调整其子树，使得所有的结点满足小根堆
                i = j;
                j = 2*i+1;
            }
        }
    }

    private int RemoveMinKey(ArrayTree2 heap, int n)
    {
        //取出堆顶元素(当前堆内最小元素)
        int key = heap.getArray()[0];
        //将最后一个元素填充到堆顶
        heap.getArray()[0] = heap.getArray()[n];
        //继续调整为小根堆  ->  从堆顶元素开始调整           注意，此时数组的有效位为[0~4]五位元素。进入下一行之后变为length=4
        siftDown(heap,n,0);                 //       这就意味着只有四个有效元素
        //将取出的元素返回
        return key;
    }

    public ArrayTree2 getAt2() {
        return at2;
    }
}

class ArrayTree2
{
    private int[] array;
    private int length;

    public ArrayTree2() {
    }
    public ArrayTree2(int[] array) {
        this.array = array;
        this.length = array.length;
    }
    public ArrayTree2(int length) {
        this.length = length;
        this.array = new int[length];
    }

    public void print()
    {
        for(int i:array)
            System.out.print(i+" ");
    }

    public int[] getArray() {
        return array;
    }
    public int getLength() {
        return length;
    }
}
