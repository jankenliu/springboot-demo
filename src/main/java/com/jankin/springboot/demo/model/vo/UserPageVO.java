package com.jankin.springboot.demo.model.vo;

/**
 * VO（from也有此写法） ,返回给前端参数的实体封装
 *
 * value object值对象,主要体现在视图的对象，对于一个WEB页面
 * 将整个页面的属性封装成一个对象。然后用一个VO对象在控制层与视图层进行传输交换。
 */
public class UserPageVO {
    private String name;
    private String gender;
    private int state;//状态
    private String roleEnum;//角色枚举
}
