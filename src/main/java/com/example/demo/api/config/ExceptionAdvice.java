package com.example.demo.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cn.hutool.json.JSONObject;

@RestControllerAdvice
public class ExceptionAdvice {

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Exception e) {
		JSONObject json = new JSONObject();
		if (e instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
			json.set("error", exception.getBindingResult().getFieldError().getDefaultMessage());
		}
		//HTTP请求类型不正确
		else if(e instanceof HttpRequestMethodNotSupportedException) {
			json.set("error", "Web方法不支持当前的HTTP请求类型");	
		}else if (e instanceof HttpMessageNotReadableException) {
			json.set("error", "缺少提交的数据");
		}
		else {
			json.set("error", "执行异常");
			e.printStackTrace();
		}
		return json.toString();
	};
}
