package cn.gzho.juc._14ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 7:42 AM
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        /**
         * add remove
         * has exception
         */
        System.out.println("add&remove");
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.add(1);
//        blockingQueue.add(1);
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        /**
         * offer poll
         * No exception
         */
        System.out.println("offer&poll");
        blockingQueue.offer(1);
        blockingQueue.offer(1);
        blockingQueue.offer(1);
        blockingQueue.offer(1,2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        /**
         * put take
         * with blocking
         */
        System.out.println("put&take");
        blockingQueue.put(1);
        blockingQueue.put(1);
        blockingQueue.put(1);
//        blockingQueue.put(1);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());

    }

}

class Resource {

    ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

    public void put(Integer integer) {
        blockingQueue.add(integer);
    }

    public Integer get() {
        return blockingQueue.remove();
    }
}