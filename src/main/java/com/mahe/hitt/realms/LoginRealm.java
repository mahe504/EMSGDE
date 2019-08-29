package com.mahe.hitt.realms;

import com.mahe.hitt.entity.Permission;
import com.mahe.hitt.entity.Role;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.mapper.LoginMapper;
import com.mahe.hitt.mapper.PermissionMapper;
import com.mahe.hitt.mapper.RoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author 马鹤
 * @Date 2019/7/5--
 * @Description
 **/
public class LoginRealm extends AuthorizingRealm {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
    private RoleMapper roleMapper;

	@Autowired
    private PermissionMapper permissionMapper;
	@Override
	public String getName() {
		return "loginRealm";
	}

	/*
	*   授权方法
	* */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new LinkedHashSet<String>();
        Set<String> pers = new LinkedHashSet<String>();

        List<Role> roleLs = roleMapper.selectRoleByUsername((String) principalCollection.getPrimaryPrincipal());
        for (Role role: roleLs) {
            List<Permission> permissions = permissionMapper.selectPermissionByRolecode(role.getRcode());
            for(Permission permission : permissions){
                pers.add(permission.getName());
            }
            roles.add(role.getRcode());
        }
        if(roles==null){
            return null;
        }
        info.addRoles(roles);
        info.setStringPermissions(pers);
        roles.clear();
        pers.clear();
		return info;
	}

	/*
	*   认证方法
	* */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		SimpleAuthenticationInfo info = null;
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = loginMapper.selectUserByUsername(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException();
		}
		/*
		 * DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)
		 * SecurityUtils.getSecurityManager(); DefaultWebSessionManager sessionManager =
		 * (DefaultWebSessionManager) securityManager.getSessionManager();
		 * Collection<Session> sessions =
		 * sessionManager.getSessionDAO().getActiveSessions();// 获取当前已登录的用户session列表 for
		 * (Session session : sessions) { // 清除该用户以前登录时保存的session if
		 * (token.getUsername().equals(String.valueOf(session.getAttribute(
		 * DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) { try{
		 * sessionManager.getSessionDAO().delete(session); }catch
		 * (UnknownSessionException use){ throw new UnknownSessionException(); } } }
		 */
		/* user.setPassword(""); */

		return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
				ByteSource.Util.bytes(token.getUsername()), this.getName());
	}
}
