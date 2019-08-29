package com.mahe.hitt.work.service.impl;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.Turnwork;
import com.mahe.hitt.entity.Work;
import com.mahe.hitt.mapper.DepartmentMapper;
import com.mahe.hitt.mapper.WorkMapper;
import com.mahe.hitt.work.service.WorkService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/25--9:43
 * @Description 出勤率的业务实现类
 **/
@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkMapper workMapper;

	@Autowired
	private DepartmentMapper departmentMapper;

	private String[] getStringV(String x, String y) {
		String[] newParam = y.split(",");
		String[] param = new String[newParam.length + 1];
        param[0] = x;
		for (int i = 1; i <= newParam.length; i++) {
            param[i] = newParam[i - 1];
		}
		return param;
	}
	/*
	 * 获取出勤记录
	 */
	@Override
	public JSONArray getWorkData() {
		JSONArray json = new JSONArray();
		int departmentCount = workMapper.selectDepartmentListCount();
		String yn = "";
		List<Department> dnames = workMapper.selectDepartmentList();
		for (Department d : dnames) {
			yn = yn + d.getDname() + ",";
		}
		List<Work> works = workMapper.selectWorkData();
		String year = "";
		for (Work work : works) {
			year = year + "," + work.getYear();
		}
		String[] newWork = year.split(",");
        String[] work = new String[newWork.length + 1];
        work[0] = "product";
        for (int i = 1; i <= newWork.length; i++) {
            work[i] = newWork[i - 1];
        }
		json.add(work);
		String[] newYn = yn.replace("无部门", "").trim().split(",");
        List<Turnwork> numbers = new LinkedList<>();
		for (int i = 0; i < newYn.length; i++) {
			if (newYn[i] != "" && newYn[i] != null) {
				numbers = workMapper.selectWorkDataNumber(newYn[i]);
				String param = "";
				for (Turnwork w : numbers) {
					param = param + "," + w.getTurnwork();
				}
				json.add(getStringV(newYn[i], param));
			}
		}
		return json;
	}

}
