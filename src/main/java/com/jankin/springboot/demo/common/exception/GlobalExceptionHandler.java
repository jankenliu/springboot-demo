package com.jankin.springboot.demo.common.exception;

import com.jankin.springboot.demo.common.result.Result;
import com.jankin.springboot.demo.common.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * @author lyy
 * @date 2018-11-12
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 参数异常
	 * @param request 请求
	 * @param response 响应
	 * @param exception 异常
	 */
	@ResponseBody
	@ExceptionHandler({MethodArgumentNotValidException.class,ValidParamException.class, BindException.class})
	public Object paramExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String reqMethodPath=request.getMethod() +request.getServletPath();
		if (exception instanceof ValidParamException){
			ValidParamException e=(ValidParamException)exception;
			log.error("[参数异常]：{} >>> {}", reqMethodPath,e.getMessage());
			return e.getResult();
		}else if (exception instanceof MethodArgumentNotValidException){
			MethodArgumentNotValidException e= (MethodArgumentNotValidException) exception;
			BindingResult bindingResult=e.getBindingResult();
			try {
				ValidationUtil.handleBindingResult(bindingResult);
			} catch (ValidParamException ex) {
				log.error("[参数异常]：{} >>> {}", reqMethodPath,ex.getMessage());
				return ex.getResult();
			} catch (Exception ex2){
				log.error("[系统异常1]：",ex2);
				return Result.fail(exception.getMessage());
			}
		}else if (exception instanceof BindException){
			BindException e= (BindException) exception;
			BindingResult bindingResult=e.getBindingResult();
			try {
				ValidationUtil.handleBindingResult(bindingResult);
			} catch (ValidParamException ex) {
				log.error("[参数异常]：{} >>> {}", reqMethodPath,ex.getMessage());
				return ex.getResult();
			} catch (Exception ex2){
				log.error("[系统异常1]：",ex2);
				return Result.fail(exception.getMessage());
			}
		}
		log.error("[系统异常2]：", exception);
		return Result.fail(exception.getMessage());
	}

	/**
	 * 参数异常
	 * @param request 请求
	 * @param response 响应
	 * @param exception 异常
	 */
	@ResponseBody
	@ExceptionHandler
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		log.error("[系统异常]：", exception);
		if (exception instanceof ValidException){
			ValidException e=(ValidException)exception;
			return e.getResult();
		}
		return Result.fail(exception.getMessage());
	}
}

