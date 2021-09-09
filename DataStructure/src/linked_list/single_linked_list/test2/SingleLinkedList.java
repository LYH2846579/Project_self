package linked_list.single_linked_list.test2;

/**
 * @author LYHstart
 * @create 2021-09-08 18:56
 *
 * 实现单链表基本的功能
 *
 * 总结：
 *  ① 在实现逆序与排序的时候，first和last指针需要进行改变!
 *  ② 在实现逆序与排序的时候，每插入一个结点，需要再次向后获取一个新的结点!   ->  要不然怎么结束！！！
 *  ③ 在实现排序的时候，需要对泛型的大小比较进行分析，需要考虑插入的位置(头插、尾插)等
 *  ④ 清空时需要注意length的修改和尾指针的修改
 */
public class SingleLinkedList<T extends Comparable>
{
    //初始头结点
    private SingleLinkedListNode<T> head;
    //头指针与尾指针
    private SingleLinkedListNode<T> first;
    private SingleLinkedListNode<T> last;
    //记录有效值长度
    private int length;

    public SingleLinkedList() {
        head = new SingleLinkedListNode<>();
        //初始化first & last指针
        this.first = this.last = head;
        head.setNext(null);
    }

    //判断是否为空
    public boolean isEmpty()
    {
        return this.first == this.last;
    }

    //尾插法
    public void pushBack(SingleLinkedListNode s)
    {
        //修改尾节点指向
        this.last.setNext(s);
        //修改last指向
        this.last = s;
        //修改有效值
        this.length++;
    }

    //尾删法
    public void popBack()
    {
        //判空
        if(isEmpty())
        {
            System.out.println("链表为空，无法尾删!");
            return;
        }
        else
        {
            //寻找倒数第二个结点
            SingleLinkedListNode temp = this.first;
            while(temp.getNext() != this.last)
            {
                temp = temp.getNext();
            }
            temp.setNext(null);
            this.last = temp;
            //修改有效值
            this.length--;
        }
    }

    //遍历
    public void print()
    {
        SingleLinkedListNode s = this.first.getNext();
        while(s != null && s != this.last)
        {
            System.out.print(s.toString()+"->");
            s = s.getNext();
        }
        if(s == this.last)
            System.out.print(s.toString()+"->nul.");
        System.out.println();
    }

    //头插
    public void pushFront(SingleLinkedListNode s)
    {
        if(isEmpty())
        {
            //若单链表为空
            this.first.setNext(s);
            this.last = s;
            this.length++;
        }
        else
        {
            s.setNext(this.first.getNext());
            this.first.setNext(s);
            this.length++;
        }
    }

    //头删
    public void popFront()
    {
        if(isEmpty())
        {
            System.out.println("链表为空，无法头删!");
        }
        else
        {
            SingleLinkedListNode temp = this.first.getNext();
            this.first.setNext(temp.getNext());
            //若删除的为最后一个元素
            if(temp == this.last)
                this.last = this.first;
            //释放结点
            temp.setNext(null);
            temp = null;
            //修改有效长度
            this.length--;
        }
    }

    //查找
    public SingleLinkedListNode<T> find(T t)
    {
        //遍历查询对应结点
        SingleLinkedListNode s = this.first.getNext();
        while(s != this.last && s.getData() != t)
        {
            s = s.getNext();
        }
        if(s == this.last && s.getData() != t)
        {
            //查询失败
            return null;
        }
        else if(s.getData() == t)
            return s;
        else
            return null;
    }

    //有效长度
    public int length()
    {
        return this.length;
    }

    //指定位置插入
    public void insert(SingleLinkedListNode s,int index)
    {
        if(index <= 0 || index > this.length+1)
        {
            //throw new RuntimeException("位置非法!");
            System.out.println("插入位置非法!");
            return;
        }
        else
        {
            //判断是否尾插
            if(index == this.length+1)
                this.pushBack(s);
            else if(index == 1)
                this.pushFront(s);
            else
            {
                int count = 0;
                SingleLinkedListNode temp = this.first;
                while(count < index-1)
                {
                    temp = temp.getNext();
                    count++;
                }
                //执行插入操作
                s.setNext(temp.getNext());
                temp.setNext(s);
                this.length++;
            }
        }
    }

    //逆序
    public void reverse()
    {
        //创建一个新结点
        SingleLinkedListNode<T> temp = new SingleLinkedListNode<>();
        //创建一个辅助结点
        SingleLinkedListNode<T> s = this.first.getNext();
        if(s == null)
            return;
        else
        {
            //直接修改last
            this.last = this.first.getNext();
            //开始进行原链表拆除
            while(s != null)
            {
                //一定注意不是this.first = s.getNext();
                this.first.setNext(s.getNext());
                //在新结点处执行头插
                s.setNext(temp.getNext());
                temp.setNext(s);
                s = this.first.getNext();
            }
            //修改first
            this.first = temp;
            temp = null;
        }
    }

    //排序 -> 与逆序类似       存在问题!!! -> 未修改first指针
    public void sort()
    {
        //增加一个判断标识
        int tag;
        //创建一个新结点
        SingleLinkedListNode<T> temp = new SingleLinkedListNode<>();
        //创建一个辅助结点
        SingleLinkedListNode<T> s = this.first.getNext();
        //创建另一个辅助结点
        SingleLinkedListNode<T> t = new SingleLinkedListNode<>();
        //再创建一个辅助结点
        SingleLinkedListNode<T> q = t;
        if(s == null)
            return;
        else
        {
            //逐步拆分原链表
            while(s != null)
            {
                this.first.setNext(s.getNext());
                //在新结点处执行按大小插入
                //判断是否为空!
                if(temp.getNext() == null)
                {
                    //直接进行头插
                    s.setNext(temp.getNext());
                    temp.setNext(s);
                    s = this.first.getNext();
                }
                else
                {
                    //依次查找
                    t = temp.getNext();
                    q = temp;
                    //泛型如何实现比大小?
                    while(true)
                    {
                        //调用泛型compareTo()       //当t == null时下面空指针检测会识别出来并进行尾插
                        tag = s.getData().compareTo(t.getData());
                        if(tag == 0)
                        {
                            //直接在t后插入
                            s.setNext(t.getNext());
                            t.setNext(s);
                            //别忘了获取下一个节点!
                            s = this.first.getNext();
                            break;
                        }
                        else if(tag > 0)
                        {
                            //继续向后遍历
                            q = t;
                            t = t.getNext();
                        }
                        else    //tag < 0
                        {
                            //在该元素前插入
                            s.setNext(t);
                            q.setNext(s);
                            //别忘了获取下一个节点!
                            s = this.first.getNext();
                            break;
                        }
                        //增加空指针异常校验机制 t == null
                        if(t == null)
                        {
                            s.setNext(null);
                            q.setNext(s);
                            //别忘了获取下一个节点!
                            s = this.first.getNext();
                            break;
                        }
                    }
                }
            }
            //修改first
            this.first = temp;
            //修改last
            s = this.first.getNext();
            if(s == null)
                this.last = s;
            else
            {
                while(s.getNext() != null)
                    s = s.getNext();
                this.last = s;
            }
        }
    }

    //清空
    public void clear()
    {
        //使用first和辅助指针依次释放结点
        SingleLinkedListNode s = this.first.getNext();
        while(s != null)
        {
            this.first.setNext(s.getNext());
            s.setNext(null);
            s = this.first.getNext();
        }
        //修改last
        this.last = this.first;
        //修改length
        this.length = 0;
    }

    //摧毁
    public void destroy()
    {
        this.clear();
        this.first = this.last = null;
    }
}
