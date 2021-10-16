package cn.gzho.juc.pool;

import java.util.concurrent.*;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 8:15 AM
 */
public class Test {

    public static void main(String[] args) {
        //单线程池
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                //使用线程池创建线程
                pool1.execute(() -> System.out.println(Thread.currentThread().getName() + "run"));
            }
        } finally {
            //线程池一定要关闭
            pool1.shutdown();
        }

        //固定个数线程池
        ExecutorService pool3 = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                pool3.execute(() -> System.out.println(Thread.currentThread().getName() + "Run"));
            }
        } finally {
            pool3.shutdown();
        }


        ExecutorService poolCache = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                poolCache.execute(() -> System.out.println(Thread.currentThread().getName() + "RunR"));
            }
        } finally {
            poolCache.shutdown();
        }

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"poolrun");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
