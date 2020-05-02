package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;
import org.springframework.stereotype.Service;

@Service
@OrderTypeAnnotation(orderType = OrderTypeEnum.INSTANT)
public class InstantOrderStrategy extends AbstractStrategy {
    @Override
    public void process(OrderDTO orderDTO) {
        System.out.println("取消即时订单");
    }
}