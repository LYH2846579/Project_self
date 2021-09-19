package tree;

import org.junit.Test;

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
    }

}

//创建一颗二叉树
class Tree
{
    private TreeNode root;

    public Tree() {
    }
    public Tree(TreeNode root) {
        this.root = root;
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
                temp.setRchild(node);
            else
                temp = temp.getRchild();
        }

    }

    public TreeNode getRoot() {
        return root;
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
