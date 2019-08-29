package com.mahe.hitt.login.controller;

import com.mahe.hitt.entity.User;
import com.mahe.hitt.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 马鹤
 * @Date 2019/7/5--16:42
 * @Description
 **/
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private LoginService loginServiceImpl;

	@RequestMapping(value = "/login.action", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String login(User user, Integer remember, HttpSession session , HttpServletRequest request) {
		user.setRemember(remember);
		String flag = loginServiceImpl.selectUserByUsernameAndPassword(user , request);
		return flag;
	}
	@RequestMapping(value = "/gotologin.action")
	public String gotologin(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:index.jsp";
		}
		return "redirect:main/mian.action";
	}

	@RequestMapping(value = "/logout.action", produces = "text/plain;charset=utf-8")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/index.jsp";
	}
}
