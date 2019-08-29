package com.mahe.hitt.permission.service;

import com.mahe.hitt.entity.Meun;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/15--19:04
 * @Description 权限的业务接口
 **/
public interface PermissionService {

	List<Meun> selectPermission(String title , HttpSession session);
}
