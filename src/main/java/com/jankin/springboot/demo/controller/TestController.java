package com.jankin.springboot.demo.controller;

import com.jankin.springboot.demo.controller.feign.TestFeign;
import com.jankin.springboot.demo.model.vo.UserVO;
import com.jankin.springboot.demo.service.abs.Pig;
import com.jankin.springboot.demo.service.impl.ShopServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author lyy
 * @date 2020/5/21 12:06 下午
 */
@RestController
public class TestController implements TestFeign {

    @Autowired
    private Pig pig;

    @Value("${sams.config.newMemberRpcDomain: 'https://aloha-qa.walmartmobile.cn/'}")
    private String newMemberRpcDomain;

    @Autowired
   private ShopServiceImpl shopService;

    @SneakyThrows
    @Override
    public String postAAA(@Valid @RequestBody UserVO user) {
       Thread.sleep(10000);
        return "3333333";
    }

    @Override
    public String getAAA(Map<String,Object> req) {

        return "444444";
    }

    @GetMapping("/const/pig")
    public Object getConst(){
        String name = pig.name();
        System.out.println("name = " + name);
        return name;
    }

    @GetMapping("/value_test")
    public Object valueTest(){
        return newMemberRpcDomain;
    }



    @GetMapping("/test33")
    public Object test33(){
        String name = shopService.aniCli1.pig.name();
        System.out.println("name = " + name);
        return name;
    }



}
