package com.mahe.hitt.admin.service;

import com.mahe.hitt.entity.User;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/19--
 * @Description 管理员的业务接口
 **/
public interface AdminService {

	/*
	 * 查询所有管理员
	 */
	List<User> selectAdmin(int page, int limit, String username);

	/*
	 * 查询所有管理员的数目
	 */
	int selectAdminAllCount(String username);

	/*
	 * 修改管理员的状态
	 */
	Boolean updateAdminStatusByUid(int status, String uid);

	/*
	 * 按uid删除管理员
	 */
	Boolean deleteAdminByUid(String uid);

	/*
	 * 查询没有身份的用户
	 */
	List<User> selectUserNoAdmin(int page, int limit, String username);

	/*
	 * 查询没有身份的用户的数量
	 */
	Integer selectUserNoAdminCount(String username);

	/*
	*   添加管理员uid
	* */
    Boolean insertAdminByUid(String uid);
}
