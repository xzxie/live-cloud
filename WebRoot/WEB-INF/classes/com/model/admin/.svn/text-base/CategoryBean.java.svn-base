package com.model.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

@Deprecated
public class CategoryBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;				// id
	private String description;		// 描述
	private String parentId;		// 父id, 顶级id=0, 多个目录
	private String href;			// 链接
	private String level;			// 层级：1:一级; 2:二级
	private String value;			// 顺序字符,第一个字符:目录的顺序,第二个字符:文件的顺序
	private String createTime;		// 创建时间
	private String status;			// 状态
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static List<CategoryBean> convert(List<Map<String,Object>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<CategoryBean> returnList = new ArrayList<CategoryBean>();
		for (Map<String,Object> map : list) {
			CategoryBean cate = new CategoryBean();
			cate.setId(map.get("id")+"");
			cate.setDescription(map.get("description")+"");
			cate.setParentId(map.get("parent_id")+"");
			cate.setHref(map.get("href")+"");
			cate.setLevel(map.get("level")+"");
			cate.setValue(map.get("value")+"");
			cate.setCreateTime(map.get("create_time")+"");
			cate.setStatus(map.get("status")+"");
			returnList.add(cate);
		}
		return returnList;
	}
}
