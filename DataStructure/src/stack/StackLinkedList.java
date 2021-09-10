package stack;

/**
 * @author LYHstart
 * @create 2021-09-10 19:44
 */
public class StackLinkedList<T>
{
    private LinkedListNode<T> first;
    private LinkedListNode<T> top;
    private int length;

    public StackLinkedList() {
        this.first = new LinkedListNode<>();
        this.top = this.first;
    }

    //入栈
    public void push(T t)
    {
        LinkedListNode node = new LinkedListNode(t);

        //进行尾插
        this.top.setNext(node);
        top = node;
        //修改length
        this.length++;
    }

    //出栈
    public void pop()
    {
        if(isEmpty())
        {
            System.out.println("栈已空，无法进行出栈!");
            return;
        }
        else
        {
            //使用循环寻找last
            LinkedListNode temp = this.first.getNext();
            //当栈中只有一个元素时
            if(length == 1)
            {
                this.first.setNext(null);
                this.top = this.first;
                length--;
            }
            else
            {
                while(temp.getNext() != this.top)
                {
                    temp = temp.getNext();
                }
                //当下一个结点为top指向的结点时
                top = temp;
                top.setNext(null);
                this.length--;
            }
        }
    }

    //获取栈顶元素
    public T getTop()
    {
        if(isEmpty())
        {
            System.out.println("栈已空，无法获取栈顶元素!");
            return null;
        }
        else
        {
            return this.top.getData();
        }
    }

    //遍历
    //实际上，应该将入栈口设置于头结点之后!!!

    //判空机制  -> 使用length即可!
    public boolean isEmpty()
    {
        if(top == this.first)
            return true;
        else
            return false;
    }
}
