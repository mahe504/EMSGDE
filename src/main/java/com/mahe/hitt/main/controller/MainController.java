package com.mahe.hitt.main.controller;

import com.mahe.hitt.entity.Loginlog;
import com.mahe.hitt.entity.Meun;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author 马鹤
 * @Date 2019/7/8--16:33
 * @Description
 **/
@Controller
@RequestMapping(value = "/main")
public class MainController {

	@Autowired
	private MainService mainServiceImpl;

	/*
	 * 后台主页
	 */
	@RequestMapping(value = "/main.action")
	public String main() {
		return "forward:/WEB-INF/jsps/main/main.jsp";
	}

	/*
	 * 跳转主页
	 */
	@RequestMapping(value = "/showmain.action")
	public String showmain() {
		return "forward:/WEB-INF/jsps/main/main.html";
	}

	/*
	 * 显示登录信息
	 */
	@RequestMapping(value = "/loginlogList.action")
	@ResponseBody
	public IResult loginlogList(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit,
			String logusername) {
		return new PageResultBean<Collection<Loginlog>>(mainServiceImpl.selectLoginlogInfo(page, limit, logusername),
				mainServiceImpl.selectLoginlogCount(logusername));
	}

	/*
	 * 菜单的信息
	 */
	@RequestMapping(value = "meunList.action")
	@ResponseBody
	public IResult meunList(HttpSession session) {
		Map<String, Object> map = mainServiceImpl.selectMeun(session);
		List list = (List) map.get("list");
		IResult result = new ResultBean<Collection<Meun>>(list);
		return result;
	}

	/*
	 * 按照id删除登陆日志信息
	 */
	@RequestMapping(value = "/deleteLoginlogById.action")
	@ResponseBody
	public Boolean deleteLoginlog(String id) {
		return mainServiceImpl.deleteLoginlogById(id);
	}

	@RequestMapping(value = "/proFile.action")
	public String proFile() {
		return "forward:/WEB-INF/jsps/main/profile.html";
	}
}
