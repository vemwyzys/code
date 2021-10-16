package cn.gzho.juc._1sync;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 1:41 PM
 */
public class ThreadTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        },"a").start();

        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        },"b").start();
    }
}

class Ticket {

    private int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + "当前多少票" + number);
        }else {
            System.out.println(Thread.currentThread().getName() + "无票");
        }
    }
}
