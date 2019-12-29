package com.jankin.springboot.demo.controller;

import com.jankin.springboot.demo.common.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * UserController
 *  @author lyy
 *  @date 2019-3-2
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {


    @ResponseBody
    @RequestMapping("/hello")
    public String login(String username,String password) throws IOException {
        return "hello123224";
    }
}