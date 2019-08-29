package com.mahe.hitt.user.service;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserDepartmentPovo;
import com.mahe.hitt.entity.povo.UserRolePOVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/18--
 * @Description 用户的业务层接口
 **/
public interface UserService {

	/*
	 * 查询个人
	 */
	UserRolePOVO selectUser(HttpSession session);

	/*
	 * 修改个人资料
	 */
	Boolean updateUser(User user, HttpSession session);

	/*
	 * 查询全部员工
	 */
	List<UserDepartmentPovo> selectUserAll(int limit, int page, String realname, String dname);

	/*
	 * 查询全部员工的数量
	 */
	Integer selectUserAllCount(String realname, String dname);

	/*
	*   删除用户
	* */
    Boolean deleteUserByUid(String uid);

    /*
    *   查询部门
    * */
    List<Department> userSelectDepartment();

    /*
    *   查询员工所在的部门
    * */
    User selectUserIsDepartment(String uid);

    /*
    *   修改员工所在的部门
    * */
    Boolean updateUserIsDepartment(String uid, int did);

    /*
    *   查询是否已经存在这个用户名
    * */
    Boolean selectUserByUsername(String username);

    /*
    *   添加员工
    * */
    Boolean insertUser(User user);

    /*
    *   修改密码
    * */
    Boolean updatePassword(String password, HttpSession session);
}
