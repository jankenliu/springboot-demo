package com.jankin.springboot.demo.common.base;

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
		return "error";
	}
}

