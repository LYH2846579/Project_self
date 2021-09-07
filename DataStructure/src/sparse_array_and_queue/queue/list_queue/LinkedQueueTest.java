package sparse_array_and_queue.queue.list_queue;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-07 20:42
 */
public class LinkedQueueTest
{
    @Test   //对于循环队列的测试
    public void test1()
    {
        LinkedQueue<Integer> lq = new LinkedQueue<>();

        lq.enQueue(1);
        lq.enQueue(2);
        lq.enQueue(3);

        lq.deQueue();
        lq.deQueue();
        lq.deQueue();
        lq.deQueue();

        lq.enQueue(4);
        lq.enQueue(5);
        lq.enQueue(6);

        lq.print();

    }
}
