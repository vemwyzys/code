package cn.gzho.juc._11CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 *
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 7:12 PM
 */
public class Test {

    private static final int NUM = 7;

    public static void main(String[] args) {

        //循环栅栏
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(NUM, () -> System.out.println("enough"));
        for (int i = 0; i < NUM; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    cyclicBarrier.await();
                } catch (Exception e) {

                }
            }, String.valueOf(i)).start();
        }


        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(10, () -> System.out.println("enough1"));
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    cyclicBarrier1.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
