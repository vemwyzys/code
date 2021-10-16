package cn.gzho.volatileAndAtomic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 11:48 AM
 */
public class Test {

    private static volatile AtomicInteger num = new AtomicInteger(0);

    public static void increment() {
        num.getAndIncrement();
    }

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,
                10,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName());
                    increment();
                }
            });
        }

        while (Thread.activeCount() > 2) {
            System.out.println(Thread.currentThread());
            Thread.yield();
        }

        poolExecutor.shutdown();
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
