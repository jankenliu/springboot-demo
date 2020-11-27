package com.jankin.springboot.demo.test.ifelse_stratage.stratage;

import com.jankin.springboot.demo.test.ifelse_stratage.OrderDTO;

public abstract class AbstractStrategy {
    abstract public void process(OrderDTO orderDTO);
}