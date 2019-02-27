package com.jankin.springboot.demo.mapper;

import com.jankin.springboot.demo.common.base.BaseMapper;
import com.jankin.springboot.demo.model.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("INSERT INTO user (name,gender,age) values (#{name},#{gender},#{age})")
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    int insertTest(User user);
}