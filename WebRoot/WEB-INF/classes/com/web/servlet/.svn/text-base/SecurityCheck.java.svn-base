package com.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.util.LocalCache;

public class SecurityCheck extends HttpServlet implements Filter {

	private static final long serialVersionUID = -9086795673916739902L;
	
	private FilterConfig config;
	private static final Log logger = LogFactory.getLog(SecurityCheck.class);
	
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		if (StringUtils.isNotBlank(uri) && uri.startsWith("/admin/")) {
			HttpSession session = httpRequest.getSession();
			setCateList(session);
			String file_id = request.getParameter("file_id");
			setCateListCurrent(session, file_id);
			checkAdminUser(httpRequest, httpResponse, uri);
		}
		
		filterChain.doFilter(httpRequest, httpResponse);
	}
	
	// 初始化加载后台一级目录
	private void setCateList(HttpSession session) {
		if (session.getAttribute("adminCateList") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "0");
			param.put("level", "1");
			List<Map<String, Object>> adminCateList = LocalCache.getCategoryList(param);
			session.setAttribute("adminCateList", adminCateList);
		}
	}
	
	// 当前目录
	private void setCateListCurrent(HttpSession session, String file_id) {
		session.setAttribute("file_id", file_id);
		String jsonCategoryCurrent = LocalCache.getCategoryForCurrent(file_id);
		session.setAttribute("jsonCategoryCurrent", jsonCategoryCurrent);
	}
	
	// 检测是后台用户登录状态
	private void checkAdminUser(HttpServletRequest httpRequest, HttpServletResponse response, String uri) {
		HttpSession session = httpRequest.getSession();
		if (!"/admin/index.do".equals(uri)) {
			Map<String, Object> adminUserinfo = (Map<String, Object>) session.getAttribute("adminUserinfo");
			if (adminUserinfo == null) {
				try {
					response.sendRedirect("/admin/index.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
}
