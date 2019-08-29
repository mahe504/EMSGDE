package com.mahe.hitt.main.service.impl;

import com.mahe.hitt.entity.Loginlog;
import com.mahe.hitt.entity.Meun;
import com.mahe.hitt.entity.Permission;
import com.mahe.hitt.entity.Role;
import com.mahe.hitt.entity.pageInfo.PageInfo;
import com.mahe.hitt.main.service.MainService;
import com.mahe.hitt.mapper.MainMapper;
import com.mahe.hitt.mapper.MeunMapper;
import com.mahe.hitt.mapper.PermissionMapper;
import com.mahe.hitt.mapper.RoleMapper;
import com.mahe.hitt.utils.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author 马鹤
 * @Date 2019/7/10--21:02
 * @Description 主页业务实现类
 **/
@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mainMapper;

	@Autowired
	private MeunMapper meunMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RoleMapper roleMapper;

	/*
	 * 查询系统用户登录信息业务代码
	 */
	@Override
	public List<Loginlog> selectLoginlogInfo(int page, int limit, String loginName) {
		PageInfo info = new PageInfo(limit, page);
		return mainMapper.selectLoginlogInfo(info.getStart(), info.getEnd(), loginName);
	}

	/*
	 * 查询用户登录日志总数 loginname登录者的姓名
	 */
	@Override
	public int selectLoginlogCount(String loginName) {
		return mainMapper.selectLoginlogCount(loginName);
	}

	/*
	 * 根据你系统角色的信息查找你对应的菜单的信息
	 */
	@Override
	public Map<String, Object> selectMeun(HttpSession session) {
		List<Meun> meuns = new LinkedList<>();
		List<Role> roles = roleMapper.selectRoleByUsername((String) SecurityUtils.getSubject().getPrincipal());
		session.setAttribute("roles" , roles);
		for (Role role : roles) {
			List<Permission> permissions = permissionMapper.selectPermissionByRolecode(role.getRcode());
			for (Permission permission : permissions) {
				List<Meun> m = meunMapper.selectMeunBypermissionId(permission.getId());
				for (int i = 0; i < m.size(); i++)
					meuns.add(m.get(i));
                System.out.println(meuns);
			}
		}
		Map<String, Object> map = TreeUtil.findTree(meuns);
        session.setAttribute("meunsMap" , map);
		return map;
	}

	@Override
	public Boolean deleteLoginlogById(String id) {
		String[] newId = id.split(",");
		Boolean flag = false;
		for (String nid : newId) {
			if (nid != "" && nid != null) {
				flag = mainMapper.deleteLoginlogById(nid);
			}
		}
		return flag;
	}

}
