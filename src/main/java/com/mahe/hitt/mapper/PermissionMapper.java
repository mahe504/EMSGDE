package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Meun;
import com.mahe.hitt.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/14--15:21
 * @Description 权限的持久接口
 **/
public interface PermissionMapper {
	/*
	 * 根据你的角色查找出对应的权限
	 */
	List<Permission> selectPermissionByRolecode(@Param("rcode") String rcode);

	/*
	 * 根据你的页面信息和你的角色查找对应的权限按钮
	 */
	List<Meun> selectPermissionBtn(@Param("type") String type , @Param("parentid") int parentid);

	Meun selectPermissionById(@Param("title") String ii);
}
