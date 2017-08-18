package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dao.PackageDao;
import com.util.DBUtil;
import com.util.Page;

public class PackageDaoImpl implements PackageDao {

	private static final Logger logger = Logger.getLogger(PackageDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> getPackageList(Map<String, Object> params,
			Page page) {
		String id = (String) params.get("id");
		String package_name = (String) params.get("package_name");
		String package_description = (String) params.get("package_description");
		String type = (String) params.get("type");
		String status = (String) params.get("status");
		String create_time = (String) params.get("create_time");
		String effect_time = (String) params.get("effect_time");
		String expire_time = (String) params.get("expire_time");
		
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_package info where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			String[] ids = id.split(",");
			if (ids.length > 1) {
				sql.append("info.id in ? ");
				paramList.add("(" + id + ")");
			} else {
				sql.append("info.id = ? ");
				paramList.add(id);
			}
		}
		if (StringUtils.isNotBlank(package_name)) {
			sql.append("info.package_name like ? ");
			paramList.add("%" + package_name + "%");
		}
		if (StringUtils.isNotBlank(package_description)) {
			sql.append("info.package_description = ? ");
			paramList.add("%" + package_description + "%");
		}
		if (StringUtils.isNotBlank(type)) {
			sql.append("info.type = ? ");
			paramList.add(type);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append("info.status = ? ");
			paramList.add(status);
		}
		if (StringUtils.isNotBlank(create_time)) {
			sql.append("DATE_FORMAT(info.create_time,'%Y-%m-%d') = ? ");
			paramList.add(create_time);
		}
		if (StringUtils.isNotBlank(effect_time)) {
			sql.append("info.effect_time >= ? ");
			paramList.add(effect_time);
		}
		if (StringUtils.isNotBlank(expire_time)) {
			sql.append("info.expire_time <= ? ");
			paramList.add(expire_time);
		}
		if (page != null) {
			sql.append("limit ?,?");
			paramList.add(page.getOffset());
			paramList.add(page.getPageSize());
		}
		
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sql.toString(), paramList.toArray());
		
		return resultList;
	}

}
