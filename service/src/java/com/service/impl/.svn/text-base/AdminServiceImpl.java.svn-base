package com.service.impl;

import java.util.List;
import java.util.Map;

import com.dao.AdminDao;
import com.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;
	
	
	public boolean login(Map<String, Object> params) {
		return adminDao.login(params);
	}
	
	public List<Map<String, Object>> getCategoryList(Map<String, Object> params) {
		return adminDao.getCategoryList(params);
	}
	public int updateCategory(Map<String, Object> params) {
		return adminDao.updateCategory(params);
	}
	public int insertCategory(Map<String, Object> params) {
		return adminDao.insertCategory(params);
	}
	
	
	public List<Map<String, Object>> getSysUserList(Map<String, Object> param) {
		return adminDao.getSysUserList(param);
	}
	public int updateSysUser(Map<String, Object> param) {
		return adminDao.updateSysUser(param);
	}
	public int insertSysUser(Map<String, Object> param) {
		return adminDao.insertSysUser(param);
	}
	public int updateSysUserPwd(Map<String, Object> param) {
		return adminDao.updateSysUserPwd(param);
	}
	
	
	public List<Map<String, Object>> getUserList(Map<String, Object> param) {
		return adminDao.getUserList(param);
	}
	public int updareUser(Map<String, Object> param) {
		return adminDao.updareUser(param);
	}
	public int insertUser(Map<String, Object> param) {
		return adminDao.insertUser(param);
	}
	
	
	public List<Map<String, Object>> getPackageList(Map<String, Object> param) {
		return adminDao.getPackageList(param);
	}
	public int updarePackage(Map<String, Object> param) {
		return adminDao.updarePackage(param);
	}
	public int insertPackage(Map<String, Object> param) {
		return adminDao.insertPackage(param);
	}
	
	
	public List<Map<String, Object>> getOrderList(Map<String, Object> param) {
		return adminDao.getOrderList(param);
	}
	
	
	// getter/setter
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
}
