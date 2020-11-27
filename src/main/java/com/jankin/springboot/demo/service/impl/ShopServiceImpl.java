package com.jankin.springboot.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.jankin.springboot.demo.service.ShopService;
import com.jankin.springboot.demo.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* ShopService实现
*  @author lyy
*  @date 2020-5-6
*/
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

}