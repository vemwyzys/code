package cn.gzho.juc.singleton;

/**
 * 饿汉式可能会浪费资源
 *
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 12:15 PM
 */
public class Hungry {

    //创建即占用
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    public Hungry() {
    }

    private final static Hungry hungry = new Hungry();

    public static Hungry getHungry() {
        return hungry;
    }
}
