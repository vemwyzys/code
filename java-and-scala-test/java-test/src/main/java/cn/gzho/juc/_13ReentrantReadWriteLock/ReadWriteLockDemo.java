package cn.gzho.juc._13ReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 7:30 PM
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}

//resource class
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    //write read lock
    //写锁=》独占锁
    //读锁=》共享锁  读都可以读，只要没人在写就行
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        Lock writeLock = rwLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " is writing " + key);
        writeLock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
        } catch (Exception e) {
        } finally {
            writeLock.unlock();
        }
    }

    public Object get(String key) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " is getting " + key);

        Lock readLock = rwLock.readLock();
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            Object value = map.get(key);
            System.out.println(name + " got the value: " + value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readLock.unlock();
        }
    }
}
