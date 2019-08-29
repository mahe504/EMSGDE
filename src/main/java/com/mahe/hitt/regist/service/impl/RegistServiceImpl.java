package com.mahe.hitt.regist.service.impl;

import com.mahe.hitt.entity.User;
import com.mahe.hitt.mapper.RegistMapper;
import com.mahe.hitt.regist.service.RegistService;
import com.mahe.hitt.utils.MD5Utils;
import com.mahe.hitt.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/7--14:31
 * @Description 注册用户实现类
 **/
@Service
public class RegistServiceImpl implements RegistService {

	@Autowired
	private RegistMapper registMapper;
	/**
	 * 验证用户民是否存在的业务方法
	 **/
	@Override
	public Boolean selectUserByUsername(String username) {
		if (registMapper.selectUserByUsername(username) != null) {
			return false;
		}
		return true;
	}

	/**
	*   添加用户业务代码
	*   @User 页面传过来的对象
	* */
	@Override
	public Boolean insertUser(User user) {
		user.setStatus("1");
		user.setUid(UUIDUtil.getUUID());
		user.setCreatetime(new Date());
		user.setPassword( MD5Utils.getMD5(user.getPassword() , user.getUsername() , 1024));
		return registMapper.insertUser(user);
	}
}
