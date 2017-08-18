package com.util;

public class Page {

	public int size = 20;		// 页默认大小
	public int total;			// 总记录数
	public int currentPage;		// 当前页数
	public int totalPage;		// 总页数
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		if (size <= 0 || size >= 100) {
			size = this.size;
		}
		this.size = size;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
