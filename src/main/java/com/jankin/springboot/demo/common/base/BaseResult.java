package com.jankin.springboot.demo.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回结果类
 * @author lyy
 * @date 2018-11-12
 */
@Setter
@Getter
@AllArgsConstructor
public class BaseResult {

    /**
     * 状态码：1成功，其他为失败
     */
    protected int code;

    /**
     * 成功为success，其他为失败原因
     */
    protected String message;

    /**
     * 数据结果集
     */
    protected Object data;

}
