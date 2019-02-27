package com.jankin.springboot.demo.test;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * 性格注解
 * */
@Target(FIELD)
@Documented
@Retention(RUNTIME)
public @interface Character {
	/**
	 * 性格枚举
	 * */
	public enum Chart{HAPPY,SAD,CRAZY};
	
	/**
	 * 性格属性
	 * */
	Chart personChart() default Chart.HAPPY;
}