package com.mahe.hitt.admin.controller;

import com.mahe.hitt.admin.service.AdminService;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @Author 马鹤
 * @Date 2019/7/19--12:17
 * @Description 管理员模块控制器
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminServiceImpl;

	/*
	 * 跳转管理员页面
	 */
	@RequestMapping(value = "/adminList.action")
	public String adminList() {
		return "forward:/WEB-INF/jsps/admin/admin.html";
	}
	/*
	 * 跳转添加管理员页面
	 */
	@RequestMapping(value = "/addAdmin.action")
	public String addAdmin() {
		return "forward:/WEB-INF/jsps/admin/addadmin.html";
	}

	/*
	 * 查询所有的管理员
	 */
	@RequestMapping(value = "/selectAdminAll.action")
	@ResponseBody
	public IResult selectAdminAll(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "username", defaultValue = "") String username) {
		System.out.println(adminServiceImpl.selectAdmin(page, limit, username));
		return new PageResultBean<Collection<User>>(adminServiceImpl.selectAdmin(page, limit, username),
				adminServiceImpl.selectAdminAllCount(username));
	}

	/*
	 * 修改管理员的状态
	 */
	@RequestMapping(value = "/editAdminStatus.action")
	@ResponseBody
	public IResult editAdminStatus(@RequestParam("status") int status, @RequestParam("uid") String uid) {
		return new ResultBean<Boolean>(adminServiceImpl.updateAdminStatusByUid(status, uid));
	}

	/*
	 * 按uid删除管理员
	 **/
	@RequestMapping(value = "/deleteAdminByUid.action")
	@ResponseBody
	public IResult deleteAdminByUid(String uid) {
		return new ResultBean<Boolean>(adminServiceImpl.deleteAdminByUid(uid));
	}

	@RequestMapping(value = "/selectUserNoAdmin.action")
	@ResponseBody
	public IResult selectUserNoAdmin(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "username", defaultValue = "") String username) {
		return new PageResultBean<Collection<User>>(adminServiceImpl.selectUserNoAdmin(page, limit, username),
				adminServiceImpl.selectUserNoAdminCount(username));
	}

	@RequestMapping("/addAdminByUid.action")
    @ResponseBody
    public IResult addAdmin(@RequestParam("uid") String uid){
        return new ResultBean<Boolean>(adminServiceImpl.insertAdminByUid(uid));
    }
}
