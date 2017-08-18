package com.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	
	// 用户登录
	public boolean login(Map<String, Object> params);
	
	/**
	 * 系统目录管理
	 */
	// 导航管理
	public List<Map<String, Object>> getCategoryList(Map<String, Object> params);
	// 修改导航
	public int updateCategory(Map<String, Object> params);
	// 新增导航
	public int insertCategory(Map<String, Object> params);
	
	
	/**
	 * 系统用户管理
	 */
	// 系统用户管理
	public List<Map<String, Object>> getSysUserList(Map<String, Object> param);
	// 修改系统用户
	public int updateSysUser(Map<String, Object> param);
	// 新增系统用户
	public int insertSysUser(Map<String, Object> param);
	// 修改系统用户密码
	public int updateSysUserPwd(Map<String, Object> param);
	
	
	
	/**
	 * 用户管理
	 */
	// 用户管理
	public List<Map<String, Object>> getUserList(Map<String, Object> param);
	// 修改用户
	public int updareUser(Map<String, Object> param);
	// 新增用户
	public int insertUser(Map<String, Object> param);
	
	
	/**
	 * 套餐管理
	 */
	public List<Map<String, Object>> getPackageList(Map<String, Object> param);
	public int updarePackage(Map<String, Object> param);
	public int insertPackage(Map<String, Object> param);
	
	
	/**
	 * 订单管理
	 */
	public List<Map<String, Object>> getOrderList(Map<String, Object> param);
}
