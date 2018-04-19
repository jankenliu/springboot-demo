package com.jankin.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello word
 *
 * @author 刘洋印
 * @date  2018/3/28 12:58
 */

@Controller
public class ManageController {

    @RequestMapping("hello")
    public String hello(){
        return "index";
    }


}
