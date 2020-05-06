package com.jankin.springboot.demo.mapper;

import com.jankin.springboot.demo.common.base.BaseMapper;
import com.jankin.springboot.demo.model.po.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
}