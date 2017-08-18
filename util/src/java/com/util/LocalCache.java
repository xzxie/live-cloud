package com.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class LocalCache {

	
	// 后台一级目录(二级目录异步加载)
	public static List<Map<String, Object>> getCategoryList(Map<String, Object> params) {
		String level = (String) params.get("level");
		String status = (String) params.get("status");
		
		StringBuilder sql = new StringBuilder("select * from t_admin_category info where 1=1 ");
		if (StringUtils.isNotBlank(level)) {
			sql.append(" and info.level =").append(level);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" and info.status =").append(status);
		}
		sql.append(" order by value");
		List<Map<String, Object>> cateList = DBUtil.executeQuery(sql.toString(), null);
		return cateList;
	}
	
	// 当前目录
	public static String getCategoryForCurrent(String file_id) {
		if (StringUtils.isBlank(file_id)) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select dir.id dirId, dir.description dirDesc, file.id fileId, file.description fileDesc from t_admin_category dir, t_admin_category file ");
		sql.append("where file.parent_id = dir.id and file.id=?");
		List<Map<String, Object>> result = DBUtil.executeQuery(sql.toString(), new Object[]{ file_id });
		String json = "";
		for (Map<String, Object> map : result) {
			json = JSON.toJSONString(map);
		}
		return json;
	}
	
	
	public static void main(String[] args) {
		
	}
}
