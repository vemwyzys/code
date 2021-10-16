package cn.gzho.juc._4LockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 2:36 PM
 */
public class LockShare {
    public static void main(String[] args) {

        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"incr").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"decr").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"incr").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"decr").start();
    }
}

class Share {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"  "+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number!=1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"  "+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

