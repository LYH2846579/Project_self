package tree;

import org.junit.Test;

import java.util.*;

/**
 * @author LYHstart
 * @create 2021-09-19 16:52
 */
public class Order
{
    @Test
    public void test1()
    {
        Tree tree = new Tree(new TreeNode(7));
        tree.addNode(new TreeNode(2));
        tree.addNode(new TreeNode(8));
        tree.addNode(new TreeNode(10));
        tree.addNode(new TreeNode(9));
        tree.addNode(new TreeNode(1));

        //二叉树的遍历(递归)
        if(tree.getRoot() != null)
        {
            tree.preOrder(tree.getRoot());
            System.out.println();
            tree.inOrder(tree.getRoot());
            System.out.println();
            tree.postOrder(tree.getRoot());
            System.out.println();
        }
        else
            System.out.println("当前二叉树为空!");

        //二叉树结点查询(递归+双函数实现)
        int key = 10;
        TreeNode treeNode = tree.preFind(tree.getRoot(), key);
        if(treeNode != null)
            System.out.println("已查询到"+key+"结点存在");
        else
            System.out.println("经查询"+key+"结点不存在");

        //二叉树结点查询(while())
        TreeNode treeNode1 = tree.preFindWhile(tree.getRoot(), key);
        if(treeNode1 != null)
            System.out.println("已查询到"+key+"结点存在");
        else
            System.out.println("经查询"+key+"结点不存在");

        //老韩写的查询方式
        TreeNode treeNode2 = tree.preFindPlus(tree.getRoot(), key);
        if(treeNode2 != null)
            System.out.println("已查询到"+key+"结点存在");
        else
            System.out.println("经查询"+key+"结点不存在");
    }

    //测试删除结点
    @Test
    public void test2()
    {
        Tree tree = new Tree(new TreeNode(7));
        tree.addNode(new TreeNode(2));
        tree.addNode(new TreeNode(8));
        tree.addNode(new TreeNode(10));
        tree.addNode(new TreeNode(9));
        tree.addNode(new TreeNode(1));

        //删除结点1
        tree.preOrder(tree.getRoot());
        int key = 10;
        boolean delete = tree.delete(tree, key);
        System.out.println();
        if(delete)
            System.out.println("删除"+key+"成功");
        else
            System.out.println("删除失败!");
        System.out.println();
        tree.preOrder(tree.getRoot());
    }


    //层次遍历测试
    @Test
    public void test3()
    {
        Tree tree = new Tree(new TreeNode(7));
        tree.addNode(new TreeNode(2));
        tree.addNode(new TreeNode(8));
        tree.addNode(new TreeNode(10));
        tree.addNode(new TreeNode(9));
        tree.addNode(new TreeNode(1));

        tree.levelOrder(tree.getRoot());
    }

}

//创建一颗二叉树
class Tree
{
    private TreeNode root;

    //层次遍历需要的栈结构    ??? 使用队列才对!!!
    private Stack<TreeNode> stack;
    //private Queue<TreeNode> queue;
    private LinkedQueue<TreeNode> queue;

    public Tree() {
    }
    public Tree(TreeNode root) {
        this.root = root;
        this.stack = new Stack<>();
        this.queue = new LinkedQueue<>();
    }

    //添加结点
    public void addNode(TreeNode node)
    {
        TreeNode temp = this.root;
        //倘若小于根节点
        while(true)     //放入循环之中不断进行寻找
        {
            while(node.getData() < temp.getData())
            {
                //在左子树继续查找插入位置
                //1.当左结点不为空时
                if(temp.getLchild() != null)
                    temp = temp.getLchild();
                else
                {
                    //添加结点
                    temp.setLchild(node);
                    return;
                }
            }
            //判断相等
            if(temp.getData() == node.getData())
                return;
            //退出上述循环之后,开始在此节点的右子树继续查找
            //倘若不存在右子树
            if(temp.getRchild() == null)
            {
                temp.setRchild(node);
                return;
            }
            else
                temp = temp.getRchild();
        }

    }

    public TreeNode getRoot() {
        return root;
    }
    public void setRoot(TreeNode node)
    {
        this.root = node;
    }

    //Order
    //二叉树的遍历
    //preOrder
    public void preOrder(TreeNode root)
    {
        System.out.print(root.getData()+" ");
        if(root.getLchild() != null)
            preOrder(root.getLchild());
        if(root.getRchild() != null)
            preOrder(root.getRchild());
    }
    //inOrder
    public void inOrder(TreeNode root)
    {
        if(root.getLchild() != null)
            inOrder(root.getLchild());
        System.out.print(root.getData()+" ");
        if(root.getRchild() != null)
            inOrder(root.getRchild());
    }
    //postOrder
    public void postOrder(TreeNode root)
    {
        if(root.getLchild() != null)
            postOrder(root.getLchild());
        if(root.getRchild() != null)
            postOrder(root.getRchild());
        System.out.print(root.getData()+" ");

    }

