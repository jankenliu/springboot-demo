package com.jankin.springboot.demo.test;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 证件信息注解
 * */
@Target(FIELD)
@Documented
@Retention(RUNTIME)
public @interface Card {
	public int id() default -1;
	String name() default "";
    String num() default "";
}