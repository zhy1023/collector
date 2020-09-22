package com.oray.arouter_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZY on 2019/12/16
 * DESC: class Parameter
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface Parameter {

    // 不填写name的注解值表示该属性名就是key，填写了就用注解值作为key
    // 从getIntent()方法中获取传递参数值
    String name() default "";
}
