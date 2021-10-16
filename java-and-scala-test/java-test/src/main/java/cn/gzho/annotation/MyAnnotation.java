package cn.gzho.annotation;

import java.lang.annotation.*;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-10 6:16 PM
 */
//runtime>class>source
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Inherited//子类可以继承父类的注解
public @interface MyAnnotation {

    //注解格式：类型 名称（）
    String value();

    String name() default "";

    int age() default -1;

    double wight();

    String[] family();


}

@MyAnnotation(value = "ddd", family = {"a", "b"}, wight = 10.1)
class Test {

}
