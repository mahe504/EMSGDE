package com.mahe.hitt.login.service.impl;

import com.mahe.hitt.entity.Loginlog;
import com.mahe.hitt.entity.User;
import com.mahe.hitt.login.service.LoginService;
import com.mahe.hitt.mapper.LoginMapper;
import com.mahe.hitt.mapper.RoleMapper;
import com.mahe.hitt.utils.IPUtils;
import com.mahe.hitt.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/5--
 * @Description 登录接口的实现类
 **/
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
    private RoleMapper roleMapper;

	@Autowired
	private HttpSession session;
	/*
	 * 登录的业务代码
	 */
	@Override
	public String selectUserByUsernameAndPassword(User user, HttpServletRequest request)
			throws AuthenticationException {
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		/* if (!subject.isAuthenticated()) { */
		try {
            ThreadContext.remove(ThreadContext.SUBJECT_KEY);
			subject.login(token);
			if (user.getRemember() != null) {
				if (user.getRemember() == 1) {
					user.setRemember(0);
					token.setRememberMe(true);
				}
			}

		} /*
			 * catch(LockedAccountException lae){ return "Accect Is No Status"; }
			 */catch (UnknownAccountException uae) {
			return "你的账号不存在";
		} catch (IncorrectCredentialsException ice) {
			return "你的账号或密码错误";
		} catch (AuthenticationException authenticationException) {
			return "账号密码错误！";
		} catch (UnknownSessionException use) {
			return "有别的账号登录！";
		}
		if (session.getAttribute("user") != null) {
			return "此电脑已经有账号登录或者上次退出异常请关闭浏览器重新登录！";
		}
		/* } */
		user = loginMapper.selectUserByUsername(user.getUsername());
		if ("1".equals(user.getStatus())) {
			session.setAttribute("user", user);
			String rolename = roleMapper.selectRoleNameByUsername(user.getUsername());
			session.setAttribute("rolename" , rolename);

			/*
			 * 获取当前登录者的账号和ip还有时间
			 */
			if(loginMapper.selectLoginlogByUsername(user.getUsername())==null){
                loginMapper.insertLoginLog(
                        new Loginlog(UUIDUtil.getUUID(), user.getUsername(), new Date(), IPUtils.getIPAddress(request)));
            }else{
                loginMapper.updateLoginLogByUsername( new Loginlog("1", user.getUsername(), new Date(), IPUtils.getIPAddress(request)));
            }
			return "login";
		} else {
			return "你的账号的角色已经被禁用暂时不能登录，请联系超级管理员！";
		}
	}
}
