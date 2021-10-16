package cn.gzho.jvm;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xlog:gc*
 *
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 9:59 AM
 */
public class MemoryTest {

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory: " + maxMemory + "字节，   " + maxMemory / (double) 1024 / 1024 + " MB");

        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory: " + totalMemory + "字节，   " + totalMemory / (double) 1024 / 1024 + " MB");

        ArrayList<String> list = new ArrayList<>();

        String str = "wss";
        while (true) {
            str += new Random().nextInt(Integer.MAX_VALUE)+new Random().nextInt(Integer.MAX_VALUE);
            list.add(str);
        }
    }
}
