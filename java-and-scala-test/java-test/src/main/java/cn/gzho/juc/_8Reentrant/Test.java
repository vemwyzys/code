package cn.gzho.juc._8Reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 6:09 PM
 */
public class Test {

    public static void main(String[] args) {
        new Test().recursion();
    }

    Lock lock = new ReentrantLock();
    private int num = 10;

    public void recursion() {
        lock.lock();
        try {
            if (num > 0) {
                num--;
                System.out.println(num);
                //可重入
                recursion();
            }else {
                return;
            }
        } finally {
            lock.unlock();
        }
    }
}
