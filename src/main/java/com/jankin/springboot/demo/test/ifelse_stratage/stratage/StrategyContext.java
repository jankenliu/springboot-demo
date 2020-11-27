package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.common.util.SpringContextUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.Map;

public class StrategyContext {
    private Map<OrderTypeEnum, Class<? extends AbstractStrategy>> strategyMap;

    public StrategyContext(Map<OrderTypeEnum, Class<? extends AbstractStrategy>> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public AbstractStrategy getStrategy(OrderTypeEnum orderTypeEnum) {
        if (orderTypeEnum == null) {
            throw new IllegalArgumentException("not fond enum");
        }

        if (CollectionUtils.isEmpty(Collections.singleton(strategyMap))) {
            throw new IllegalArgumentException("strategy map is empty,please check you strategy package path");
        }

        Class<? extends AbstractStrategy> clazz = strategyMap.get(orderTypeEnum);
        if (clazz == null) {
            throw new IllegalArgumentException("not fond strategy for type:" + orderTypeEnum.getCode());
        }

        return SpringContextUtil.getBean(clazz);
    }
}