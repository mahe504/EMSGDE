package com.mahe.hitt.user.service.impl;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.entity.povo.UserDepartmentPovo;
import com.mahe.hitt.entity.povo.UserRolePOVO;
import com.mahe.hitt.mapper.UserMapper;
import com.mahe.hitt.user.service.UserService;
import com.mahe.hitt.utils.MD5Utils;
import com.mahe.hitt.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/18--15:10
 * @Description 用户的业务层实现类
 **/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/*
	 * 查询个人资料的业务方法
	 */
	@Override
	public UserRolePOVO selectUser(HttpSession session) {
		return userMapper.selectUser(((User) session.getAttribute("user")).getUid());
	}

	/*
	 * 修改个人资料的业务方法
	 */
	@Override
	public Boolean updateUser(User user, HttpSession session) {
		user.setUid(((User) session.getAttribute("user")).getUid());
		return userMapper.updateUser(user);
	}

	/*
	 * 查询所有员工
	 */
	@Override
	public List<UserDepartmentPovo> selectUserAll(int limit, int page, String realname, String dname) {
		PageInfo info = new PageInfo(limit, page);
		return userMapper.selectUserAll(info.getStart(), info.getEnd(), realname, dname);
	}

	/*
	 * 查询所有员工的数量
	 */
	@Override
	public Integer selectUserAllCount(String realname, String dname) {
		return userMapper.selectUserAllCount(realname, dname);
	}

	/*
	 * 删除用户
	 */
	@Override
	public Boolean deleteUserByUid(String uid) {
		String[] newUid = uid.split(",");
		Boolean flag = false;
		for (String nUid : newUid) {
			flag = userMapper.deleteUserByUid(nUid);
            userMapper.deleteUserGroupByUid(uid);
		}
		return flag;
	}

	/*
	 * 查询部门
	 */
	@Override
	public List<Department> userSelectDepartment() {
		List<Department> departments = userMapper.userSelectDepartment();
		for (Department department : departments) {
			department.setUsercount(userMapper.selectDepartmentUserCount(department.getId()));
		}
		return departments;
	}

	@Override
	public User selectUserIsDepartment(String uid) {
		return userMapper.selectUserIsDepartment(uid);
	}

	@Override
	public Boolean updateUserIsDepartment(String uid, int did) {
		return userMapper.updateUserIsDepartment(uid, did);
	}

	@Override
	public Boolean selectUserByUsername(String username) {
		System.out.println(userMapper.selectUserByUsername(username));
		if (userMapper.selectUserByUsername(username) != null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean insertUser(User user) {
		user.setStatus("1");
		user.setUid(UUIDUtil.getUUID());
		user.setCreatetime(new Date());
		user.setPassword(MD5Utils.getMD5(user.getPassword(), user.getUsername(), 1024));
        userMapper.insertUserAsGroup(user.getUid());
		return userMapper.insertUser(user);
	}

	/*
	 * 修改密码
	 */
	@Override
    public Boolean updatePassword(String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String passwordSalt= MD5Utils.getMD5(password , user.getUsername() , 1024);
        user.setPassword(passwordSalt);
        return userMapper.updatePassword(user.getUid() , passwordSalt);
    }
}
