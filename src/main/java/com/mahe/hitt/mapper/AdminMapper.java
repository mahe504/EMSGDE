package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/19--
 * @Description 管理员持久层接口
 **/
public interface AdminMapper {

    /*
    *   查询管理员
    * */
    List<User> selectAdminAll(@Param("start") int start , @Param("end") int end , @Param("username") String username);

    /*
    *   查询管理员的条目
    * */
    int selectAdminAllCount(@Param("username") String username);

    /*
    *   修改管理员的状态
    * */
	Boolean updateAdminStatusByUid(@Param("status") int status,@Param("uid") String uid);

	/*
	*   按uid删除管理员
	* */
    Boolean deleteAdminByUid(@Param("uid") String uid);

    /*
    *   查询不是管理员的用户
    * */
    List<User> selectUserNoAdmin(@Param("start")int start, @Param("end") int end, @Param("username") String username);

    /*
    *   查询不是管理员的用户的数量
    * */
    int selectUserNoAdminCount(@Param("username") String username);

    /*
    *   按uid添加管理员
    * */
    Boolean insertAdminByUid(@Param("uid") String uid);
}
