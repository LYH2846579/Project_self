package sparse_array_and_queue.queue.array_queue;

import sun.security.util.Length;

/**
 * @author LYHstart
 * @create 2021-09-07 17:03
 *
 * 数组实现队列   ->   前移队列
 */
public class ArrayQueue<T>
{
    private int front;
    private int rear;
    private T[] array;
    private int maxSize;

    //Constructor
    public ArrayQueue() {
    }
    public ArrayQueue(int length) {
        //this.array = new T[length];   //不能实例化泛型数组
        //解决方案 -> 通过Object数组进行转化
        this.array = (T[]) new Object[length];
        this.maxSize = length;
    }
    public ArrayQueue(int front, int rear, T[] array) {
        this.front = front;
        this.rear = rear;
        this.array = array;
    }

    //enQueue & deQueue
    public void enQueue(T t)
    {
        //判断是否队列满
        if(rear <= maxSize-1)
        {
            array[rear++] = t;
            System.out.println(t+"入队成功!");
        }
        else
            System.out.println("队列已满!");
    }
    public T deQueue()
    {
        T ans = null;
        //判断队列是否为空
        if(front == rear)
        {
            //System.out.println("队列已空!无法出队");
            //抛出异常
            try
            {
                throw new MyException("队列已空!无法出队");
            } catch (MyException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            //获取出队元素
            ans = array[front];
            //队列前移
            T temp;
            for (int i = 0; i < rear-1; i++)    //由于下面为i+1,所以这里范围必须为rear-1
            {
                array[i] = array[i+1];
            }
            front = 0;      //front永远指向首位
            rear--;
        }
        return ans;
    }

    //遍历队列
    public void print()
    {
        System.out.print("队列输出:");
        for (int i = 0; i < rear; i++)
        {
            System.out.print(array[i]+"\t");
        }
    }

}

//自定义异常类
class MyException extends Exception
{
    static final long serialVersionUID = -3387516993124171717L;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
