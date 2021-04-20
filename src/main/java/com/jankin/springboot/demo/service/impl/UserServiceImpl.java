package com.jankin.springboot.demo.service.impl;

import com.google.common.collect.Maps;
import com.jankin.springboot.demo.mapper.UserMapper;
import com.jankin.springboot.demo.model.vo.UserVO;
import com.jankin.springboot.demo.service.UserService;
import com.jankin.springboot.demo.test.anno.CustomCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* UserService实现
*  @author lyy
*  @date 2020-5-6
*/
@Slf4j
@Service
@CustomCache(key = "user111")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> testProxy(UserVO param1, UserVO param2) {
        log.info("================ 进入主业务");
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("aaa","12341324");
        map.put("param1",param1);
        map.put("param2",param2);
        return map;
    }

    @Override
    public String testStr() {
        return "ssssssssss";
    }
}