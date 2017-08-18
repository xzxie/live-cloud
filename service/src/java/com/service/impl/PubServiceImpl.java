package com.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dao.PubDao;
import com.service.PubService;
import com.util.Page;

public class PubServiceImpl implements PubService {

	private PubDao pubDao;

	

	@Override
	public List<Map<String, Object>> getArticleList(Map<String, Object> params, Page page) {
		return pubDao.getArticleList(params, page);
	}
	
	@Override
	public List<Map<String, Object>> getTagList(Map<String, Object> params, Page page) {
		return pubDao.getTagList(params, page);
	}
	
	@Override
	public List<Map<String, Object>> getQuestionList(Map<String, Object> params, Page page) {
		return pubDao.getQuestionList(params, page);
	}
	
	
	@Override
	public boolean saveArticle(Map<String, Object> params) {
		boolean result = false;
		String id = (String) params.get("id");
		if (StringUtils.isBlank(id)) {
			result = pubDao.insertArticle(params);
		} else {
			result = pubDao.updateArticle(params);
		}
		return result;
	}
	@Override
	public boolean saveQuestion(Map<String, Object> params) {
		return pubDao.saveQuestion(params);
	}
	@Override
	public boolean saveTag(Map<String, Object> params) {
		return pubDao.saveTag(params);
	}
	
	
	
	
	// getter/setter
	public PubDao getPubDao() {
		return pubDao;
	}

	public void setPubDao(PubDao pubDao) {
		this.pubDao = pubDao;
	}
	
}
