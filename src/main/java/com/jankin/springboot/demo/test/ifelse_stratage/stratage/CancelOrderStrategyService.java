package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderStrategyService {

    @Autowired
    private StrategyContext context;

    public void process(OrderDTO orderDTO) {
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getByCode(orderDTO.getServiceType());
        AbstractStrategy strategy = context.getStrategy(orderTypeEnum);
        strategy.process(orderDTO);
    }
}