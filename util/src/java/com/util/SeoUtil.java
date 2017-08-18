package com.util;


//站点枚举
enum SiteEnum{ main, blog, book, download, vpn}

public class SeoUtil {

	public static final String domain = "https://www.xiexiaozhang.com";
	public static final String siteName = "云烟阁";
	public static final String description = "云烟阁,为直播而生";
	public static final String keywords = "云烟阁";
	
	
	public static final String getDescription(Enum site) {
		if (site == SiteEnum.blog) {
			return "博客";
		} else if (site == SiteEnum.book) {
			return "图书";
		} else if (site == SiteEnum.download) {
			return "资源下载";
		} else if (site == SiteEnum.vpn) {
			return "vpn";
		}
		return description;
	}
	
	public static final String getkeywords(Enum site) {
		if (site == SiteEnum.blog) {
			return "博客";
		} else if (site == SiteEnum.book) {
			return "图书";
		} else if (site == SiteEnum.download) {
			return "资源下载";
		} else if (site == SiteEnum.vpn) {
			return "vpn";
		}
		return keywords;
	}
	
	
	
	
	public static void main(String[] args) {
		Enum site = SiteEnum.vpn;
		if (site == SiteEnum.main) {
			System.out.println(SiteEnum.main);
		} else if (site == SiteEnum.book) {
			System.out.println(SiteEnum.book);
		} else if (site == SiteEnum.download) {
			System.out.println(SiteEnum.download);
		} else if (site == SiteEnum.vpn) {
			System.out.println(SiteEnum.vpn);
		}
		
		System.out.println(SiteEnum.book.toString().equals("book"));
	}
}
