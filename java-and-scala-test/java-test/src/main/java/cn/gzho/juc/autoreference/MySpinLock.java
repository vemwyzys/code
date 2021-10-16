package cn.gzho.juc.autoreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自制自旋锁
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 2:53 PM
 */
public class MySpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //lock
    public void lock() {
        System.out.println(Thread.currentThread().getName()+"->try lock");
        //自旋锁
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            //cas success=>pass
            //cas not success=>spin
        }
        System.out.println(Thread.currentThread().getName()+":cas success");
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName()+" unlock");
        atomicReference.compareAndSet(Thread.currentThread(), null);
    }


    public static void main(String[] args) {

        MySpinLock lock = new MySpinLock();

        new Thread(() -> {
            lock.lock();
            try {
                //cas success
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();//cas spin
            try {
                //cas success
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"b").start();

    }


}
