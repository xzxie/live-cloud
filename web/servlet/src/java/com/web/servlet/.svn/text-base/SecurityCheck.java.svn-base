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

import com.util.Constant;
import com.util.DateUtil;
import com.util.LocalCache;
import com.util.WebUtil;

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
		HttpSession session = httpRequest.getSession();
		
		// 上班时段不可访问
		boolean isWorkTime = false; //DateUtil.isWorkTime();
		if (isWorkTime) {
			request.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
			return;
		}
		
		// 关闭网站
		if (!"open".equals(Constant.site_open_close_switch) && !"/config.jsp".equals(uri)) {
			request.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
			return;
		}
		
		// scheme
		if (WebUtil.isHttp(httpRequest)) {
			session.setAttribute("scheme", "http");
		} else if (WebUtil.isHttps(httpRequest)) {
			session.setAttribute("scheme", "https");
		}
		
		// 手机端
		if (WebUtil.isPhone(httpRequest)) {
			logger.error("=========================  mobile 访问  ==========================");
			if (uri.equals("/")) {
				request.getRequestDispatcher("/jsp/m/index.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}
		
		// 管理员后台
		if (StringUtils.isNotBlank(uri) && uri.startsWith("/admin/")) {
			putCateLevel1List_to_session(session);
			String file_id = request.getParameter("file_id");
			putCateListCurrentLevel2_to_session(session, file_id);
			checkAdminUserLogin(httpRequest, httpResponse, uri);
		}
		
		// 前台用户个人中心
		/*if (StringUtils.isNotBlank(uri) && uri.startsWith("/user/") && !(uri.equals("/user/reg.do") || uri.equals("/user/login.do") || uri.equals("/user/logout.do"))) {
			Map<String, Object> userinfo = (Map<String, Object>) session.getAttribute("userinfo");
			if (MapUtils.isEmpty(userinfo)) {
				request.getRequestDispatcher("/user/logout.do").forward(request, response);
				return;
			}
		}*/
		
		filterChain.doFilter(httpRequest, httpResponse);
	}
	
	// 初始化加载后台一级目录
	private void putCateLevel1List_to_session(HttpSession session) {
		if (session.getAttribute("adminCateList") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "0");
			param.put("level", "1");
			List<Map<String, Object>> adminCateList = LocalCache.getCategoryList(param);
			session.setAttribute("adminCateList", adminCateList);
		}
	}
	
	// 当前目录
	private void putCateListCurrentLevel2_to_session(HttpSession session, String file_id) {
		session.setAttribute("file_id", file_id);
		String jsonCategoryCurrent = LocalCache.getCategoryForCurrent(file_id);
		session.setAttribute("jsonCategoryCurrent", jsonCategoryCurrent);
	}
	
	// 检测是后台用户登录状态
	private void checkAdminUserLogin(HttpServletRequest httpRequest, HttpServletResponse response, String uri) {
		HttpSession session = httpRequest.getSession();
		if (!"/admin/index.do".equals(uri) && !"/admin/login.do".equals(uri) && !"/admin/logout.do".equals(uri)) {
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
