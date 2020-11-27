package com.jankin.springboot.demo.model.po;

import javax.persistence.*;
import lombok.Data;

/**
 * 
 * 数据库表：shop
 */
@Data
@Table(name = "shop")
public class Shop {
    /**
     * 主键
     */
    @Id
    private Integer id;//主键

    /**
     * 门店名称
     */
    @Column(name = "shop_name")
    private String shopName;//门店名称

    /**
     * 门店地址
     */
    @Column(name = "shop_address")
    private String shopAddress;//门店地址

    /**
     * 门店代号
     */
    @Column(name = "shop_no")
    private Integer shopNo;//门店代号
}