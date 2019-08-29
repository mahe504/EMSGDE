package com.mahe.hitt.department.service;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/21--14:22
 * @Description 部门业务的接口
 **/
public interface DepartmentService {

	/*
	 * 查询部门的接口
	 */
	List<Department> selectDepartmentList(int page, int limit, String dname, String createuser);

	/*
	 * 查询部门的数量
	 */
	int selectDepartmentListCount(String dname, String createuser);

	/*
	 * 删除部门
	 */
	Boolean deleteDepartmentById(String id);

	/*
	 * 根据id查找部门
	 */
	Department selectDepartmentById(int id);

	/*
	 * 根据id修改部门信息
	 */
	Boolean updateDepartmentById(int id, String dname, int maxnumber, HttpSession session);

	/*
	 * 查询部门所有的员工
	 */
	List<User> selectDepartmentAllUserByDid(int did, String username, int page, int limit);

	/*
	 * 查询部门所有员工的数量
	 */
	int selectDepartmentAllUserCount(int did, String username);

	/*
	 * 按did开除员工
	 */
	Boolean deleteDepartmentUserByUid(String uid);

	/*
	*   添加部门
	* */
    Boolean addDepartment(String dname, int maxnumber, HttpSession session);

    /*
     *   查看部门是否添加
     * */
    Boolean selectDepartmentByDname(String dname);

    /*
    *   查询某个部门的人数
    * */
    Integer selectDepartmentUserCount(int did);
}
