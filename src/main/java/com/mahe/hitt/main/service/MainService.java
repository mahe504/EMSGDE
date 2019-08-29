package com.mahe.hitt.main.service;

import com.mahe.hitt.entity.Loginlog;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author 马鹤
 * @Date 2019/7/10--
 * @Description 主页业务接口
 **/
public interface MainService {

    /*
    *   分页查询登录日志的信息 page 页序 limit 数量 loginname 登录者的名字
    * */
	List<Loginlog> selectLoginlogInfo(int page, int limit, String loginName);

	/*
	*   查询登录日志的总数
	* */
	int selectLoginlogCount(String loginName);

	/*
	*   查询菜单
	* */
	Map<String , Object> selectMeun(HttpSession session);

	/*
	*   查找对应的id删除
	* */
	Boolean deleteLoginlogById(String id);
}
