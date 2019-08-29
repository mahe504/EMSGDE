package com.mahe.hitt.admin.service.impl;

import com.mahe.hitt.admin.service.AdminService;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/19--16:59
 * @Description 管理员的业务层实现类
 **/
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	/*
	 * 实现了查询全部管理员的信息
	 */
	@Override
	public List<User> selectAdmin(int page, int limit, String username) {
		PageInfo info = new PageInfo(limit, page);
		System.out.println(adminMapper.selectAdminAll(info.getStart(), info.getEnd(), username));
		return adminMapper.selectAdminAll(info.getStart(), info.getEnd(), username);
	}

	/*
	 * 实现了查询管理员的总数
	 */
	@Override
	public int selectAdminAllCount(String username) {
		return adminMapper.selectAdminAllCount(username);
	}

	/*
	 * 实现管理员的状态的修改的业务
	 */
	@Override
	public Boolean updateAdminStatusByUid(int status, String uid) {
		return adminMapper.updateAdminStatusByUid(status, uid);
	}

	/*
	 * 按uid删除管理员
	 */
	@Override
	public Boolean deleteAdminByUid(String uid) {
		String[] newUid = uid.split(",");
		Boolean flag = false;
		for (String nUid : newUid) {
			if (nUid != "" && nUid != null) {
				flag = adminMapper.deleteAdminByUid(nUid);
			}
		}
		return flag;
	}

	@Override
	public List<User> selectUserNoAdmin(int page, int limit, String username) {
		PageInfo info = new PageInfo(limit, page);
		return adminMapper.selectUserNoAdmin(info.getStart(), info.getEnd(), username);
	}

	@Override
	public Integer selectUserNoAdminCount(String username) {
		return adminMapper.selectUserNoAdminCount(username);
	}

    @Override
    public Boolean insertAdminByUid(String uid) {
	    String [] newUid = uid.split(",");
        Boolean flag = false;
        for (String nUid : newUid) {
            if (nUid != "" && nUid != null) {
                flag = adminMapper.insertAdminByUid(nUid);
            }
        }
        return flag;
    }
}
