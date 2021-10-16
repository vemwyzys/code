package cn.gzho.juc._2Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 1:52 PM
 */
public class LockTest {

    public static void main(String[] args) {
        LTicket ticket = new LTicket();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "b").start();
    }
}

class LTicket {
    private int number = 30;

    //可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + " : " + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
