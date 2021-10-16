package cn.gzho.juc.diyDeadLock;

import java.util.concurrent.TimeUnit;

/**
 * diy deadlock
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 8:28 PM
 */
public class DeadLock implements Runnable {

    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " get lock:" + lockA);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " get lock:" + lockB);
            }
        }
    }


    public static void main(String[] args) {

        new Thread(new DeadLock("a","b"),"nick").start();
        new Thread(new DeadLock("b","a"),"nick").start();

    }
}
