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
     * 用户id(主键)
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;
}