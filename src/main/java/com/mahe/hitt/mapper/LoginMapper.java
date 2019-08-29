package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Loginlog;
import com.mahe.hitt.entity.User;

/**
 * @Author 马鹤
 * @Date 2019/7/5--15:03
 * @Description 持久层
 **/
public interface LoginMapper {
	/**
	 * 用户登录通过用户名验证的持久层
	 * 
	 * @param username
	 **/
	User selectUserByUsername(String username);

	/*
	 * 获取当前登录者的账号和ip还有时间
	 */
	void insertLoginLog(Loginlog loginlog);

	/*
	* 查找username有无登录记录
	* */
	Loginlog selectLoginlogByUsername(String username);

	/*
	 * 修改最后登录的时间和ip
	 */
	void updateLoginLogByUsername(Loginlog loginlog);
}
