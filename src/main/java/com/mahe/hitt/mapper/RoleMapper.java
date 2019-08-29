package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/14--14:58
 * @Description 角色的持久接口
 **/
public interface RoleMapper {

    List<Role> selectRoleByUsername(@Param("username") String username);

    String selectRoleNameByUsername(@Param("username") String username);
}
