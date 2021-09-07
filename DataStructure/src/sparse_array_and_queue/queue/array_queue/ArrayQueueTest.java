package sparse_array_and_queue.queue.array_queue;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-07 18:40
 */
public class ArrayQueueTest
{
    @Test   //普通数组队列
    public void test1()
    {
        //创建长度为5，存储整型的队列
        ArrayQueue<Integer> aq = new ArrayQueue<>(5);

        //验证队列满
        aq.enQueue(2);
        aq.enQueue(8);
        aq.enQueue(4);
        aq.enQueue(6);
        aq.enQueue(5);
        aq.enQueue(7);

        //出队
        Integer integer = aq.deQueue();
        System.out.println(integer+"已被移出队列");
        integer = aq.deQueue();
        System.out.println(integer+"已被移出队列");

        //再次入队
        aq.enQueue(7);
        aq.enQueue(9);


        aq.print();
    }

    @Test   //循环数组队列
    public void test2()
    {
        ArrayQueuePlus<Integer> aqp = new ArrayQueuePlus(8);

        aqp.enQueue(1);
        aqp.enQueue(2);
        aqp.enQueue(3);
        aqp.enQueue(4);
        aqp.enQueue(5);
        aqp.enQueue(6);
        aqp.enQueue(7);
        aqp.enQueue(8);
        aqp.enQueue(9);

        Integer integer = aqp.deQueue();
        integer = aqp.deQueue();
        integer = aqp.deQueue();

        aqp.enQueue(10);
        aqp.enQueue(11);
        aqp.enQueue(12);
        aqp.enQueue(13);

        aqp.print();
    }
}
