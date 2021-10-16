package cn.gzho.juc._12Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号系统  颁发许可证
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 7:19 PM
 */
public class Test {

    public static void main(String[] args) {
        //六辆汽车停入三个停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //抢占
                    semaphore.acquire();

                    String name = Thread.currentThread().getName();
                    System.out.println(name + ": got one");

                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(name + ": had left");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }


        Semaphore sits = new Semaphore(4);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    sits.acquire();
                    System.out.println(name+" get a sit");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(name+" leave");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sits.release();
                }
            }).start();
        }
    }
}
