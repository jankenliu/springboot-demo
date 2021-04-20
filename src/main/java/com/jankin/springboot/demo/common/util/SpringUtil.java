package com.jankin.springboot.demo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil
 *
 * @author griffinxiao
 * @date 2020/08/14
 */
@Component
public class SpringUtil implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    public static <T> T getBean(Class<T> type) {
        return beanFactory.getBean(type);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return beanFactory.getBean(name, type);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}