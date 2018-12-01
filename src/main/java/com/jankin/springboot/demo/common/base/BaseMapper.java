package com.jankin.springboot.demo.common.base;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper 自定义接口
 * @param <T>
 * @author lyy
 * @date 2018-11-25
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}