package com.model;

import java.io.Serializable;

public class SearchBean implements Serializable {

	private static final long serialVersionUID = -3987736568718266860L;
	
	private String q;	//查询串
	private long p;		//当前页
	private long cr;	//总记录
	private long cp;	//总页
	private int s;		//条件查询方式
	private String sd;	//开始日期
	private String ed;	//结束日期
	
	private int size = 10;//每页显示记录数
	
	public SearchBean(){
		
	}
	
	public SearchBean(String q, long p, int s, String sd, String ed){
		this.q = q;
		this.p = p;
		this.s = s;
		this.sd = sd;
		this.ed = ed;
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	public long getP() {
		return p;
	}

	public void setP(long p) {
		this.p = p;
	}

	public long getCr() {
		return cr;
	}

	public void setCr(long cr) {
		this.cr = cr;
	}

	public long getCp() {
		return cp;
	}

	public void setCp(long cp) {
		this.cp = cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
