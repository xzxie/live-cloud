package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dao.UserDao;
import com.util.Constant;
import com.util.DBUtil;
import com.util.Page;
import com.util.RegExpUtil;
import com.util.crypto.Md5Util;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	
	public List<Map<String, Object>> getUserInfoList(Map<String, Object> params, Page page) {
		String userId = (String) params.get("id");
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		String status = (String) params.get("status");
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sbsql = new StringBuilder("select * from t_user where 1=1 ");
		if (StringUtils.isNotBlank(userId)) {
			sbsql.append("and id = ? ");
			paramList.add(userId);
		}
		if (StringUtils.isNotBlank(username)) {
			if (RegExpUtil.isEmail(username)) {
				sbsql.append("and email = ? ");
				paramList.add(username);
			} else if (RegExpUtil.isPhone(username)) {
				sbsql.append("and phone = ? ");
				paramList.add(username);
			}
		}
		if (StringUtils.isNotBlank(password)) {
			sbsql.append("and password = ? ");
			paramList.add(password);
		}
		if (StringUtils.isNotBlank(status)) {
			sbsql.append("and status = ? ");
			paramList.add(status);
		}
		if (page != null) {
			sbsql.append("limit ?,? ");
			paramList.add(page.getOffset());
			paramList.add(page.getPageSize());
		}
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sbsql.toString(), paramList.toArray());
		return resultList;
	}
	
	public Map<String, Object> getUserinfoByOpenId(String openID, int type) {
		if (StringUtils.isBlank(openID)) {
			return MapUtils.EMPTY_MAP;
		}
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sbsql = new StringBuilder("select * from t_user_oauth where 1=1 and openid = ? and type = ? and status=0");
		paramList.add(openID);
		paramList.add(type);
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sbsql.toString(), paramList.toArray());
		return resultList.get(0);
	}
	
	public List<Map<String, Object>> getUserInfoList_qq(Map<String, Object> params, Page page) {
		return null;
	}
	
	public List<Map<String, Object>> getUserInfoList_weixin(Map<String, Object> params, Page page) {
		return null;
	}
	
	public List<Map<String, Object>> getUserInfoList_weibo(Map<String, Object> params, Page page) {
		return null;
	}
	
	
	public int login_qq(Map<String, Object> params) {
		return -1;
	}
	
	public int login_weixin(Map<String, Object> params) {
		return -1;
	}
	
	public int login_weibo(Map<String, Object> params) {
		return -1;
	}
	
	public int reg(Map<String, Object> params) {
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		boolean isEmail = RegExpUtil.isEmail(username);
		boolean isPhone = RegExpUtil.isPhone(username);
		
		List<String> list = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("insert into t_user(nikename, password, salt, phone, email, create_time) values(?,?,?,?,?, now())");
		
		String salt = Md5Util.getSalt();
		
		list.add(username);
		list.add(Md5Util.md5(password, salt));
		list.add(salt);
		if (isPhone) {
			list.add(username);
			list.add("");
		} else if (isEmail) {
			list.add("");
			list.add(username);
		}
		
		int result = DBUtil.executeUpdate(sql.toString(), list.toArray());
		
		return result;
	}
	
	public int reg_qq(Map<String, Object> params) {
		return -1;
	}
	
	public int reg_weixin(Map<String, Object> params) {
		return -1;
	}
	
	public int reg_weibo(Map<String, Object> params) {
		String weibo = (String) params.get("username");
		StringBuilder sql_weibo = new StringBuilder("insert into t_user_weibo() values(?,?,?,?,?)");
		StringBuilder sql = new StringBuilder("insert into t_user(nikename, password, salt, phone, email, create_time) values(?,?,?,?,?, now())");
		return -1;
	}
	
	public String getSalt(String username) {
		if (StringUtils.isBlank(username)) {
			return Constant.password_default_salt;
		}
		boolean isEmail = RegExpUtil.isEmail(username);
		boolean isPhone = RegExpUtil.isPhone(username);
		StringBuilder sql = new StringBuilder("select salt from t_user where ");
		if (isEmail) {
			sql.append("email = ? limit 1");
		} else if (isPhone) {
			sql.append("phone = ? limit 1");
		}
		List<String> list = new ArrayList<String>();
		list.add(username);
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sql.toString(), list.toArray());
		String salt = resultList.get(0).get("salt") + "";
		return salt;
	}
}
