package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OrderTypeAnnotation {
    OrderTypeEnum orderType();
}