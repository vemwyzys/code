package cn.gzho.juc._10CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 6:42 PM
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "结束");
                //计数--
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "扫尾");


        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch1.countDown();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch1.await();
        System.out.println("complete");
    }

}

class CountDownLatchDemo {

}
