package com.jankin.springboot.demo.service;

import com.jankin.springboot.demo.model.vo.UserVO;

import java.util.Map;

/**
* UserService接口
*  @author lyy
*  @date 2019-3-2
*/
public interface UserService  {

    Map<String,Object> testProxy(UserVO param1, UserVO param2);


    String testStr();

}