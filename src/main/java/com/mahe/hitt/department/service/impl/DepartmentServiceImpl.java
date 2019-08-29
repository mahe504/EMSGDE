package com.mahe.hitt.department.service.impl;

import com.mahe.hitt.department.service.DepartmentService;
import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/21--14:23
 * @Description 部门业务的实现类
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	/*
	 * 查询所有部门
	 */
	@Override
	public List<Department> selectDepartmentList(int page, int limit, String dname, String createuser) {
		PageInfo info = new PageInfo(limit, page);
		List<Department> departments = departmentMapper.selectDepartmentList(info.getStart(), info.getEnd(), dname,
				createuser);
		for (Department department : departments) {
			department.setUsercount(departmentMapper.selectDepartmentUserCount(department.getId()));
		} ;
		return departments;
	}

	/*
	 * 查询所有部门的数量
	 */
	@Override
	public int selectDepartmentListCount(String dname, String createuser) {
		return departmentMapper.selectDepartmentListCount(dname, createuser);
	}

	/*
	 * 删除某个部门的全部信息包括员工
	 */
	@Override
	public Boolean deleteDepartmentById(String id) {
		Boolean flag = false;
		String[] newIds = id.split(",");
		for (String newId : newIds) {
			flag = departmentMapper.deleteDePartmentById(Integer.parseInt(newId));
			departmentMapper.deleteDePartmentUsersByDid(Integer.parseInt(newId));
		}
		return flag;
	}

	/*
	 * 根据id查找部门信息
	 */
	@Override
	public Department selectDepartmentById(int id) {
		return departmentMapper.selectDepartmentById(id);
	}

	/*
	 * 根据id修改部门信息
	 */
	@Override
	public Boolean updateDepartmentById(int id, String dname, int maxnumber, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return departmentMapper.updateDepartmentById(id, dname, maxnumber, user.getUsername());
	}

	/*
	 * 查询某个部门的所有员工
	 */
	@Override
	public List<User> selectDepartmentAllUserByDid(int did, String username, int page, int limit) {
		PageInfo info = new PageInfo(limit, page);
		return departmentMapper.selectDepartmentAllUserByDid(info.getStart(), info.getEnd(), did, username);
	}

	/*
	 * 查询某个部门多有员工的数量
	 */
	@Override
	public int selectDepartmentAllUserCount(int did, String username) {
		return departmentMapper.selectDepartmentAllUserCount(did, username);
	}

	/*
	 * 按did开除员工
	 */
	@Override
	public Boolean deleteDepartmentUserByUid(String uid) {
		String[] newUid = uid.split(",");
		Boolean flag = false;
		for (String nUid : newUid) {
			flag = departmentMapper.updateDepartmentUserByUid(nUid);
		}
		return flag;
	}

	/*
	 * 添加部门
	 */
	@Override
	public Boolean addDepartment(String dname, int maxnumber, HttpSession session) {
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		return departmentMapper.addDepartment(dname, maxnumber, username);
	}

	/*
	 * 查看部门是否添加
	 */
	@Override
	public Boolean selectDepartmentByDname(String dname) {
		if (departmentMapper.selectDepartmentByDname(dname) != null) {
            return true;
        }
        return false;
	}

    @Override
    public Integer selectDepartmentUserCount(int did) {
        return departmentMapper.selectDepartmentUserCount(did);
    }
}
