package com.jankin.springboot.demo.test.ifelse_stratage.ifelse;

import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderService {

    public void process(OrderDTO orderDTO) {
        int serviceType = orderDTO.getServiceType();
        if (1 == serviceType) {
            System.out.println("取消即时订单");
        } else if (2 == serviceType) {
            System.out.println("取消预约订单");
        } else if (3 == serviceType) {
            System.out.println("取消拼车订单");
        }
    }
}