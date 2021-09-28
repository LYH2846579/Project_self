package tree.ThreadBinaryTree;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author LYHstart
 * @create 2021-09-23 14:36
 *
 * 实现中序线索化二叉树
 */
public class ThreadBinaryTreeTest
{
   @Test
    public void test1()
   {
       ThreadBinaryTree tree = new ThreadBinaryTree(new TreeNode(7));
       tree.addNode(new TreeNode(5));
       tree.addNode(new TreeNode(9));
       tree.addNode(new TreeNode(6));
       tree.addNode(new TreeNode(10));
       tree.addNode(new TreeNode(2));
       tree.addNode(new TreeNode(4));
       tree.addNode(new TreeNode(4));

       //tree.preOrder(tree.root);
       //System.out.println();
       tree.treeFlagSet(tree.root);       //确实扫描一遍
       tree.threadTree(tree.root);
       tree.print(tree.root);
       System.out.println("Hello Tree!");
   }

   @Test    //对中序线索化二叉树的结构进行加强测试
    public void test2()
   {
       ThreadBinaryTree tree = new ThreadBinaryTree(new TreeNode(7));
       tree.addNode(new TreeNode(5));
       tree.addNode(new TreeNode(6));
       tree.addNode(new TreeNode(1));
       tree.addNode(new TreeNode(3));
       tree.addNode(new TreeNode(2));
       tree.addNode(new TreeNode(4));
       tree.addNode(new TreeNode(9));
       tree.addNode(new TreeNode(8));
       tree.addNode(new TreeNode(10));

       tree.preOrder(tree.root);
       System.out.println();
       tree.treeFlagSet(tree.root);       //确实扫描一遍
       tree.threadTree(tree.root);
       tree.print(tree.root);
   }
}

//创建二叉树结点
class TreeNode
{
    private int data;
    private TreeNode lchild;
    private TreeNode rchild;
    //左右子树与前驱后继结点标识
    private boolean islchild;   //true为指向左子树,false为指向前驱结点
    private boolean isrchild;   //同上类似

    public TreeNode() {
    }
    public TreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public TreeNode getLchild() {
        return lchild;
    }
    public void setLchild(TreeNode lchild) {
        this.lchild = lchild;
    }
    public TreeNode getRchild() {
        return rchild;
    }
    public void setRchild(TreeNode rchild) {
        this.rchild = rchild;
    }
    public boolean isIslchild() {
        return islchild;
    }
    public void setIslchild(boolean islchild) {
        this.islchild = islchild;
    }
    public boolean isIsrchild() {
        return isrchild;
    }
    public void setIsrchild(boolean isrchild) {
        this.isrchild = isrchild;
    }
}
//创建二叉树
class ThreadBinaryTree
{
    TreeNode root;
    //中序线索化需要的辅助指针
    TreeNode pre;

    public ThreadBinaryTree() {
    }
    public ThreadBinaryTree(TreeNode root) {
        this.root = root;
    }


    //添加结点
    public void addNode(TreeNode node)
    {
        TreeNode temp = this.root;
        while(true)
        {
            while(node.getData() < temp.getData())
            {
                if(temp.getLchild() == null)
                {
                    temp.setLchild(node);
                    return;
                }
                else
                    temp = temp.getLchild();
            }
            if(node.getData() == temp.getData())
                return;
            if(temp.getRchild() == null)
            {
                temp.setRchild(node);
                return;
            }
            else
                temp = temp.getRchild();
        }
    }

    //前序遍历
    public void preOrder(TreeNode node)
    {
        if(node == null)
            return;
        System.out.print(node.getData()+" ");
        if(node.getLchild() != null)
            preOrder(node.getLchild());
        if(node.getRchild() != null)
            preOrder(node.getRchild());
    }

    //实现线索化
    public void threadTree(TreeNode node)   //这里进行中序线索化
    {
        //处理左子树
        if(node.isIslchild() && node.getLchild() != null)
            threadTree(node.getLchild());

        //处理当前结点
        if(!node.isIslchild())
            node.setLchild(pre);
        //设置前序结点的下一个结点
        if(pre != null && !pre.isIsrchild())
            pre.setRchild(node);
        //1.倘若无前序结点
        //if(pre == null)
        pre = node;
        //2.进行线索化   使得前序结点的右指针指向此节点???
        //if(!Node.isIslchild() && Node.getLchild()!=null && !Node.getLchild().isIsrchild())//根本用不上!
        //    Node.getLchild().setRchild(Node);

        //处理右子树
        if(node.isIsrchild() && node.getRchild() != null)
            threadTree(node.getRchild());
    }
    //将树中所有的标志位全部赋值 -> 这里采取层次遍历的方式进行实现
    public void treeFlagSet(TreeNode node)
    {
        LinkedQueue<TreeNode> queue = new LinkedQueue<>();
        if(node == null)
            return;
        else
        {
            queue.offer(node);
            while(!queue.isEmpty())
            {
                TreeNode temp = queue.poll();
                //System.out.print(temp.getData()+" ");
                if(temp.getLchild() != null)
                {
                    temp.setIslchild(true);
                    queue.offer(temp.getLchild());
                }
                if(temp.getRchild() != null)
                {
                    temp.setIsrchild(true);
                    queue.offer(temp.getRchild());
                }
            }
        }
    }

    //中序线索化二叉树的遍历
    public void print(TreeNode node)
    {
        //寻找未指向子树，且前驱结点为null的结点(其实从根节点一路向左即可)
        while(node.getLchild() != null)
            node = node.getLchild();

        //寻找到第一个结点,进入打印循环
        while(true)
        {
            System.out.print(node.getData()+" ");
            //不一定是它的右孩子!
            if(!node.isIsrchild())
                node = node.getRchild();
            else
            {
                node = node.getRchild();
                while(node.getLchild() != null && node.isIslchild())
                    node = node.getLchild();
            }
            //打印到最后一个结点的时候，退出循环
            if(!node.isIsrchild() && node.getRchild()==null)
            {
                System.out.println(node.getData());
                break;
            }
        }
    }
}


class LinkedQueue<T>
{
    //手撕链队列
    private LinkedList<T> list;
    private int length;

    public LinkedQueue() {
        this.list = new LinkedList<>();
    }
    public LinkedQueue(LinkedList<T> list) {
        this.list = list;
    }

    public void offer(T t)
    {
        list.offerLast(t);
        this.length++;
    }
    public T poll()
    {
        if(!list.isEmpty())
        {
            this.length--;
            return list.pollFirst();
        }
        else
            throw new RuntimeException("出队异常!");
    }

    public boolean isEmpty()
    {
        return this.length == 0;
    }

}



