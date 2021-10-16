package cn.gzho.juc._3ThreadCommunication;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 2:10 PM
 */
public class ThreadCommunication {

    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "incr").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "decr").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "incr1").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "decr1").start();
    }
}

class Share {

    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        if (number != 0) {
            /**
             * 虚假唤醒问题
             * 在哪里睡！就在哪里醒！醒来不判断！！！！
             * 需要把if改成while
             */
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        if (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
    }
}
