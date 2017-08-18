package com.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dao.PackageDao;
import com.service.PackageService;
import com.util.Page;

public class PackageServiceImpl implements PackageService {

	private static final Logger logger = Logger.getLogger(PackageServiceImpl.class);
	
	private PackageDao packageDao;
	
	@Override
	public List<Map<String, Object>> getPackage4Index(
			Map<String, Object> params, Page page) {
		List<Map<String, Object>> packageList = packageDao.getPackageList(params, page);
		return packageList;
	}

	
	
	// getter/setter
	public PackageDao getPackageDao() {
		return packageDao;
	}

	public void setPackageDao(PackageDao packageDao) {
		this.packageDao = packageDao;
	}

	
}
