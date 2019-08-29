package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/5--15:10
 * @Description User表对应的实体类
 **/
@Data
public class User implements Serializable {
	private String uid;// 唯一主键标识
	private String email;// 用户的email
	private String username;// 用户的登录名
	private String realname;// 用户的真实姓名
	private String password;// 用户的登录密码
	private String gender;// 用户的性别
	private String birthday;// 用户的生日
	private String address;// 用户的地址
	private String phone;// 用户的手机号
	private String ecode;// 邮编
	private String status; // 用户的状态信息
	private Date createtime; // 用户创建的时间
	private Integer remember;// 记住我
    private String pic;//头像
    private double wages;//工资
    private int did;

}
