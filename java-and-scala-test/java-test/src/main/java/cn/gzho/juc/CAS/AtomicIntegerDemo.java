package cn.gzho.juc.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 9:29 PM
 */
public class AtomicIntegerDemo {

    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (num.get() < 1000) {
                    System.out.println(Thread.currentThread().getName() + " : " + num.incrementAndGet());
                }
            }).start();
        }
    }
}
