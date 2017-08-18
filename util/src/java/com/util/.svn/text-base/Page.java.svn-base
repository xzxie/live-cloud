package com.util;

public class Page {

	public int record;			// 总记录数
	public int pageSize;		// 页大小
	public int totalPage;		// 总页数
	public int currentPage;		// 当前页数
	public int offset;			// 偏移量
	
	
	public Page() {
		pageSize = 20;
		currentPage = 1;
		offset = 0;
	}
	
	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0 || pageSize >= 100) {
			pageSize = this.pageSize;
		}
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		int totalPage = (record % pageSize == 0) ? 
				(record / pageSize) : (record / pageSize) + 1;
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		if (currentPage < 0 || currentPage > totalPage) {
			currentPage = 0;
		}
		this.currentPage = currentPage;
	}

	public int getOffset() {
		return (this.currentPage - 1) / this.pageSize;
	}
	
}
