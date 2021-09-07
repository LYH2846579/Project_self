package sparse_array_and_queue.queue.array_queue;

import java.util.function.Predicate;

/**
 * @author LYHstart
 * @create 2021-09-07 19:22
 */
public class ArrayQueuePlus<T>
{
    private int front;
    private int rear;
    private T[] array;
    private int maxSize;

    public ArrayQueuePlus() {
    }
    public ArrayQueuePlus(int length)
    {
        this.front = 0;
        this.rear = 0;
        this.array = (T[]) new Object[length];
        this.maxSize = length;
    }

    //判断是否队列空
    public boolean isEmpty()
    {
        if(rear == front)
            return true;
        else
            return false;
    }

    //判断是否队列满
    public boolean isFull()
    {
        if((rear+1)%maxSize == front)
            return true;
        else
            return false;
    }

    //enQueue & deQueue
    public void enQueue(T t)
    {
        if(isFull())
        {
            //throw new RuntimeException("队列已满，无法入队列!");
            System.out.println("队列已满!");
        }
        else
        {
            array[rear] = t;
            System.out.println(t+"入队成功!");
            rear = (rear+1)%maxSize;
        }
    }
    public T deQueue()
    {
        T ans = null;
        if(isEmpty())
        {
            //throw new RuntimeException("队列已空，无法出队列!");
            System.out.println("队列已空!");
        }
        else
        {
            ans = array[front];
            System.out.println(ans+"出队成功!");
            front = (front+1)%maxSize;
        }
        return ans;
    }

    //遍历队列
    public void print()
    {
        for (int i = 0; i < maxSize; i++)
        {
            System.out.print(array[i]+"\t");
        }
    }


}