    //简单实现前序查找  -> (递归+双函数)
    public TreeNode preFind(TreeNode root,int key)
    {
        if(root.getData() == key)
            return root;
        else if(root.getData() > key)
        {
            //倘若根节点的值较大
            if(root.getLchild() != null)
            {
                TreeNode treeNode = preFindRecursion(root.getLchild(), key);
                return treeNode;
            }
            else //左子树为空
                return null;
        }
        else //root.getData() < key
        {
            //倘若根节点的值比较小
            if(root.getRchild() != null)
            {
                return preFindRecursion(root.getRchild(),key);
            }
            else //右子树为空
                return null;
        }

    }
    public TreeNode preFindRecursion(TreeNode root,int key)
    {
        if(root.getData() == key)
            return root;
        else if(root.getLchild() != null)
        {
            return preFindRecursion(root.getLchild(),key);
        }
        else if(root.getRchild() != null)
        {
            return preFindRecursion(root.getRchild(),key);
        }
        else
            return null;
    }

    //前序查找,非递归方式实现 -> while()
    public TreeNode preFindWhile(TreeNode root,int key)
    {
            if(root.getData() == key)
                return root;
            else if(root.getData() > key)
            {
                //在左子树中查询
                if(root.getLchild() != null)
                {
                    root = root.getLchild();
                }
                else
                {
                    //倘若左子树为空
                    return null;
                }
                //进入左子树循环
                while(true)
                {
                    if(root.getData() == key)
                        return root;
                    while(root.getData() > key && root.getLchild() != null)
                    {
                        root = root.getLchild();
                    }
                    if(root.getData() > key && root.getLchild() == null)
                    {
                        return null;
                    }
                    else
                    {
                        //判断右子树是否存在
                        if(root.getRchild() != null)
                        {
                            root = root.getRchild();
                        }
                        else
                            return null;
                    }

                }
            }
            else //root.getData() < key
            {
                //在右子树中查询
                if(root.getRchild() != null)
                {
                    root = root.getRchild();
                }
                else //右子树为空
                    return null;
                //进入右子树循环
                while(true)
                {
                    if(root.getData() == key)
                        return root;
                    while(root.getData() < key && root.getRchild() != null)
                    {
                        root = root.getRchild();
                    }
                    if(root.getData() < key && root.getRchild() == null)
                    {
                        return null;
                    }
                    else
                    {
                        //从左子树搜寻
                        root = root.getLchild();
                    }
                }
            }
    }


    //单函数递归实现查询的方式  -> 老师的思路
    public TreeNode preFindPlus(TreeNode root,int key)
    {
        //判断是否是根节点
        if(root.getData() == key)
            return root;

        //获取查询变量
        TreeNode temp = null;
        //判断是否有左子树
        if(root.getLchild() != null)
        {
            temp = preFindPlus(root.getLchild(),key);
        }
        //判断temp
        if(temp != null)
            return temp;

        //再次判断右结点
        if(root.getRchild() != null)
            temp = preFindPlus(root.getRchild(),key);
        return temp;
    }

    //二叉树结点的删除
    public boolean delete(Tree tree,int key)
    {
        if(tree.getRoot() == null)
            return false;
        else if(tree.root.getData() == key)
        {
            tree.setRoot(null);
            return true;
        }
        else
        {
            return delNode(tree.root,key);
        }
    }

    private boolean delNode(TreeNode root, int key)
    {
        if(root.getLchild()!=null && root.getLchild().getData() == key)
        {
            root.setLchild(null);
            return true;
        }
        else if(root.getRchild()!=null && root.getRchild().getData() == key)
        {
            root.setRchild(null);
            return true;
        }

        boolean b = false;
        if(root.getLchild() != null)
        {
            b = delNode(root.getLchild(), key);
        }
        if(b == true)
            return b;
        if(root.getRchild() != null)
            return delNode(root.getRchild(),key);
        return false;
    }


    //实现层次遍历    -> 使用手写队列成功实现!
    public void levelOrder(TreeNode node)
    {
        if(node != null)
            queue.offer(node);
        while(!queue.isEmpty())
        {
            TreeNode temp = queue.poll();
            System.out.print(temp.getData()+" ");
            if(temp.getLchild() != null)
                queue.offer(temp.getLchild());
            if(temp.getRchild() != null)
                queue.offer(temp.getRchild());
        }
    }
}
//二叉树的结点
class TreeNode
{
    private int data;
    private TreeNode lchild;
    private TreeNode rchild;

    public TreeNode() {
    }
    public TreeNode(int data) {
        this.data = data;
        this.lchild = null;
        this.rchild = null;
    }
    public TreeNode(int data, TreeNode lchild, TreeNode rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
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
