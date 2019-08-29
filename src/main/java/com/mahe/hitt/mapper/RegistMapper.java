package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.User;

/**
 * @Author 马鹤
 * @Date 2019/7/7--
 * @Description 注册的持久接口
 **/
public interface RegistMapper {

    /*
    *   根据你的名字查找用户
    * */
    User selectUserByUsername(String username);

    /*
    *   注册
    * */
    Boolean insertUser(User user);
}
