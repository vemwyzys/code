package cn.gzho.juc._4LockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 7:36 PM
 */
public class LockCondition {

    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    if (finalI % 3 == 0) {
                        data.toA();
                    } else if (finalI % 3 == 1) {
                        data.toB();
                    } else {
                        data.toC();
                    }
                }
            }).start();
        }
    }

}

class Data {

    private int num = 1;
    private char c = 'A';
    private final ReentrantLock lock = new ReentrantLock();
    //监视器
    final Condition condition1 = lock.newCondition();
    final Condition condition2 = lock.newCondition();
    final Condition condition3 = lock.newCondition();

    public void toA() {
        lock.lock();
        try {
            while (c == 'A') {
                condition1.await();
            }
            c = 'A';
            System.out.println(Thread.currentThread().getName() + "  " + c);
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void toB() {
        lock.lock();
        try {
            while (c == 'B') {
                condition2.await();
            }
            c = 'B';
            System.out.println(Thread.currentThread().getName() + "  " + c);
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void toC() {
        lock.lock();
        try {
            while (c == 'C') {
                condition3.await();
            }
            c = 'C';
            System.out.println(Thread.currentThread().getName() + "  " + c);
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
