package com.jankin.springboot.demo.mapper;

import com.jankin.springboot.demo.common.base.BaseMapper;
import com.jankin.springboot.demo.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserIn(@Param("userIdList") List<Integer> userIdList);
}