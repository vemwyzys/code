package cn.gzho.jvm;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-08 7:53 AM
 */
public class _StringTest {
    public static void main(String[] args) {
        String aaa = "aaa";
        aaa.intern();

        String a = "123";
        String b = "1239";

        System.out.println(a==b);
    }
}
