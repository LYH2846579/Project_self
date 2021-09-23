package tree;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-23 10:16
 *
 * 对使用数组顺序存储二叉树进行详细学习
 */
public class ArrayTreeTest
{
    @Test
    public void test1()
    {
        int[] array = {1,2,3,4,5,6,7};

        ArrayTree arrayTree = new ArrayTree(array);
        arrayTree.preOrder(0);
    }
}

class ArrayTree
{
    private int[] array;
    private int length;

    public ArrayTree(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    //前序遍历顺序存储的二叉树
    public void preOrder(int index)
    {
        if(array == null || length == 0)
            return;
        if(index < length)
            System.out.print(array[index]+" ");
        if(index*2+1 < length)
            preOrder(index*2+1);
        if(index*2+2 < length)
            preOrder(index*2+2);
    }

    //中序遍历和后续遍历仅需修改位置和方法
}
