package com.mahe.hitt.man.service.impl;

import com.mahe.hitt.entity.Group;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.entity.povo.UserGroupDepartmentPovo;
import com.mahe.hitt.man.service.ManagerService;
import com.mahe.hitt.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/27--8:45
 * @Description 角色和权限管理的业务实现类
 **/
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;

	/*
	 * 查询角色
	 */
	@Override
	public List<Group> selectRole(int page, int limit, String gname) {
		PageInfo info = new PageInfo(limit, page);
        List<Group> groups = managerMapper.selectRole(info.getStart(), info.getEnd(), gname);
        for(Group group : groups){
            group.setGcount(managerMapper.selectRoleAsUserCount(group.getId()));
        }
		return managerMapper.selectRole(info.getStart(), info.getEnd(), gname);
	}

	/*
	 * 查询角色的数量
	 */
	@Override
	public int selectRoleCount(String gname) {
		return managerMapper.selectRoleCount(gname);
	}

	/*
	 * 删除角色
	 */
	@Override
	public Boolean deleteRoleById(int id) {
		if (managerMapper.deleteRoleById(id)) {
           /* managerMapper.deleteRoleAsPermissionByRid(id);*/
			return true;
		}
		return false;
	}

    @Override
    public Boolean selectRoleInfo(String gname, String gcode) {
	    if(managerMapper.selectRoleInfo(gname , gcode)==null){
            return false;
        }
        return true;
    }

    /*
    *   添加角色
    * */
    @Override
    public Boolean insertRole(String gname, String gcode) {
        return managerMapper.insertRole(gname , gcode);
    }

    @Override
    public Boolean updateRole(int id, String gname, String gcode) {
        return managerMapper.updateRoleById(id , gname , gcode);
    }

    /*
    *   查询某个id的角色信息
    * */
    @Override
    public Group selectRoleById(int id) {
        return managerMapper.selectRoleById(id);
    }

    /*
     *   每个角色对应的列表
     * */
    @Override
    public List<User> selectManagerAllUser(int gid , int page , int limit ,String username) {
        PageInfo info = new PageInfo(limit , page );
        return managerMapper.selectManagerAllUser(gid , username , info.getStart() , info.getEnd());
    }

    /*
     *   查询某个角色对应的列表的数量
     * */
    @Override
    public int selectManagerAllUserCount(int gid, String username) {
        return managerMapper.selectManagerAllUserCount(gid , username);
    }

    /*
    *   卸任
    * */
    @Override
    public Boolean deleteGroupByUid(String uid) {
        return managerMapper.deleteGroupByUid(uid);
    }

    /*
     *   查找不是某个角色的员工列表
     * */
    @Override
    public List<UserGroupDepartmentPovo> selectManagerAllUserNoGroup(int page , int limit , int id) {
        PageInfo info = new PageInfo(limit , page );
        return managerMapper.selectManagerAllUserNoGroup(info.getStart() , info.getEnd() , id);
    }

    /*
     *   查找某个角色不是的员工的数量
     * */
    @Override
    public Integer selectManagerAllUserNoGroupCount(int id) {
        return managerMapper.selectManagerAllUserNoGroupCount(id);
    }

    @Override
    public Boolean updateGroupById(int gid, String uid) {
        return managerMapper.updateGroupById(gid , uid);
    }
}
