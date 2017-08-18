package com.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	// 前台
	public static final String key_userinfo = "userinfo"; 
	
	// 后台
	public static final String key_admin_userinfo = "adminUserinfo";
	
	
	private HttpSession session;
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setAttribute(HttpServletRequest request, String key, Object value) {
		session = request.getSession();
		session.setAttribute(key, value);
	}
	public Object getAttribute(HttpServletRequest request, String key) {
		session = request.getSession();
		return session.getAttribute(key);
	}
	public void removeAttribute(HttpServletRequest request, String key) {
		session = request.getSession();
		session.removeAttribute(key);
	}
	@SuppressWarnings("rawtypes")
	public void removeAttributeAll(HttpServletRequest request) {
		session = request.getSession();
		Enumeration em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
	}
	
	
	// 后台用户信息
	public void setAdminUserinfo(HttpServletRequest request, Object value) {
		this.setAttribute(request, key_admin_userinfo, value);
	}
	public Map<String, Object> getAdminUserinfo(HttpServletRequest request) {
		return (Map<String, Object>) this.getAttribute(request, key_admin_userinfo);
	}
	public void removeAdminUserinfo(HttpServletRequest request, String key) {
		this.removeAttribute(request, key_admin_userinfo);
	}
	
	
}
