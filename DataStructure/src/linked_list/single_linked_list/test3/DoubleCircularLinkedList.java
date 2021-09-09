package linked_list.single_linked_list.test3;

import linked_list.single_linked_list.test2.SingleLinkedListNode;

/**
 * @author LYHstart
 * @create 2021-09-09 15:06
 *
 * 无头结点的双向循环链表实现
 *
 * 总结：
 *  [1] 在逆置、排序、清空等过程中要时刻维护着双向循环链表的结构!
 *  [2] 在没有头结点的结构中，清除和摧毁在一定程度上等价
 *  [3] 使用查找find可以实现快速删除(指定元素变量)
 *  [4] 逆序时，初始化辅助链表需要形成自循环双向链表，这样才不会出现空指针异常!
 *  [5] 排序时，使用原链表头结点实现自循环链表时，一定不要忘记维护源链表的结构!!
 *  [6] 如何在逆序、排序之后寻找到first、last指针指向是关键! 小心陷入死循环!
 *
 */
public class DoubleCircularLinkedList<T extends Comparable>
{
    //私有属性
    private DoubleCircularLinkedListNode first;
    private DoubleCircularLinkedListNode last;
    private int length;

    public DoubleCircularLinkedList() {
    }

    //pushBack(T t)
    public void pushBack(DoubleCircularLinkedListNode node)
    {
        if(this.first == null)
        {
            //若链表为空，创建新的结点
            this.first = node;
            this.last = this.first;
            //设置指针
            this.first.setPre(this.last);
            this.last.setNext(this.first);
            //修改length
            this.length++;
        }
        else
        {
            //执行尾插
            DoubleCircularLinkedListNode d = node;
            //1.修改新结点左区间指向
            this.last.setNext(d);
            d.setPre(this.last);
            //2.修改新结点右区间指向
            d.setNext(this.first);
            this.first.setPre(d);
            //修改last
            this.last = d;
            //修改length
            this.length++;
        }
    }

    //尾删
    public void popBack()
    {
        if(isEmpty())
        {
            System.out.println("链表已空，无法进行尾删！");
            return;
        }
        else
        {
            DoubleCircularLinkedListNode d = this.last;
            //判断是否仅有一个结点
            if(d == this.first)
            {
                //删除后链表为空
                this.first = this.last = null;
                d.setPre(null);
                d.setNext(null);
                d = null;
                //修改长度
                this.length--;
            }
            else
            {
                //删除后链表仍有结点
                d.getNext().setPre(d.getPre());
                d.getPre().setNext(d.getNext());
                //修改last指针
                this.last = d.getPre();
                //置空自身指针
                d.setPre(null);
                d.setNext(null);
                d = null;
                this.length--;
            }
        }
    }

    //头插
    public void pushFront(DoubleCircularLinkedListNode node)
    {
        //判空
        if(isEmpty())
        {
            //直接插入  ->  调用尾插即可
            this.pushBack(node);
        }
        else
        {
            //修改新结点右区间指向
            node.setNext(this.first);
            this.first.setPre(node);
            //修改新结点左区间指向
            node.setPre(this.last);
            this.last.setNext(node);
            //修改first
            this.first = node;
            //修改length
            this.length++;
        }
    }

    //头删
    public void popFront()
    {
        if(isEmpty())
        {
            System.out.println("链表已空，无法进行头删！");
            return;
        }
        else
        {
            DoubleCircularLinkedListNode d = this.first;
            //判断是否只有一个结点
            if(this.first == this.last)
            {
                //删除后链表为空 -> 直接调用尾删
                popBack();
            }
            else
            {
                //删除后不为空
                //1.修改头结点左区间指向
                d.getPre().setNext(d.getNext());
                //2.修改头结点右区间指向
                d.getNext().setPre(d.getPre());
                //修改头指针
                this.first = d.getNext();
                //3.修改头结点自身指针
                d.setPre(null);
                d.setNext(null);
                //修改length
                this.length--;
            }
        }
    }

