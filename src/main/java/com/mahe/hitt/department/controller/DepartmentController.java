package com.mahe.hitt.department.controller;

import com.mahe.hitt.department.service.DepartmentService;
import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @Author 马鹤
 * @Date 2019/7/21--14:22
 * @Description 部门的控制器
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentServiceImpl;

	/*
	 * 跳转到部门的页面
	 */
	@RequestMapping("/departmentList.action")
	public String departmentList() {
		return "forward:/WEB-INF/jsps/department/department.html";
	}

	/*
	 * 查询部门
	 */
	@RequestMapping("/selectDepartment.action")
	@ResponseBody
	public IResult selectDepartment(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "dname", defaultValue = "") String dname,
			@RequestParam(value = "createuser", defaultValue = "") String createuser) {
		return new PageResultBean<Collection<Department>>(
				departmentServiceImpl.selectDepartmentList(page, limit, dname, createuser),
				departmentServiceImpl.selectDepartmentListCount(dname, createuser));
	}

	/*
	 * 按id删除部门
	 */
	@RequestMapping(value = "/deleteDepartmentById.action")
	@ResponseBody
	public IResult deleteDepartment(@RequestParam("id") String id) {
		return new ResultBean<Boolean>(departmentServiceImpl.deleteDepartmentById(id));
	}

	/*
	 * 按照id查找部门
	 */
	@RequestMapping(value = "/selectDepartmentById.action")
	@ResponseBody
	public IResult selectDepartmentById(@RequestParam("id") String id) {
		return new ResultBean<Department>(departmentServiceImpl.selectDepartmentById(Integer.parseInt(id)));
	}

	/*
	 * 修改某个部门信息
	 */
	@RequestMapping(value = "/editDepartmentById.action")
	@ResponseBody
	public IResult editDepartmentById(@RequestParam("id") int id, @RequestParam("dname") String dname,
			@RequestParam("maxnumber") int maxnumber, HttpSession session) {
		return new ResultBean<Boolean>(departmentServiceImpl.updateDepartmentById(id, dname, maxnumber, session));
	}

	/*
	 * 查询部门的员工
	 */
	@RequestMapping(value = "/selectDepartmentAllUser.action")
	@ResponseBody
	public IResult selectDepartmentAllUser(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "username", defaultValue = "") String username, @RequestParam("did") int did) {
		return new PageResultBean<Collection<User>>(
				departmentServiceImpl.selectDepartmentAllUserByDid(did, username, page, limit),
				departmentServiceImpl.selectDepartmentAllUserCount(did, username));
	}

	/*
	*   开除id的员工
	* */
	@RequestMapping(value = "/deleteDepartmentUserByUid.action")
	@ResponseBody
	public IResult deleteDepartmentUser(@RequestParam("uid") String uid) {
		return new ResultBean<Boolean>(departmentServiceImpl.deleteDepartmentUserByUid(uid));
	}

	/*
	*   添加部门
	* */
	@RequestMapping(value="/addDepartment.action")
    @ResponseBody
    public IResult addDepartment(@RequestParam("dname") String dname , @RequestParam("maxnumber") int maxnumber , HttpSession session){
        return new ResultBean<Boolean>(departmentServiceImpl.addDepartment(dname , maxnumber , session));
    }

    /*
    *   查看部门是否添加
    * */
    @RequestMapping(value="selectDepartmentByDname.action")
    @ResponseBody
    public IResult selectDepartmentIsInsert(@RequestParam("dname") String dname){
        return new ResultBean<Boolean>(departmentServiceImpl.selectDepartmentByDname(dname));
    }

    /*
    *   查询某个部门的员工数量
    * */
    @RequestMapping("/selectDepartmentUserCount.action")
    @ResponseBody
    public IResult selectDepartmentUserCount(@RequestParam("did") int did){
        return new ResultBean<Integer>(departmentServiceImpl.selectDepartmentUserCount(did));
    }
}