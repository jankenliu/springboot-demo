package com.jankin.springboot.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jankin
 * @date 2019/3/1
 */
@FeignClient(name = "test",url = "http://localhost:8080")
public interface TestClient {

    @GetMapping("/test")
    String getUser();
}
