package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * 预约单处理逻辑
 * @author lujiahao
 * @date 2019-05-22 17:47
 */
@Service
@OrderTypeAnnotation(orderType = OrderTypeEnum.BOOKING)
public class BookingOrderStrategy extends AbstractStrategy {

    @Override
    public void process(OrderDTO orderDTO) {
        System.out.println("取消预约订单");
    }
}