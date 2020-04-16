package com.jankin.springboot.demo.controller;


import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController
 *  @author lyy
 *  @date 2019-3-2
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get_hello")
    public Result getUser() {
        return Result.success("get");
    }

    @PostMapping("/post_hello")
    public Result postUser(@Valid @RequestBody UserVO userVO) {
        return Result.success(userVO);
    }
}