package com.jankin.springboot.demo.controller.feign;

import com.jankin.springboot.demo.model.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author lyy
 * @date 2020/5/21 11:46 上午
 */
@FeignClient(name = "aa",url = "localhost:8081/")
public interface TestFeign {

    @PostMapping("post_user1")
    String postAAA(@RequestBody UserVO user);

    @GetMapping("get_user1")
    String getAAA(@RequestParam Map<String,Object> req);

}
