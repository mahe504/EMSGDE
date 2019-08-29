package com.mahe.hitt.regist.service;

import com.mahe.hitt.entity.User;

/**
 * @Author 马鹤
 * @Date 2019/7/7--
 * @Description 用户注册的业务接口
 **/
public interface RegistService {
    /*
    *   查找用户是否注册
    * */
    Boolean selectUserByUsername(String username);

    /*
    *   注册用户
    * */
    Boolean insertUser(User user);
}
