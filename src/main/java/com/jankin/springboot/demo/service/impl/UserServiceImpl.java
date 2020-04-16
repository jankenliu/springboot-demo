package com.jankin.springboot.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.jankin.springboot.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* UserService实现
*  @author lyy
*  @date 2020-4-17
*/
@Slf4j
@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;

}