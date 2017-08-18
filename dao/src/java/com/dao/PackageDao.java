package com.dao;

import java.util.List;
import java.util.Map;

import com.util.Page;

public interface PackageDao {

	public List<Map<String, Object>> getPackageList(Map<String, Object> params, Page page);
}
