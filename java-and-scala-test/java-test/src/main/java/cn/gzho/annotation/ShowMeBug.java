package cn.gzho.annotation;/*
 可以引⼊的库和版本相关请参考 "环境说明"
 请通过输出方法打印代码运行结果，"运行"后在控制台查看输出信息
 请勿修改`ShowMeBug` ⼊⼝类和 `public static void main(String[] args)` ⼊⼝⽅法，以防执⾏失败

 Please refer to the "Environmental Notes" for the libraries and versions that can be introduced.
 Please print the results of your code using the output method and check the output on the console after "run".
 Don't modify the `ShowMeBug` input class and the public static void Main() entry function to prevent execution failure.
*/

import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ShowMeBug {

    public static class ApplicationContext {

        public void registerBean(Object bean) {
            //TODO
        }

        /**
         * refresh bean field members
         */
        public void refresh() {
            //TODO
        }

        public <T> T getBean(Class<T> clazz) {
            //TODO
            return null;
        }
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Inject {

    }

    @Data
    public static class TestA {

        @Inject
        private TestB testB;

        @Inject
        private TestC c;

    }

    @Data
    public static class TestC {

    }

    @Data
    public static class TestB {

        @Inject
        private TestA testA;
    }


    public static void main(String[] args) {
        // 请在此处书写代码
        // please write your code here
        final var ctx = new ApplicationContext();
        ctx.registerBean(new TestA());
        ctx.registerBean(new TestB());
        ctx.registerBean(new TestC());
        ctx.refresh();
        final var testA = ctx.getBean(TestA.class);
        assert testA.getTestB() != null;
        assert testA.getC() != null;
        assert testA.getTestB().getTestA() != null;
    }
}