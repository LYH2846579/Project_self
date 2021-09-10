package stack;

/**
 * @author LYHstart
 * @create 2021-09-10 19:20
 */
public class StackArray<T>
{
    private T[] array;
    private int capacity;
    private int top;

    public StackArray() {
    }

    public StackArray(int capacity) {
        this.capacity = capacity;
        //泛型必须这样实现!
        this.array = (T[]) new Object[capacity];
        this.top = -1;
    }

    //入栈
    public void push(T t)
    {
        if(isFull())
        {
            System.out.println("栈已满，无法入栈");
            return;
        }
        else
        {
            this.top++;
            array[top] = t;
        }
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
            //T temp = array[top--];
            top--;
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
            T temp = array[top];        //获取栈顶元素不需要top--
            return temp;
        }
    }

    //输出栈内元素,自顶向下输出
    public void print()
    {
        for (int i = top; i >= 0; i--)
        {
            System.out.println(array[i]);
            if(i != 0)
            {
                System.out.println("↓");
            }

        }
    }

    //判空机制
    public boolean isEmpty()
    {
        if(top == -1)
            return true;
        else
            return false;
    }
    //判满机制
    public boolean isFull()
    {
        if(top == capacity-1)
            return true;
        else
            return false;
    }
}
