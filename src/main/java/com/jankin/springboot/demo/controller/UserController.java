package com.jankin.springboot.demo.controller;

import com.jankin.springboot.demo.common.base.BaseController;
import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.model.vo.UserVO;
import com.jankin.springboot.demo.common.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *  @author lyy
 *  @date 2019-3-2
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/get_hello")
    public Result getUser() {
        return Result.success("get");
    }

    @PostMapping("/post_hello")
    public Result postUser(@Validated @RequestBody UserVO userVO, BindingResult bindingResult) {
        ValidationUtil.handleBindingResult(bindingResult);
        return Result.success(userVO);
    }
}