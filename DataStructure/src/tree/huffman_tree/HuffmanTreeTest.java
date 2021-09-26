package tree.huffman_tree;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-09-26 16:15
 *
 * 实现HuffmanTree
 */
public class HuffmanTreeTest
{
    @Test
    public void test()
    {
        int[] arr = {13,7,8,3,29,6,1};
        HuffmanTree tree = new HuffmanTree(arr);
        tree.createHuffmanTree();
        tree.preOrder();
    }
}

//HuffmanTree
class HuffmanTree
{
    //原始数组
    private int[] array;
    //创建的HuffmanTree的根节点
    HuffmanTreeNode root;
    //存储结点的单链表
    LinkedList<HuffmanTreeNode> list;

    public HuffmanTree(int[] array) {
        this.array = array;
        this.list = new LinkedList<>();
    }

    //创建HuffmanTree
    public void createHuffmanTree()
    {
        //将数组中的所有结点装载到链表之中
        for (int i = 0; i < array.length; i++)
        {
            list.offerLast(new HuffmanTreeNode(array[i]));
        }
        //进入循环之中
        while(list.size() != 1)
        {
            //将结果排序
            Collections.sort(list);
            //取出前两个值最小的元素来
            HuffmanTreeNode node1 = list.pollFirst();
            HuffmanTreeNode node2 = list.pollFirst();
            //创建这两个元素的父结点
            HuffmanTreeNode node = new HuffmanTreeNode(node1.getData()+node2.getData());
            //这里让左结点永远小于等于右节点
            node.setLchild(node1);
            node.setRchild(node2);
            //将该结点加入链表
            list.offerLast(node);
        }
        //退出循环的时候 -> root即为生成的HuffmanTree
        root = list.peekFirst();
    }

    //打印HuffmanTree
    public void preOrder()
    {
        if(root == null)
            return;
        preOrderPlus(this.root);
    }
    public void preOrderPlus(HuffmanTreeNode root)
    {
        System.out.print(root.getData()+" ");
        if(root.getLchild() != null)
            preOrderPlus(root.getLchild());
        if(root.getRchild() != null)
            preOrderPlus(root.getRchild());
    }
}

//HuffmanTreeNode
class HuffmanTreeNode implements Comparable
{
    private int data;
    private HuffmanTreeNode lchild;
    private HuffmanTreeNode rchild;

    public HuffmanTreeNode(int data) {
        this.data = data;
    }

    public HuffmanTreeNode getLchild() {
        return lchild;
    }
    public void setLchild(HuffmanTreeNode lchild) {
        this.lchild = lchild;
    }
    public HuffmanTreeNode getRchild() {
        return rchild;
    }
    public void setRchild(HuffmanTreeNode rchild) {
        this.rchild = rchild;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof HuffmanTreeNode)
            return this.data-((HuffmanTreeNode) o).data;
        else
            throw new RuntimeException("对象类型异常!");
    }
}





