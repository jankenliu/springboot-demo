package com.jankin.springboot.demo.model.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 BO（service,manager,business等命名）：
 主要作用是把业务逻辑封装为一个对象。这个对象可以包括一个或多个其它的对象。
 形象描述为一个对象的形为和动作，当然也有涉及到基它对象的一些形为和动作。比如处理一个人的业务逻辑，有睡觉，吃饭，工作，上班等等形为还有可能和别人发关系的形为。
 这样处理业务逻辑时，我们就可以针对BO去处理。
 */
@Data
public class UserBO {

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

    private String ip;

    private String address;
}