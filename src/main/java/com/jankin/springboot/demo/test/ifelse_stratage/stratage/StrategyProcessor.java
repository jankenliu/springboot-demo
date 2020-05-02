package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.google.common.collect.Maps;
import com.jankin.springboot.demo.common.util.ClassScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrategyProcessor implements BeanFactoryPostProcessor {

    private static final String STRATEGY_PACKAGE = "com.jankin.springboot.demo.test.ifelse_stratage.stratage";


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<OrderTypeEnum,Class<? extends AbstractStrategy>> handlerMap = Maps.newHashMapWithExpectedSize(3);
        ClassScanner.scan(new String[]{STRATEGY_PACKAGE}, OrderTypeAnnotation.class).forEach(clazz -> {
            if (AbstractStrategy.class.isAssignableFrom(clazz)) {
                    OrderTypeEnum type = clazz.getAnnotation(OrderTypeAnnotation.class).orderType();
                    handlerMap.put(type, (Class<? extends AbstractStrategy>) clazz);
            }
        });
        StrategyContext context = new StrategyContext(handlerMap);
        configurableListableBeanFactory.registerSingleton(StrategyContext.class.getName(), context);
    }

}