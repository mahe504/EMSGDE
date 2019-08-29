package com.mahe.hitt.man.controller;

import com.mahe.hitt.entity.Group;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserGroupDepartmentPovo;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.man.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @Author 马鹤
 * @Date 2019/7/27--8:42
 * @Description 角色和权限管理的控制器
 **/
@Controller
@RequestMapping("/man")
public class ManagerController {

	@Autowired
	private ManagerService managerServiceImpl;

	/*
	 * 跳转角色管理页面
	 */
	@RequestMapping("/roleList.action")
	public String roleList() {
		return "forward:/WEB-INF/jsps/role/role.html";
	}

	/*
	 * 跳转权限管理页面
	 */
	@RequestMapping("/permissionList.action")
	public String permissionList() {
		return "forward:/WEB-INF/jsps/permission/permission.html";
	}

	/*
	 * 查询角色信息
	 */
	@RequestMapping("/selectRole.action")
	@ResponseBody
	public IResult selectRole(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "gname", defaultValue = "") String gname) {
		return new PageResultBean<Collection<Group>>(managerServiceImpl.selectRole(page, limit, gname),
				managerServiceImpl.selectRoleCount(gname));
	}

	/*
	 * 删除角色
	 */
	@RequestMapping(value = "/deleteRoleById.action")
	@ResponseBody
	public IResult deleteRole(@RequestParam("id") int id) {
		return new ResultBean<Boolean>(managerServiceImpl.deleteRoleById(id));
	}

	/*
	 * 卸任
	 */
	@RequestMapping(value = "/deleteGroupByUid.action")
	@ResponseBody
	public IResult deleteGroupByUid(@RequestParam("uid") String uid) {
		return new ResultBean<Boolean>(managerServiceImpl.deleteGroupByUid(uid));
	}

	/*
	 * 查看角色信息是否存在
	 */
	@RequestMapping("/selectRolecodeOrRolenameIsTrue.action")
	@ResponseBody
	public IResult selectRoleInfo(@RequestParam(value = "gname", defaultValue = "") String gname,
			@RequestParam(value = "gcode", defaultValue = "") String gcode) {
		return new ResultBean<Boolean>(managerServiceImpl.selectRoleInfo(gname, gcode));
	}

	/*
	 * 添加角色
	 */
	@RequestMapping("/addRole.action")
	@ResponseBody
	public IResult addRole(@RequestParam(value = "gname", defaultValue = "") String gname,
			@RequestParam(value = "gcode", defaultValue = "") String gcode) {
		return new ResultBean<Boolean>(managerServiceImpl.insertRole(gname, gcode));
	}

	/*
	 * 修改角色
	 */
	@RequestMapping("/chanageRole.action")
	@ResponseBody
	public IResult chanageRole(@RequestParam(value = "id") int id, @RequestParam(value = "gname") String gname,
			@RequestParam(value = "gcode") String gcode) {
		return new ResultBean<Boolean>(managerServiceImpl.updateRole(id, gname, gcode));
	}

	/*
	 * 查询某个id的角色信息
	 */
	@RequestMapping("/selectRoleById.action")
	@ResponseBody
	public IResult selectRoleById(@RequestParam(value = "id") int id) {
		return new ResultBean<Group>(managerServiceImpl.selectRoleById(id));
	}

	/*
	 * 每个角色对应的列表
	 */
	@RequestMapping("/selectManagerAllUser.action")
	@ResponseBody
	public IResult selectManagerAllUser(@RequestParam(value = "gid") int gid,
			@RequestParam(value = "username", defaultValue = "") String username, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		return new PageResultBean<Collection<User>>(managerServiceImpl.selectManagerAllUser(gid, page, limit, username),
				managerServiceImpl.selectManagerAllUserCount(gid, username));
	}

	/*
	 * 查找不是某个角色的员工列表
	 */
	@RequestMapping("/selectManagerAllUserNoGroup.action")
	@ResponseBody
	public IResult selectManagerAllUserNoGroup(@RequestParam("id") int id, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		return new PageResultBean<Collection<UserGroupDepartmentPovo>>(
				managerServiceImpl.selectManagerAllUserNoGroup(page, limit, id),
				managerServiceImpl.selectManagerAllUserNoGroupCount(id));
	}

    /*
     * 查找不是某个角色的员工列表
     */
    @RequestMapping("/updateGroupById.action")
    @ResponseBody
    public IResult updateGroupById(@RequestParam("gid") int gid, @RequestParam("uid") String uid) {
        return new ResultBean<Boolean>(managerServiceImpl.updateGroupById(gid , uid));
    }
}