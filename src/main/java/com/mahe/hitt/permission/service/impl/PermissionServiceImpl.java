package com.mahe.hitt.permission.service.impl;

import com.mahe.hitt.entity.Meun;
import com.mahe.hitt.entity.Role;
import com.mahe.hitt.mapper.PermissionMapper;
import com.mahe.hitt.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/15--19:05
 * @Description 权限业务的实现类
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	/*
	 * 查找出系统的角色对应的权限按钮 title : 你页面的标志
	 */
	@Override
	public List<Meun> selectPermission(String title, HttpSession session) {
		List<Role> roles = (List<Role>) session.getAttribute("roles");
		List<Meun> btn = new LinkedList<>();
		for (Role role : roles) {
			// 记录一下看到这些你就要记住了在linux下面要设置一下字符集编码格式切记啊马鹤不要在向第一次做项目一样了！！！！！！！！！！！！！！！！！！！！来自马鹤内心的真实的想法
			String rrr = role.getRcode();
			Meun parentid = permissionMapper.selectPermissionById(title);
			Integer parid = parentid.getMenuId();
			btn = permissionMapper.selectPermissionBtn(rrr, parid);
		}
		return btn;
	}
}