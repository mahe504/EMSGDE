package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Wages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/26--9:28
 * @Description 工资的持久接口
 **/
public interface WagesMapper {

	/*
	 * 查询工资等级
	 */
	List<Wages> selectWagesAll(@Param("start") int start, @Param("end") int end, @Param("lv") String lv);

	/*
	 * 查询工资等级的数量
	 */
	int selectWagesAllCount(@Param("lv") String lv);

	/*
	 * 删除工资等级
	 */
	Boolean deleteWagesById(@Param("id") int id);

	/*
	 * 查询工资名称
	 */
	Wages selectWagesByLv(@Param("lv") String lv);

	/*
	 * 添加工资
	 */
	Boolean insertWages(@Param("lv") String lv, @Param("wnumber") double wnumber);

	/*
	 * 修改工资等级
	 */
	Boolean updateWagesById(@Param("id") int id, @Param("lv") String lv, @Param("wnumber") double wnumber);

	/*
	*   根据id查询工资等级信息
	* */
    Wages selectWagesById(int id);
}
