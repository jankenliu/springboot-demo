package com.jankin.springboot.demo.common.base;

import com.jankin.springboot.demo.common.exception.ValidException;
import com.jankin.springboot.demo.common.exception.ValidParamException;
import com.jankin.springboot.demo.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * @author lyy
 * @date 2018-11-12
 */
@Slf4j
public abstract class BaseController {

	/**
	 * 统一异常处理
	 * @param request 请求
	 * @param response 响应
	 * @param exception 异常
	 */
	@ExceptionHandler
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		log.error("统一异常处理：", exception);
		if (exception instanceof ValidParamException){
			ValidParamException e=(ValidParamException)exception;
			return e.getResult();
		}else if (exception instanceof ValidParamException){
			ValidException e= (ValidException) exception;
			return e.getResult();
		}
		return Result.fail(exception.getMessage());
	}
}

