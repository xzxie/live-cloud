package com.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.dao.UserDao;
import com.service.UserService;
import com.util.Page;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	
	public List<Map<String, Object>> getUserInfoList(Map<String, Object> params, Page page) {
		return userDao.getUserInfoList(params, page);
	}
	
	public Map<String, Object> getUserinfoByOpenId(String openID, int type) {
		return userDao.getUserinfoByOpenId(openID, type);
	}
	
	public int login(Map<String, Object> params) {
		int ret = 0;
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		params.remove("password");
		List<Map<String, Object>> userinfoList = getUserInfoList(params, null);
		if (CollectionUtils.isEmpty(userinfoList)) {
			ret = 1;	// 用户不存在
			return ret;
		}
		
		Map<String, Object> userinfo = userinfoList.get(0);
		if (MapUtils.isEmpty(userinfo)) {
			ret = -1;	// 用户无法登录
			return ret;
		}
		
		boolean isCorrect = password.equals(userinfo.get("password"));
		if (!isCorrect) {
			ret = 2;	// 密码不正确
			return ret;
		}
		
		int status = Integer.valueOf(userinfo.get("status")+"");
		if (status == 1) {
			ret = 3;	// 用户失效
			return ret;
		}
		return ret;
	}
	
	public int login_qq(Map<String, Object> params) {
		return userDao.login_qq(params);
	}
	
	public int login_weixin(Map<String, Object> params) {
		return userDao.login_weixin(params);
	}
	
	public int login_weibo(Map<String, Object> params) {
		return userDao.login_weibo(params);
	}
	
	public int reg(Map<String, Object> params) {
		int ret = 0;
		List<Map<String, Object>> userinfoList = getUserInfoList(params, null);
		if (!CollectionUtils.isEmpty(userinfoList)) {
			ret = -1;	// 用户已经存在
		} else {
			ret = userDao.reg(params);
		}
		return ret;
	}
	
	public int reg_qq(Map<String, Object> params) {
		return userDao.reg_weixin(params);
	}
	
	public int reg_weixin(Map<String, Object> params) {
		return userDao.reg_weixin(params);
	}
	
	public int reg_weibo(Map<String, Object> params) {
		return userDao.reg_weibo(params);
	}
	
	public String getSalt(String username) {
		return userDao.getSalt(username);
	}
	
	
	
	/**
	 * getter/setter
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
