package cn.gzho.juc._7FairLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 3:27 PM
 */
public class Test {

    public static void main(String[] args) {
        ReentrantLock reentrantLockOfUnfair = new ReentrantLock(false);
        //非公平锁效率高

        ReentrantLock reentrantLockOfFair = new ReentrantLock(true);
        //公平锁
        //线程不会饿死
    }
}
