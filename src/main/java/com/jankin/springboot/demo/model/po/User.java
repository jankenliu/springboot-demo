package com.jankin.springboot.demo.model.po;

import javax.persistence.*;
import lombok.Data;

/**
 * 用户表
 * 数据库表：user
 *
 * PO（bean,entity等命名）:
 *
 * 数据库表中的记录在java对象中的显示状态,最形象的理解就是一个PO就是数据库中的一条记录。
 * 好处是可以把一条记录作为一个对象处理，可以方便的转为其它对象。
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