    //判空
    public boolean isEmpty()
    {
        if(this.first == null && this.last == null)
            return true;
        else
            return false;
    }

    //遍历
    public void print()
    {
        if(this.first == null)
            return;
        DoubleCircularLinkedListNode d = this.first;
        while(d != null && d != this.last)
        {
            System.out.print(d.toString()+"->");
            d = d.getNext();
        }
        if(d == this.last)
            System.out.print(d.toString()+"->nul.");
        System.out.println();
    }

    //查找    ->   用于删除指定元素
    public DoubleCircularLinkedListNode find(T t)
    {
        if(isEmpty())
        {
            System.out.println("链表为空，无法进行查询!");
            return null;
        }
        else
        {
            DoubleCircularLinkedListNode d = this.first;
            while(d != this.last)
            {
                if(d.getData().equals(t))
                    break;
                else
                    d = d.getNext();
            }
            if((d == this.last && d.getData().equals(t)) || (d != this.last))
            {
                return d;
            }
            else
            {
                System.out.println("该结点未查询到!");
                return null;
            }
        }
    }

    //指定元素删除
    public void deleteElm(T t)
    {
        DoubleCircularLinkedListNode d = this.find(t);
        if(d == null)
        {
            System.out.println("结点删除失败!");
            return;
        }
        else
        {
            //直接删除即可?
            //1.判断是否是唯一元素 + 头删尾删处理
            if((d == this.first))
            {
                popFront();         //内置处理唯一元素方式
            }
            else if(d == this.last)
            {
                popBack();
            }
            else
            {
                //一般位置删除
                //修改前后结点指针
                d.getNext().setPre(d.getPre());
                d.getPre().setNext(d.getNext());
                //置空自身指针
                d.setPre(null);
                d.setNext(null);
                d = null;
                //修改length
                length--;
            }
        }
    }

    //指定位置插入
    public void insert(DoubleCircularLinkedListNode node,int index)
    {
        if(index <= 0 || index > this.length+1)
        {
            System.out.println("插入位置不合法!");
            return;
        }
        else
        {
            //若链表为空!
            if(isEmpty())
            {
                pushBack(node);
            }
            else if(index == 1)   //头插
            {
                pushFront(node);
            }
            else if(index == length+1)
            {
                pushBack(node);
            }
            else
            {
                //普通插入
                //1.寻找指定位置
                DoubleCircularLinkedListNode d = this.first;
                int count = 1;
                while(count < index && d != this.last)
                {
                    d = d.getNext();
                    count++;
                }
                //2.在位置上执行前端插入(双向链表就是爽)
                node.setPre(d.getPre());
                node.setNext(d);
                d.getPre().setNext(node);
                d.setPre(node);
                length++;
            }
        }
    }

    //逆序    ->   与单链表逆序类似
    public void reverse()
    {
        //判断是否为空或者仅存在一个结点
        if(this.length == 0 || this.length == 1)
            return;
        else
        {
            //当存在两个以上结点时
            //创建一个新的辅助结点(暂时的头结点)
            DoubleCircularLinkedListNode<T> temp = new DoubleCircularLinkedListNode<>();
            //初始化的辅助链表必须为双向循环链表!!!
            temp.setPre(temp);
            temp.setNext(temp);
            //创建遍历指针
            DoubleCircularLinkedListNode<T> d = this.first;
            while(d != this.last)       //不断从头部取出结点
            {
                //修改first指向
                this.first = d.getNext();
                //将该结点取出
                d.getPre().setNext(d.getNext());
                d.getNext().setPre(d.getPre());
                //添加到新结点队列 -> 头插
                d.setNext(temp.getNext());
                d.setPre(temp);
                temp.getNext().setPre(d);
                temp.setNext(d);
                //取出下一个结点
                d = this.first;
                if(d == this.last)
                    break;
            }
            //将最后一个尾节点取出
            //添加到新结点队列 -> 头插
            d.setNext(temp.getNext());
            d.setPre(temp);
            temp.getNext().setPre(d);
            temp.setNext(d);
            //修改first
            this.first = temp;
            //寻找last
            d = this.first.getNext();
            //一定注意循环条件!!!       ->      极有可能造成死循环!
            while(d.getNext() != this.first)
            {
                d = d.getNext();
            }
            this.last = d;
            //删除头结点
            this.last.setNext(this.first.getNext());
            this.first.getNext().setPre(this.last);
            d = this.first;
            this.first = d.getNext();
            //置空自身指针
            d.setPre(null);
            d.setNext(null);
        }
    }

