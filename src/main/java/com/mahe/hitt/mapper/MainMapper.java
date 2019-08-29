package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Loginlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/10--
 * @Description 主页持久接口
 **/
public interface MainMapper {
    /*
    *   查询系统用户登录信息
    * */
	List<Loginlog> selectLoginlogInfo(@Param("start") Integer start , @Param("end") Integer end , @Param("logusername") String loginName);

	/*
	*   查询登录信息的总数
	* */
    int selectLoginlogCount(@Param("logusername") String loginName);

    /*
    *   按照id删除登陆日志信息
    * */
    Boolean deleteLoginlogById(String id);
}
