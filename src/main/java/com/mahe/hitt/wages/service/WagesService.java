package com.mahe.hitt.wages.service;

import com.mahe.hitt.entity.Wages;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/26--
 * @Description 工资的业务方法接口
 **/
public interface WagesService {

	/*
	 * 查询工资等级
	 */
	List<Wages> selectWagesAll(int page, int limit, String lv);

	/*
	 * 查询工资等级数量
	 */
	int selectWagesCount(String lv);

	/*
	 * 删除工资等级
	 */
	Boolean deleteWagesById(int id);

	/*
	 * 查询工资名称
	 */
	Boolean selectWagesByLv(String lv);

	/*
	 * 添加工资
	 */
	Boolean insertWages(String lv, double wnumber);

	/*
	*   修改工资等级
	* */
    Boolean updateWagesById(int id, String lv, double wnumber);

    /*
    *   根据id获取工资等级
    * */
    Wages selectWagesById(int id);
}
