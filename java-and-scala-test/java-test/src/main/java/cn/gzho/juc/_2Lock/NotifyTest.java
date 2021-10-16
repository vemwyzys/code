package cn.gzho.juc._2Lock;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 7:36 PM
 */
public class NotifyTest {

    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    if (finalI % 2 == 0) {
                        System.out.println(Thread.currentThread().getName());
                        data.increment();
                    } else {
                        data.decrement();
                    }
                }
            }).start();
        }
    }

}

class Data {

    private int num = 1;

    public synchronized void increment() {
        while (num > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "  " + num);
        notifyAll();
    }

    public synchronized void decrement() {
        while (num < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "  " + num);
        notifyAll();
    }

}
