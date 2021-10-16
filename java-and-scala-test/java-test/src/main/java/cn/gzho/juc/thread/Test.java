package cn.gzho.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 7:00 PM
 */
public class Test {
    public static void main(String[] args) {

        //Thread状态的枚举
        Thread.State state = Thread.State.NEW;

        //获取核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
