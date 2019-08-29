package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserDepartmentPovo;
import com.mahe.hitt.entity.povo.UserRolePOVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/18--
 * @Description 个人资料的持久层接口
 **/
public interface UserMapper {

	/*
	 * 查询user
	 */
	UserRolePOVO selectUser(String uid);

	/*
	 * 修改user
	 */
	Boolean updateUser(User user);

	/*
	 * 查询所有员工
	 */
	List<UserDepartmentPovo> selectUserAll(@Param("start") int start, @Param("end") int end, @Param("realname") String realname , @Param("dname") String dname);

	/*
	*   查询所有员工的数量
	* */
	int selectUserAllCount(@Param("realname") String realname , @Param("dname") String dname);

	/*
	*   删除用户
	* */
    Boolean deleteUserByUid(String parseInt);

    /*
     *   删除用户的角色
     * */
    Boolean deleteUserGroupByUid(String uid);
    /*
    *   查询部门
    * */
    List<Department> userSelectDepartment();

    /*
    *   查询员工所在的部门
    * */
    User selectUserIsDepartment(String uid);

    /*
    *   查询员工的数量
    * */
    int selectDepartmentUserCount(int did);

    /*
    *   修改员工所在的部门
    * */
    Boolean updateUserIsDepartment(@Param("uid") String uid,@Param("did") int did);

    /*
    *   查询是否已经存在这个用户民
    * */
    User selectUserByUsername(@Param("username") String username);

    /*
    *   添加员工
    * */
    Boolean insertUser(User user);

    /*
    *   修改密码
    * */
    Boolean updatePassword(@Param("uid") String uid, @Param("passwordSalt") String passwordSalt);

    /*
    *   添加职位
    * */
    Boolean insertUserAsGroup(@Param("uid") String uid);
}
