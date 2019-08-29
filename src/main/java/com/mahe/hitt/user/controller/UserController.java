package com.mahe.hitt.user.controller;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserDepartmentPovo;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.user.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/18--15:01
 * @Description 个人信息控制器
 **/
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	/*
	 * 查询个人信息
	 */
	@RequestMapping(value = "/selectUser.action")
	@ResponseBody
	public JSONArray selectUser(HttpSession session) {
		return JSONArray.fromObject(userServiceImpl.selectUser(session));
	}

	/*
	 * 修改个人资料
	 */
	@RequestMapping(value = "/chanageUser.action")
	@ResponseBody
	public Boolean chanageUser(User user, HttpSession session) {
		System.out.println(user);
		return userServiceImpl.updateUser(user, session);
	}

	/*
	 * 跳转员工页面
	 */
	@RequestMapping(value = "/userList.action")
	public String userList() {
		return "forward:/WEB-INF/jsps/user/user.html";
	}

	/*
	*   跳转设置的页面
	* */
	@RequestMapping(value="setupUser.action")
    public String  setupUser(){
	    return "forward:/WEB-INF/jsps/user/setupuser.html";
    }

    /*
	 * 查询全部员工
	 */
	@RequestMapping(value = "/selectUserAll.action")
	@ResponseBody
	public IResult selectUserAll(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "realname", defaultValue = "") String realname , @RequestParam(value = "dname" , defaultValue = "") String dname) {
		return new PageResultBean<Collection<UserDepartmentPovo>>(
				userServiceImpl.selectUserAll(limit, page, realname , dname),
				userServiceImpl.selectUserAllCount(realname , dname));
	}

	/*
	*   删除id的员工
	* */
	@RequestMapping(value="/deleteUserByUid.action")
    @ResponseBody
    public IResult deleteUser(@RequestParam("uid") String uid){
        return new ResultBean<Boolean>(userServiceImpl.deleteUserByUid(uid));
    }

    /*
    *   查询部门
    * */
    @RequestMapping(value="/userSelectDepartment.action")
    @ResponseBody
    public IResult userSelectDepartment(){
        return new ResultBean<List<Department>>(userServiceImpl.userSelectDepartment());
    }

    /*
    *   查询user所在的部门
    * */
    @RequestMapping(value="/selectUserIsDepartment.action")
    @ResponseBody
    public IResult selectUserIsDepartment(@RequestParam("uid") String uid){
        return new ResultBean<User>(userServiceImpl.selectUserIsDepartment(uid));
    }

    /*
    *   修改员工所在的部门
    * */
    @RequestMapping(value="/updateUserIsDepartment.action")
    @ResponseBody
    public IResult updateUserIsDepartment(@RequestParam("uid") String uid , @RequestParam("did") int did){
        return new ResultBean<Boolean>(userServiceImpl.updateUserIsDepartment(uid , did));
    }

    /*
    *   查询是否已经存在用户名
    * */
    @RequestMapping(value="selectUsername.action")
    @ResponseBody
    public IResult selectUsername(@RequestParam("username") String username){
        return new ResultBean<Boolean>(userServiceImpl.selectUserByUsername(username));
    }

    /*
     *   添加员工
     * */
    @RequestMapping(value="addUser.action")
    @ResponseBody
    public IResult addUser(User user){
        return new ResultBean<Boolean>(userServiceImpl.insertUser(user));
    }

    /*
     *   修改密码
     * */
    @RequestMapping(value="updatePassword.action")
    @ResponseBody
    public IResult updatePassword(@RequestParam("password") String password , HttpSession session){
        return new ResultBean<Boolean>(userServiceImpl.updatePassword(password , session));
    }
}
