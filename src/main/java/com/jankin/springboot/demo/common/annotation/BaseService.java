package com.jankin.springboot.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * @author lyy
 * @date 2018-11-12
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
