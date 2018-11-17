package com.jankin.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello word
 *
 * @author lyy
 * @date  2018/3/28 12:58
 */

@Controller
public class ManageController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }


}
