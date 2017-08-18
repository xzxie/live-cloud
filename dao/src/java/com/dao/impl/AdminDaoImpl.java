package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dao.AdminDao;
import com.util.DBUtil;

public class AdminDaoImpl implements AdminDao {

	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);
	
	public List<Map<String, Object>> getCategoryList(Map<String, Object> params) {
		String id = (String) params.get("id");
		String level = (String) params.get("level");
		String parent_id = (String) params.get("parent_id");
		String status = (String) params.get("status");
		
		List<Object> parmList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_admin_category info where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and info.id =?");
			parmList.add(id);
		}
		if (StringUtils.isNotBlank(parent_id)) {
			sql.append(" and info.parent_id =?");
			parmList.add(parent_id);
		}
		if (StringUtils.isNotBlank(level)) {
			String[] levels = level.split(",");
			if (levels.length == 1) {
				sql.append(" and info.level=?");
				parmList.add(level);
			} else {
				sql.append(" and info.level in(?,?)");
				parmList.add(levels[0]);
				parmList.add(levels[1]);
			}
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" and info.status =?");
			parmList.add(status);
		}
		sql.append(" order by value");
		List<Map<String, Object>> cateList = DBUtil.executeQuery(sql.toString(), parmList.toArray());
		return cateList;
	}
	public int updateCategory(Map<String, Object> params) {
		String id = (String) params.get("id");
		String level = (String) params.get("level");
		String description = (String) params.get("description");
		String parent_id = (String) params.get("parent_id");
		String status = (String) params.get("status");
		StringBuilder sql = new StringBuilder("update t_admin_category set ");
		sql.append("update_time=now()");
		if (StringUtils.isNotBlank(level)) {
			sql.append(", level=?");
		}
		if (StringUtils.isNotEmpty(description)) {
			sql.append(", description=?");
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(", status=? ");
		}
		sql.append(" where id=?");
		return DBUtil.executeUpdate(sql.toString(), new Object[]{level, description, parent_id, status, id});
	}
	public int insertCategory(Map<String, Object> params) {
		String sql = "insert into t_admin_category(description, parent_id, href, level, value, create_time, status) values(?,?,?,?,?, now(),0)";
		return DBUtil.executeUpdate(sql, new Object[]{
				params.get("description"),params.get("parent_id"),params.get("href"),params.get("level"),params.get("value")
		});
	}
	
	
	public List<Map<String, Object>> getSysUserList(Map<String, Object> param) {
		String id = (String) param.get("id");
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		String status = (String) param.get("status");
		
		List<Object> parmList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_admin_user where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and id = ?");
			parmList.add(id);
		}
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and username = ?");
			parmList.add(username);
		}
		if (StringUtils.isNotBlank(password)) {
			sql.append(" and password = ?");
			parmList.add(password);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" and status = ? ");
			parmList.add(status);
		}
		List<Map<String, Object>> list = DBUtil.executeQuery(sql.toString(), parmList.toArray());
		return list;
	}
	public int updateSysUser(Map<String, Object> param) {
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		String status = (String) param.get("status");
		String id = (String) param.get("id");
		List<Object> parmList = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder("update admin_sys_user set ");
		if (StringUtils.isNotBlank(username)) {
			sql.append("username=?");
			parmList.add(username);
		}
		if (StringUtils.isNotBlank(password)) {
			sql.append(",password=?");
			parmList.add(password);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(",status=?");
			parmList.add(status);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" where id=? ");
			parmList.add(id);
		}
		int result = DBUtil.executeUpdate(sql.toString(), parmList.toArray());
		return result;
	}
	public int insertSysUser(Map<String, Object> param) {
		StringBuilder sql = new StringBuilder("insert into admin_sys_user(username, password, create_time, status) values(?,?,now(),0)");
		int result = DBUtil.executeUpdate(sql.toString(), new Object[]{param.get("username"), param.get("password")});
		return result;
	}
	public int updateSysUserPwd(Map<String, Object> param) {
		String id = (String) param.get("id");
		String password = (String) param.get("password");
		String passwordNew = (String) param.get("passwordNew");
		String sql = "update t_admin_user set password=? where id=? and password=?";
		int result = DBUtil.executeUpdate(sql, new Object[]{passwordNew, id, password});
		return result;
	}
	
	
	public List<Map<String, Object>> getUserList(Map<String, Object> param) {
		String id = (String) param.get("id");
		String username = (String) param.get("username");
		String phone = (String) param.get("phone");
		String status = (String) param.get("status");
		List<Object> parmList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_user where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and id = ?");
			parmList.add(id);
		}
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and username = ?");
			parmList.add(username);
		}
		if (StringUtils.isNotBlank(username)) {
			sql.append(" and phone = ?");
			parmList.add(phone);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" and status = ? ");
			parmList.add(status);
		}
		List<Map<String, Object>> list = DBUtil.executeQuery(sql.toString(), parmList.toArray());
		return list;
	}
	public int updareUser(Map<String, Object> param) {
		return -1;
	}
	public int insertUser(Map<String, Object> param) {
		return -1;
	}

}
