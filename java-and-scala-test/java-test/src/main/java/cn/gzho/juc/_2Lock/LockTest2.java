package cn.gzho.juc._2Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 7:21 PM
 */
public class LockTest2 {

    public static void main(String[] args) {
        Resource resource = new Resource();

        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                for (int i = 0; i < 20; i++) {
                    resource.sale();
                }
            }).start();
        }
    }
}

class Resource {

    private int num = 50;

    public void sale() {
        ReentrantLock lock = new ReentrantLock(false);
        boolean flag = lock.tryLock();
        if (flag){
            try {
                if (num>0){
                    num--;
                }
                System.out.println(Thread.currentThread().getName() + "   " + num);
            } finally {
                lock.unlock();
            }
        }
    }
}
