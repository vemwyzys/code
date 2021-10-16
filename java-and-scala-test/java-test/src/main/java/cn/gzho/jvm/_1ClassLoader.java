package cn.gzho.jvm;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-07 9:48 PM
 */
public class _1ClassLoader {

    public static void main(String[] args) {

        _1ClassLoader test = new _1ClassLoader();

        ClassLoader classLoader = test.getClass().getClassLoader();

        System.out.println(classLoader);//app cl
        System.out.println(classLoader.getParent());//ext cl
        System.out.println(classLoader.getParent().getParent());//root cl

        System.out.println("================String==================");

        String str = new String(new char[]{'a', 'b'});

        ClassLoader test2 = str.getClass().getClassLoader();

        System.out.println(test2);
        System.out.println(test2.getParent());
        System.out.println(test2.getParent().getParent());

    }
}