    //排序        -> 是否存在不可靠的地方?
    public void sort()
    {
        //判断是否为空或者一个元素
        if(length == 0 || length == 1)
            return;
        else
        {
            //进行进一步判断
            //将以使用无头结点的方式进行实现
            //1.将原链表进行拆解 -> 创建辅助指针
            DoubleCircularLinkedListNode<T> d = this.first;
            //将first指针后移
            this.first = d.getNext();
            //将d拆解下来
            d.getPre().setNext(d.getNext());
            d.getNext().setPre(d.getPre());
            //设置成自循环链表
            d.setPre(d);
            d.setNext(d);
            //创建新链表头指向
            DoubleCircularLinkedListNode<T> temp = d;
            //创建新链表插入辅助指针 -> 用于循环遍历新链表以寻找插入位置
            DoubleCircularLinkedListNode<T> t = temp;
            //进入原链表拆解循环
            while(true)
            {
                //再次拆解下一个结点
                d = this.first;
                //当已经将最后一个结点拆除之后，退出拆除循环
                if(d == null)
                    break;

                if(d != this.last)          //当为最后一个结点时，停止拆除
                {
                    this.first = d.getNext();
                    //将原来的链表还原为双向循环链表
                    d.getPre().setNext(d.getNext());
                    d.getNext().setPre(d.getPre());
                }
                else
                    this.first = null;

                //接下来分析插入点
                t = temp;
                while(true)
                {
                    if(d.getData().compareTo(t.getData()) == 0)
                    {
                        //若两者相等 -> 直接尾插
                        d.setPre(t);
                        d.setNext(t.getNext());
                        t.getNext().setPre(d);
                        t.setNext(d);
                        break;
                    }
                    else if(d.getData().compareTo(t.getData()) > 0)     //继续向后查找
                    {
                        if(t.getNext() == temp)     //当已经到最后的时候 -> 最大值
                        {
                            //直接进行尾插
                            d.setNext(t.getNext());
                            d.setPre(t);
                            t.getNext().setPre(d);
                            t.setNext(d);
                            break;
                        }
                        else
                            t = t.getNext();
                    }
                    else        //balababala<0
                    {
                        //直接进行头插
                        //判断是否是最小值
                        if(t == temp)
                            temp = d;
                        d.setNext(t);
                        d.setPre(t.getPre());
                        t.getPre().setNext(d);
                        t.setPre(d);
                        break;
                    }
                }
            }
            //修改first
            this.first = temp;
            //寻找last
            t = temp;
            while(t.getNext() != temp)
            {
                t = t.getNext();
            }
            this.last = t;
        }
    }

    //清空
    public void clear()
    {
        if(length == 0)
            return;
        else
        {
            DoubleCircularLinkedListNode<T> d = this.first.getNext();
            while(d != this.first)
            {
                //还原双向循环链表
                d.getNext().setPre(d.getPre());
                d.getPre().setNext(d.getNext());
                //自身指针置空
                d.setPre(null);
                d.setNext(null);
                //获取下一个结点
                d = this.first.getNext();
            }
            this.first = this.last = null;
            d = null;
            length = 0;
        }
    }

    //摧毁
    public void destroy()
    {
        this.clear();
        //好像和清除每区别! 呆
    }
}

