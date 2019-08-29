package com.mahe.hitt.login.service;

import com.mahe.hitt.entity.User;
import org.apache.shiro.authc.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 马鹤
 * @Date 2019/7/5--
 * @Description 登录的业务层接口
 **/
public interface LoginService {
	/**
	 * 用户登录业务接口方法
	 *
	 * @param User 用户对象 ， 和记住我的标志
	 */
	String selectUserByUsernameAndPassword(User user , HttpServletRequest request ) throws AuthenticationException;

}
