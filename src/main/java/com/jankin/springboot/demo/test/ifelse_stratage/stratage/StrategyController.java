package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyy
 * @date 2020/5/2 3:26 上午
 */
@RestController
public class StrategyController {

    @Autowired
    private CancelOrderStrategyService cancelOrderStrategyService;

    @GetMapping("order/strategy")
    Result orderStrategy(int serviceType){
        OrderDTO orderDTO=new OrderDTO(serviceType);
        cancelOrderStrategyService.process(orderDTO);
        return Result.success();
    }

}
