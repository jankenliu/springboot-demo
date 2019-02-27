package com.jankin.springboot.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jankin.springboot.demo.mapper.UserMapper;
import com.jankin.springboot.demo.model.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void findAllUser() {
        PageHelper.startPage(2,3);
        List<User> users = userMapper.selectAll();
        System.out.println(JSON.toJSON(users));
        System.out.println("==========================");
        System.out.println(users);
        System.out.println("==========================");
        System.out.println(JSON.toJSON(new PageInfo<>(users)));
    }
}