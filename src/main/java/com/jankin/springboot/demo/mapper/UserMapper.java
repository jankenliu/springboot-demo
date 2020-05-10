package com.jankin.springboot.demo.mapper;

import com.jankin.springboot.demo.common.base.BaseMapper;
import com.jankin.springboot.demo.model.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public Object get();
}