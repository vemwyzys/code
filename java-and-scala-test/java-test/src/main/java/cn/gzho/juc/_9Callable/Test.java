package cn.gzho.juc._9Callable;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 6:21 PM
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread()).start();

        /**
         * FutureTask 使用Callable 两种方式
         */
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " futureTask1");
            return "futureTask1";
        });

        /**
         * futureTask需要Thread启动
         */
        new Thread(futureTask1, "f1").start();


        while (!futureTask1.isDone()) {
            System.out.println("wait");
        }
        System.out.println(futureTask1.get());

        System.out.println("----------------");

        System.out.println(futureTask1.get());


        new Thread(new FutureTask<>(() -> "null")).start();


        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                num += i;
            }
            return num;
        });

        new Thread(futureTask2).start();

    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("MyThread");
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return "MyCallable";
    }
}
