package com.mahe.hitt.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 马鹤
 * @Date 2019/7/8--
 * @Description
 **/
public class Logininterceptor extends HandlerInterceptorAdapter {

	/*
	 * 此拦截器主要拦截没有登录就访问页面的人，如果没有登录就推到登录页面
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}
		response.sendRedirect("login/login.html");
		return false;
	}
}
