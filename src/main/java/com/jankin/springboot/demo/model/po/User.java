package com.jankin.springboot.demo.model.po;

import javax.persistence.*;
import lombok.Data;

/**
 * 用户表
 * 数据库表：user
 */
@Data
@Table(name = "user")
public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;//用户id

    /**
     * 名称
     */
    private String name;//名称

    /**
     * 性别0未知 1男 2女
     */
    private Byte gender;//性别0未知 1男 2女

    /**
     * 年龄
     */
    private Byte age;//年龄
}