package com.jankin.springboot.demo.common.util;

import java.lang.reflect.Proxy;

public class ApiProxy {
    public static <T> T getInstance(T o) {
        Object o1 = Proxy.newProxyInstance(
                o.getClass().getClassLoader(),
                o.getClass().getInterfaces(),
                new ApiInvocationHandler(o));
        return (T) o1;
    }

}