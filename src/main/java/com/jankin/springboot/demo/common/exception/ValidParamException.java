package com.jankin.springboot.demo.common.exception;

import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.common.result.ResultStateEnum;
import lombok.Getter;

/**
 * @Author lyy
 * @Title ValidException
 * @Description: 数据校验异常类, controller处理BindingResult异常时抛出, 配合ControllerAdvice和ExceptionHandle处理返回
 * @Date 2018/6/29 21:19
 */
@Getter
public class ValidParamException extends RuntimeException {

    /**
     * 错误结果
     */
    Result result;

    public ValidParamException(ResultStateEnum resultState) {
        super(resultState.getMessage());
        this.result=new Result(resultState);
    }

    public ValidParamException(String desc) {
        super(desc);
        this.result = Result.fail().setDesc(desc);
    }


}
