package com.mahe.hitt.wages.service.impl;

import com.mahe.hitt.entity.Wages;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.mapper.WagesMapper;
import com.mahe.hitt.wages.service.WagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/26--9:27
 * @Description 工资的业务方法实现类
 **/
@Service
public class WagesServiceImpl implements WagesService {

	@Autowired
	private WagesMapper wagesMapper;

	/*
	 * 查询工资等级
	 */
	@Override
	public List<Wages> selectWagesAll(int page, int limit, String lv) {
		PageInfo info = new PageInfo(limit, page);
		return wagesMapper.selectWagesAll(info.getStart(), info.getEnd(), lv);
	}

	/*
	 * 查询工资数量
	 */
	@Override
	public int selectWagesCount(String lv) {
		return wagesMapper.selectWagesAllCount(lv);
	}

	/*
	 * 删除工资等级
	 */
	@Override
	public Boolean deleteWagesById(int id) {
		return wagesMapper.deleteWagesById(id);
	}

	/*
	 * 查询工资名称
	 */
	@Override
	public Boolean selectWagesByLv(String lv) {
		if (wagesMapper.selectWagesByLv(lv) != null) {
			return true;
		}
		return false;
	}

	/*
	*   添加工资
	* */
    @Override
    public Boolean insertWages(String lv, double wnumber) {
        return wagesMapper.insertWages(lv , wnumber);
    }

    /*
    *   修改工资等级
    * */
    @Override
    public Boolean updateWagesById(int id, String lv, double wnumber) {
        return wagesMapper.updateWagesById(id , lv, wnumber);
    }

    @Override
    public Wages selectWagesById(int id) {
        return wagesMapper.selectWagesById(id);
    }

}
