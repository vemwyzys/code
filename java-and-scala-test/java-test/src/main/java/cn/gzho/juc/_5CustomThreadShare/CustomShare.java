package cn.gzho.juc._5CustomThreadShare;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 2:52 PM
 */
public class CustomShare {

    public static void main(String[] args) {
        CustomShare share = new CustomShare();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"con1T").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"con2T").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"con3T").start();
    }

    private int flag = 1;

    private Lock lock = new ReentrantLock();

    private Condition con1 = lock.newCondition();
    private Condition con2 = lock.newCondition();
    private Condition con3 = lock.newCondition();

    public void print1() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                con1.await();
            }
            System.out.println(Thread.currentThread().getName() + " con1");
            flag = 2;
            con2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print2() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                con2.await();
            }
            System.out.println(Thread.currentThread().getName() + " con2");
            flag = 3;
            con3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print3() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                con3.await();
            }
            System.out.println(Thread.currentThread().getName() + " con3");
            flag = 1;
            con1.signal();
        } finally {
            lock.unlock();
        }
    }
}
