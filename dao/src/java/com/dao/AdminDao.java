package com.dao;

import java.util.List;
import java.util.Map;

public interface AdminDao {

	public boolean login(Map<String, Object> params);
	
	public List<Map<String, Object>> getCategoryList(Map<String, Object> params);
	public int updateCategory(Map<String, Object> params);
	public int insertCategory(Map<String, Object> params);
	
	public List<Map<String, Object>> getSysUserList(Map<String, Object> param);
	public int updateSysUser(Map<String, Object> param);
	public int insertSysUser(Map<String, Object> param);
	public int updateSysUserPwd(Map<String, Object> param);
	
	public List<Map<String, Object>> getUserList(Map<String, Object> param);
	public int updareUser(Map<String, Object> param);
	public int insertUser(Map<String, Object> param);
	
	public List<Map<String, Object>> getPackageList(Map<String, Object> param);
	public int updarePackage(Map<String, Object> param);
	public int insertPackage(Map<String, Object> param);
	
	public List<Map<String, Object>> getOrderList(Map<String, Object> param);
	
}
