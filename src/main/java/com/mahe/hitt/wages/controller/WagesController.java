package com.mahe.hitt.wages.controller;

import com.mahe.hitt.entity.Wages;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.PageResultBean;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.wages.service.WagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @Author 马鹤
 * @Date 2019/7/26--9:26
 * @Description 工资控制器
 **/
@Controller
@RequestMapping("/wages")
public class WagesController {

	@Autowired
	private WagesService wagesServiceImpl;

	/*
	 * 返回工资视图
	 */
	@RequestMapping("/wagesList.action")
	public String wagesList() {
		return "forward:/WEB-INF/jsps/wages/wages.html";
	}

	/*
	 * 返回工资视图
	 */
	@RequestMapping("/addwages.action")
	public String addWages() {
		return "forward:/page/wages/addwages.html";
	}

	/*
	 * 工资列表
	 */
	@RequestMapping("/selectWagesAll.action")
	@ResponseBody
	public IResult selectWagesAll(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam(value = "lv", defaultValue = "") String lv) {
		return new PageResultBean<Collection<Wages>>(wagesServiceImpl.selectWagesAll(page, limit, lv),
				wagesServiceImpl.selectWagesCount(lv));
	}

	/*
	 * 删除工资等级
	 */
	@RequestMapping("/deleteWagesById.action")
	@ResponseBody
	public IResult deleteWages(@RequestParam("id") int id) {
		return new ResultBean<Boolean>(wagesServiceImpl.deleteWagesById(id));
	}

	/*
	 * 查询工资名称
	 */
	@RequestMapping("/selectWagesByLv.action")
	@ResponseBody
	public IResult selectWagesByLv(@RequestParam("lv") String lv) {
		return new ResultBean<Boolean>(wagesServiceImpl.selectWagesByLv(lv));
	}

	/*
	 * 添加工资
	 */
	@RequestMapping("/addUWages.action")
	@ResponseBody
	public IResult addUWages(@RequestParam("lv") String lv, @RequestParam("wnumber") String wnumber) {
		return new ResultBean<Boolean>(wagesServiceImpl.insertWages(lv, Double.parseDouble(wnumber)));
	}

	/*
	 * 修改等级
	 */
	@RequestMapping("/updateWagesById.action")
	@ResponseBody
	public IResult updateWagesByLv(@RequestParam("id") int id, @RequestParam("lv") String lv,
			@RequestParam("wnumber") String wnumber) {
		return new ResultBean<Boolean>(wagesServiceImpl.updateWagesById(id, lv, Double.parseDouble(wnumber)));
	}

	/*
	 * 根据id获取工资信息
	 */
	@RequestMapping("/selectWagesById.action")
	@ResponseBody
	public IResult selectWagesById(@RequestParam("id") int id) {
		return new ResultBean<Wages>(wagesServiceImpl.selectWagesById(id));
	}

}
