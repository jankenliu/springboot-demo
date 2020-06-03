package com.jankin.springboot.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.common.util.ApiProxy;
import com.jankin.springboot.demo.controller.feign.TestFeign;
import com.jankin.springboot.demo.model.vo.UserVO;
import com.jankin.springboot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * UserController
 *  @author lyy
 *  @date 2019-3-2
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private UserService userService;

    @GetMapping("/get_hello")
    public Result getUser(@Valid UserVO userVO, BindingResult bindingResult) {
        return Result.success("get");
    }

    @PostMapping("/post_hello")
    public Result postUser(@Valid @RequestBody UserVO userVO) {
        return Result.success(userVO);
    }

    @PostMapping("/post_aaa_by_feign")
    String postAAAByFei(@Valid @RequestBody UserVO user){
        String s = testFeign.postAAA(user);
        UserVO u=new UserVO(3L,"33aaa");
        testFeign.postAAA(u);
        return "feign:"+s+"  feign2:"+u;
    }

    @GetMapping("/get_aaa_by_feign")
    String getAAAByFei2(@Valid UserVO user){
        Map map = new ObjectMapper().convertValue(user, Map.class);
        testFeign.getAAA(map);

        return "feign:"+testFeign.getAAA(map)+"feign";
    }

    @GetMapping("/proxy3")
    Object proxy3(@Valid UserVO user){
        TestFeign userService = ApiProxy.getInstance(this.testFeign);
        UserVO vo=new UserVO();
        vo.setId(1L);
        vo.setName("param1");

        UserVO vo2=new UserVO();
        vo2.setId(2L);
        vo2.setName("param2");
        Map map = new ObjectMapper().convertValue(user, Map.class);
        return userService.getAAA(map);
    }



    @GetMapping("/proxy5")
    Object proxy5(){
        UserService userService = ApiProxy.getInstance(this.userService);
        return "proxy:"+userService.testStr();
    }




}