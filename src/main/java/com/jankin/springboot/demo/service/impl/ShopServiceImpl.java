package com.jankin.springboot.demo.service.impl;

import com.jankin.springboot.demo.mapper.ShopMapper;
import com.jankin.springboot.demo.service.ShopService;
import com.jankin.springboot.demo.service.UserService;
import com.jankin.springboot.demo.test.t2.AniCli1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
* ShopService实现
*  @author lyy
*  @date 2020-5-6
*/
@Order(value = 2)
@Slf4j
@Service
public class ShopServiceImpl implements ShopService  , CommandLineRunner {

    @Autowired
    ShopMapper shopMapper;



    @Autowired
    private UserService userService;

    @Autowired
    public AniCli1 aniCli1;

    @Override
    public void run(String... args) throws Exception {
        String s = userService.testStr();
        System.out.println("s = " + s);
        String name = aniCli1.pig.name();
        System.out.println("name = " + name);
    }

}