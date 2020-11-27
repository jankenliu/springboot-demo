package com.jankin.springboot.demo.common.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lyy
 * @date 2020/5/21 6:17 下午
 */
@Slf4j
public class ApiInvocationHandler<T> implements InvocationHandler {
    private T object;

    public ApiInvocationHandler(T object) {
        this.object = object;
    }

    @SneakyThrows
    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        Class<?>[] interfaces = object.getClass().getInterfaces();

        log.info("=== 代理请求开始 ==== class:{} , method:{} ,params:{}", interfaces, method.getName(), args);
        Object invoke = null;
        try {
            invoke = method.invoke(this.object, args);
        } catch (Exception e) {
            log.error("=== 代理失败请检验代码 ==== class:{} , method:{} ,params:{}", interfaces, method.getName(), args);
            throw new RuntimeException("代理失败：",e);
        }
        log.info("=== 代理请求结果 ==== class:{} , method:{} ,invoke:{}", interfaces, method.getName(), invoke);
        return invoke;
    }
}
