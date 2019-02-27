package com.jankin.springboot.demo.test;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
/**
 * 地址注解
 * */
@Target(FIELD)//定义使用在哪里（当前为可用于成员变量、枚举常量）
@Retention(RUNTIME)//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Documented
public @interface Address {
	String value() default "";
}