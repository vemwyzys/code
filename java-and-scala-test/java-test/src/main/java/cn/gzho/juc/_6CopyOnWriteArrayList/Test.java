package cn.gzho.juc._6CopyOnWriteArrayList;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-04 3:12 PM
 */
public class Test {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                list.add("aa");
                System.out.println(list);
            }).start();
        }


    }
}
