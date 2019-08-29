package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/21--
 * @Description 部门的持久层接口
 **/
public interface DepartmentMapper {

	/*
	 * 查询全部部门的信息
	 */
	List<Department> selectDepartmentList(@Param("start") Integer start, @Param("end") Integer end,
			@Param("dname") String dname, @Param("createuser") String createuser);

	/*
	 * 查询部门的数量
	 */
	int selectDepartmentListCount(@Param("dname") String dname, @Param("createuser") String createuser);

	/*
	 * 查询某个部门的员工的数量
	 */
	int selectDepartmentUserCount(@Param("id") int id);

	/*
	 * 删除部门的全部信息v
	 */
	Boolean deleteDePartmentById(@Param("id") int newId);

	/*
	 * 删除关于某个部门的员工
	 */
	Boolean deleteDePartmentUsersByDid(@Param("did") int newId);

	/*
	 * 根据id查找部门
	 */
	Department selectDepartmentById(@Param("id") int id);

	/*
	 * 根据id修改部门信息
	 */
	Boolean updateDepartmentById(@Param("id") int id, @Param("dname") String dname, @Param("maxnumber") int maxnumber,
			@Param("updateuser") String updateuser);

	/*
	 * 查询某个部门的员工
	 */
	List<User> selectDepartmentAllUserByDid(@Param("start") int start, @Param("end") int end, @Param("did") int did,
			@Param("username") String username);

	/*
	 * 查询某个部门员工的数量
	 */
	Integer selectDepartmentAllUserCount(@Param("did") int did, @Param("username") String username);

	/*
	 * 按Did开除员工
	 */
	Boolean updateDepartmentUserByUid(@Param("uid") String uid);

	/*
	*   添加部门
	* */
	Boolean addDepartment(@Param("dname") String dname, @Param("maxnumber") int maxnumber,
			@Param("username") String username);

	/*
	*   查看部门是否添加
	* */
    Department selectDepartmentByDname(@Param("dname") String dname);
}
