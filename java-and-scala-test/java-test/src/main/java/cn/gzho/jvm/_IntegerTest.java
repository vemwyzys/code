package cn.gzho.jvm;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 8:51 AM
 */
public class _IntegerTest {

    public static void main(String[] args) {
        Integer x1 = 127;
        Integer x2 = 127;

        System.out.println(x1 == x2);//true 享元模式

        Integer i1 = 129;
        Integer i2 = 129;

        System.out.println(i1 == i2);//false 是不同对象
    }
}
