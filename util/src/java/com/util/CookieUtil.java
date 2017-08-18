package com.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	
	private HttpServletRequest request;
    private HttpServletResponse response;
    private int expire;//设置cookie经过多长秒后被删除
	
	public static final String domain = ".xiaozhang.com";
	public static final String domain_www = "www.xiexiaozhang.com";
	
	public static final Integer expire_day = 24*60*60;
	public static final Integer expire_month = 30*expire_day;
	public static final Integer expire_year = 12*expire_month;
	public static final Integer expire_when_explore_close = -1;//浏览器关闭时自动删除
    public static final Integer expire_when_immediately = 0;//立即删除
	
	public static final String key_username = "username";
	public static final String key_index_animal = "index-animal";
	public static final String key_time_id = "time_id";
	public static final String key_xiexiaozhang_session_id = "session_id";
	public static final String key_cookie_id = "cookie_id";
	
	
	
	
	
	
	
	public static void addCookie(HttpServletResponse response,
			String name, String value, int expire) {
        Cookie cookies = new Cookie(name, value);
        cookies.setPath("/");
        //cookies.setMaxAge(-1);//设置cookie经过多长秒后被删除。如果0，就说明立即删除。如果是负数就表明当浏览器关闭时自动删除。
        cookies.setMaxAge(expire);
        response.addCookie(cookies);
    }
	
	public static void addDomainCookie(HttpServletResponse response,
			String name, String value, int expire) {
		Cookie cookies = new Cookie(name, value);
    	cookies.setDomain(domain);
    	cookies.setPath("/");
    	cookies.setMaxAge(expire);
    	response.addCookie(cookies);
	}
	
	public static String getCookieValue(HttpServletRequest request, 
			String cookieName) {
        if (cookieName != null) {
            Cookie cookie = getCookie(request, cookieName);
            if(cookie!=null){
                return cookie.getValue();
            }
        }
        return "";
    }
	
	public static boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, 
			String cookieName) {
		
		if (cookieName != null) {
            Cookie cookie = getCookie(request, cookieName);
            if(cookie!=null){
                cookie.setMaxAge(0);//如果0，就说明立即删除
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
	}
	
	public static boolean deleteDomainCookie(HttpServletRequest request, HttpServletResponse response, 
			String cookieName, String domain) {
		
		if (cookieName != null) {
    		Cookie cookie = getCookie(request, cookieName);
    		if(cookie!=null){
    			cookie.setMaxAge(0);//如果0，就说明立即删除
    			cookie.setDomain(CookieUtil.domain);
    			cookie.setPath("/");
    			response.addCookie(cookie);
    			return true;
    		}
    	}
    	return false;
	}
	
	public static Cookie getCookie(HttpServletRequest request, 
			String cookieName) {
		
		Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static Cookie getDomainCookie(HttpServletRequest request, 
			String cookieName) {
		
		Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        try {
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	
}
