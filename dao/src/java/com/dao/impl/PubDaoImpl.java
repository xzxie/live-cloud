package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dao.PubDao;
import com.util.DBUtil;
import com.util.Page;

public class PubDaoImpl implements PubDao {

	private static final Logger logger = Logger.getLogger(PubDaoImpl.class);

	@Override
	public List<Map<String, Object>> getArticleList(Map<String, Object> params, Page page) {

		String id = (String) params.get("id");
		String title = (String) params.get("title");
		String tags = (String) params.get("tags");
		String status = (String) params.get("status");
		
		StringBuilder sbsql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		sbsql.append("select * from t_pub_article where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			sbsql.append("and id = ? ");
			paramList.add(id);
		}
		if (StringUtils.isNotBlank(status)) {
			sbsql.append("and status = ? ");
			paramList.add(status);
		}
		if (StringUtils.isNotBlank(title)) {
			sbsql.append("and title like ? ");
			paramList.add("%" + title + "%");
		}
		if (StringUtils.isNotBlank(tags)) {
			sbsql.append("and tags in ? ");
			paramList.add("(" + tags + ")");
		}
		
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sbsql.toString(), paramList.toArray());
		
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> getTagList(Map<String, Object> params, Page page) {
		String tagId = (String) params.get("tag_id");
		String articleId = (String) params.get("article_id");
		
		StringBuilder sbsql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		sbsql.append("select * from t_pub_article_tag where 1=1 ");
		if (StringUtils.isNotBlank(tagId)) {
			sbsql.append("and id = ? ");
			paramList.add(articleId);
		}
		if (StringUtils.isNotBlank(tagId)) {
			sbsql.append("and article_id = ? ");
			paramList.add(tagId);
		}
		
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sbsql.toString(), paramList.toArray());
		
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> getQuestionList(Map<String, Object> params, Page page) {
		String id = (String) params.get("id");
		String title = (String) params.get("title");
		
		StringBuilder sbsql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		sbsql.append("select * from t_pub_question where 1=1 ");
		if (StringUtils.isNotBlank(id)) {
			sbsql.append("and id = ? ");
			paramList.add(id);
		}
		if (StringUtils.isNotBlank(title)) {
			sbsql.append("and title like ? ");
			paramList.add("%" + title + "%");
		}
		
		List<Map<String, Object>> resultList = DBUtil.executeQuery(sbsql.toString(), paramList.toArray());
		
		return resultList;
	}
	
	@Override
	public boolean insertArticle(Map<String, Object> params) {
		String title = (String) params.get("title");
		String author = (String) params.get("author");
		String content = (String) params.get("content");
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(title);
		paramList.add(author);
		paramList.add(content);
		
		StringBuilder sbsql = new StringBuilder();
		sbsql.append("insert into t_pub_article(title, author, create_time, content, status) ");
		sbsql.append("values(?,?,now(),?,0)");
		
		int result = DBUtil.executeUpdate(sbsql.toString(), paramList.toArray());
		return (result > 0);
	}
	@Override
	public boolean updateArticle(Map<String, Object> params) {
		String id = (String) params.get("id");
		String title = (String) params.get("title");
		String author = (String) params.get("author");
		String content = (String) params.get("content");
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(title);
		paramList.add(author);
		paramList.add(content);
		paramList.add(id);
		
		StringBuilder sbsql = new StringBuilder();
		sbsql.append("update t_pub_article set title = ?, author = ?, content = ? where author = ?");
		
		int result = DBUtil.executeUpdate(sbsql.toString(), paramList.toArray());
		return (result > 0);
	}
	@Override
	public boolean saveQuestion(Map<String, Object> params) {
		return false;
	}
	@Override
	public boolean saveTag(Map<String, Object> params) {
		return false;
	}

}
