package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Group;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.povo.UserGroupDepartmentPovo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/27--
 * @Description 角色和权限管理的持久层接口
 **/
public interface ManagerMapper {

	/*
	 * 查询角色
	 */
	List<Group> selectRole(@Param("start") int start, @Param("end") int end, @Param("gname") String gname);

	/*
	 * 查询角色数量
	 */
	int selectRoleCount(@Param("gname") String gname);

	/*
	 * 删除角色
	 */
	Boolean deleteRoleById(int id);

	/*
	 * 删除角色对应的权限
	 *//*
		 * Boolean deleteRoleAsPermissionByRid(int id);
		 */

	/*
	 * 查询角色信息是否存在
	 */
	Group selectRoleInfo(@Param("gname") String gname, @Param("gcode") String gcode);

	/*
	 * 添加角色
	 */
	Boolean insertRole(@Param("gname") String gname, @Param("rcode") String rcode);

	/*
	 * 修改角色信息
	 */
	Boolean updateRoleById(@Param("id") int id, @Param("gname") String gname, @Param("gcode") String gcode);

	/*
	 * 查询某个id的角色的信息
	 */
	Group selectRoleById(int id);

	/*
	 * 查询角色的人数
	 */
	int selectRoleAsUserCount(int id);

	/*
	 * 每个角色对应的列表
	 */
	List<User> selectManagerAllUser(@Param("gid") int gid, @Param("username") String username,
			@Param("start") int start, @Param("end") int end);

	/*
	 * 每个角色对应的列表的数量
	 */
	int selectManagerAllUserCount(@Param("gid") int gid, @Param("username") String username);

	/*
	 * 卸任
	 */
	Boolean deleteGroupByUid(String uid);

	/*
	 * 查找不是某个角色的员工列表
	 */
	List<UserGroupDepartmentPovo> selectManagerAllUserNoGroup(@Param("start") int start, @Param("end") int end,
			@Param("id") int id);

	/*
	 * 查找某个角色不是的员工的数量
	 */
	Integer selectManagerAllUserNoGroupCount(int id);

	/*
	*   修改角色
	* */
    Boolean updateGroupById(@Param("gid") int gid,@Param("uid") String uid);
}
