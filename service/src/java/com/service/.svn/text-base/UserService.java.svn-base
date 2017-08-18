package com.service;

import java.util.List;
import java.util.Map;

import com.util.Page;


public interface UserService {

	public List<Map<String, Object>> getUserInfoList(Map<String, Object> params, Page page);
	public Map<String, Object> getUserinfoByOpenId(String openID, int type);
	
	public int login(Map<String, Object> params);
	public int login_qq(Map<String, Object> params);
	public int login_weixin(Map<String, Object> params);
	public int login_weibo(Map<String, Object> params);
	
	public int reg(Map<String, Object> params);
	public int reg_qq(Map<String, Object> params);
	public int reg_weixin(Map<String, Object> params);
	public int reg_weibo(Map<String, Object> params);
	
	public String getSalt(String username);
}
