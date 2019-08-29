package com.mahe.hitt.man.service;

import com.mahe.hitt.entity.Group;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserGroupDepartmentPovo;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/27--8:45
 * @Description 角色和权限管理的业务接口
 **/
public interface ManagerService  {

    /*
    *   查询角色
    * */
    List<Group> selectRole(int page, int limit, String gname);

    /*
    *   查询角色的数量
    * */
    int selectRoleCount(String gname);

    /*
    *   删除角色
    * */
    Boolean deleteRoleById(int id);

    /*
    *   查询角色信息是否存在
    * */
    Boolean selectRoleInfo(String gname, String gcode);

    /*
    *   添加角色
    * */
    Boolean insertRole(String gname, String gcode);

    /*
    *   修改角色信息
    * */
    Boolean updateRole(int id, String gname, String gcode);

    /*
    *   查询某个id的角色
    * */
    Group selectRoleById(int id);

    /*
    *   查询某个角色对应的列表
    * */
    List<User> selectManagerAllUser(int gid , int page , int limit ,String username);

    /*
    *   查询某个角色对应的列表的数量
    * */
    int selectManagerAllUserCount(int gid , String username);

    /*
    *   卸任
    * */
    Boolean deleteGroupByUid(String uid);

    /*
    *   查找不是某个角色的员工列表
    * */
    List<UserGroupDepartmentPovo> selectManagerAllUserNoGroup(int page , int limit , int id);

    /*
     *   查找某个角色不是的员工的数量
     * */
    Integer selectManagerAllUserNoGroupCount(int id);

    /*
    *   修改角色
    * */
    Boolean updateGroupById(int id, String uid);
}
