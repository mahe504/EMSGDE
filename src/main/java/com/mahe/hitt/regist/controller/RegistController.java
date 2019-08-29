package com.mahe.hitt.regist.controller;

import com.mahe.hitt.entity.User;
import com.mahe.hitt.regist.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 马鹤
 * @Date 2019/7/7--14:26
 * @Description  注册控制器
 **/
@Controller
@RequestMapping(value = "/regist")
public class RegistController {

	@Autowired
	private RegistService registServiceImpl;

	/*
	 * 判断这个用户名是否已经被注册了 没有返回true
	 */
	@RequestMapping(value = "/selectUsername.action")
	@ResponseBody
	public Boolean selectUsername(String username) {
		return registServiceImpl.selectUserByUsername(username);
	}
	/*
	 * 注册的控制层代码
	 */
	@RequestMapping(value = "/registUser.action", produces = "text/plain ; charset=utf-8")
	@ResponseBody
	public String registUser(User user) {
	    if(registServiceImpl.insertUser(user)){
	        return "注册成功";
        }
		return "注册失败";
	}
}