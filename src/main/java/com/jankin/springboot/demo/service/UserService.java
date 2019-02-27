package com.jankin.springboot.demo.service;

/**
* UserService接口
*  @author lyy
*  @date 2018-12-9
*/
public interface UserService  {

    Object findAllUser();

    Object findUserByPage(int pageNum, int pageSize);

    Object findUserByPage2(int pageNum, int pageSize);

    Object addUser(String name, int gender, int age);
}