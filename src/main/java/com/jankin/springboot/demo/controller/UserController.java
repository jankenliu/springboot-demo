package com.jankin.springboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jankin.springboot.demo.common.base.BaseController;
import com.jankin.springboot.demo.model.vo.TestVo;
import com.jankin.springboot.demo.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * UserController
 *  @author lyy
 *  @date 2018-12-2
 */
@Slf4j
@Controller
@RequestMapping("/user")
@Api(value = "User控制器", description = "User管理")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(){
        return userService.findAllUser();
    }

    @ResponseBody
    @GetMapping("/page")
    public Object findUserByPage(int pageNum,int pageSize){
        return userService.findUserByPage(pageNum,pageSize);
    }

    @ResponseBody
    @GetMapping("/page2")
    public Object findUserByPage2(int pageNum,int pageSize){
        return userService.findUserByPage2(pageNum,pageSize);
    }
    @ResponseBody
    @GetMapping("/add")
    public Object addUser(String name,int gender,int age){
        return userService.addUser(name,gender,age);
    }


}