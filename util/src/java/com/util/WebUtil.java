package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.util.StatusEnum.Operation;
import com.util.StatusEnum.UserRole;


@SuppressWarnings({ "unchecked", "serial" })
public class WebUtil {
	
	// 获取scheme
	public static String getScheme(HttpServletRequest request) {
		return request.getScheme();
	}
	
	public static boolean isHttp(HttpServletRequest request) {
		return "http".equals(getScheme(request));
	}
	
	public static boolean isHttps(HttpServletRequest request) {
		return "https".equals(getScheme(request));
	}
	
	// 网站域名
	public static String getDomain(HttpServletRequest request) {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		String domain = scheme + "://" + serverName + "/";
		return domain;
	}
	
	public static boolean isLocal() {
		boolean isLocal = false;
		if (StringUtils.isBlank(Constant.STATIC_PREFIX)) {
			isLocal = true;
		}
		return isLocal;
	}
	
	// 客户端IP
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		}
		if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	// 页面传递的操作类型
	public static Enum<?> getRequestOperation(HttpServletRequest request) {
		String operation = request.getParameter("operation");
		if (StringUtils.isBlank(operation)) {
			operation = "select";
		}
		Operation opEnum = Enum.valueOf(Operation.class, operation);
		if (opEnum == Operation.select 
				|| opEnum == Operation.toInsert 
				|| opEnum == Operation.insert 
				|| opEnum == Operation.toUpdate 
				|| opEnum == Operation.update 
				|| opEnum == Operation.delete) {
			return opEnum;
		} else {
			return Operation.select;
		}
	}
	
	// 全局错误信息捕获
	public static String getError(HttpServletRequest request) {
		
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");//错误状态码
		if(throwable==null && status_code==null) {
			return null;
		}
		if(!(status_code!=null && status_code>=HttpServletResponse.SC_BAD_REQUEST && status_code<=HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED)) {
			return null;
		}
		
		String split = "\r\n";
		String exception_type = (String) request.getAttribute("javax.servlet.error.exception_type");//错误类型
		String request_uri = (String) request.getAttribute("javax.servlet.error.request_uri");//访问路径
		String message = (String) request.getAttribute("javax.servlet.error.message");//错误消息
		if(request_uri==null) {
			request_uri = "Unknown";
		}
		
		return new StringBuilder(split)
				.append(status_code).append(split)
				.append(exception_type).append(split)
				.append(request_uri).append(split)
				.append(message).append(split)
				.toString();
	}
	
	public static Map getParameterMap(HttpServletRequest request) {
		return getParameterMap(request, (String[]) null);
	}
	
	public static Map getParameterMap(HttpServletRequest request, String...strArr){
		Map params = new HashMap();
		if(strArr==null || strArr.length==0){
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				if(name!=null && name.length()>0 && !name.contains(".")){
					params.put(name, request.getParameter(name));
				}
			}
		} else {
			for(String name : strArr){
				params.put(name, request.getParameter(name));
			}
		}
		return params;
	}
	
	// 输出json
	public static void write(HttpServletResponse response, String data) {
		response.setCharacterEncoding(Constant.charset);
		try {
			PrintWriter pw = response.getWriter();
			pw.println(data);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 输出map
	public static void write(HttpServletResponse response, Map<String, Object> result) {
		String jsonStr = JSON.toJSONString(result);
		write(response, jsonStr);
	}
	
	// 验证adminUserSession过期
	public static boolean checkAdminUserSession(HttpServletRequest request) {
		Map<String, Object> adminUser = (Map<String, Object>) request.getSession().getAttribute("adminUserinfo");
		if (adminUser == null) {
			return false;
		}
		return true;
	}
	
	// 判断是否为移动端
	public static boolean isMobile(HttpServletRequest request) {
		return (isPhone(request) || isPad(request));
	}
	
	// 判断是否为手机端
	public static boolean isPhone(HttpServletRequest request) {
		boolean isPhone = false;
		String[] mobileAgents = { "Android", "iPhone", "iPod", "Windows Phone", "MQQBrowser", "micromessenger" };
		String userAgent = request.getHeader("User-Agent");
		if (userAgent != null) {
			for (String mobileAgent : mobileAgents) {
				if (userAgent.toLowerCase().contains(mobileAgent.toLowerCase())) {
					isPhone = true;
					break;
				}
			}
		}
		return isPhone;
	}
	
	// 判断是否为pad端
	public static boolean isPad(HttpServletRequest request) {
		boolean isPad = false;
		String[] mobileAgents = { "iPad" };
		String userAgent = request.getHeader("User-Agent");
		if (userAgent != null) {
			for (String mobileAgent : mobileAgents) {
				if (userAgent.toLowerCase().contains(mobileAgent.toLowerCase())) {
					isPad = true;
					break;
				}
			}
		}
		return isPad;
	}
	
	// 获取容器中的bean实例
	public static Object getBean(ServletContext context, String springBeanId) {
		ApplicationContext applicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		Object bean = (Object) applicationContext.getBean(springBeanId);
		return bean;
	}
	
	// 用户是否登录
	public static boolean isLogin(HttpServletRequest request, StatusEnum.UserRole userRole) {
		boolean isLogin = true;
		Map<String, Object> userinfo = null;
		if (userRole == UserRole.web) {
			userinfo = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.key_userinfo);
		} else if (userRole == UserRole.admin) {
			userinfo = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.key_admin_userinfo);
		}
		if (MapUtils.isEmpty(userinfo)) {
			isLogin = false;
		}
		return isLogin;
	}
	
	// 用户非登录状态跳转至首页
	public static ModelAndView isLoginSecurityCheck(HttpServletRequest request, StatusEnum.UserRole userRole) throws IOException {
		boolean isLogin = isLogin(request, userRole);
		if (!isLogin) {
			if (userRole == UserRole.web) {
				return new ModelAndView("/user/login.do");
			} else if (userRole == UserRole.admin) {
				return new ModelAndView("/admin/index.do");
			}
		}
		return null;
	}
	
	// 页面查询默认有效数据
	public static final Map<String, Object> initStatusParams = new HashMap<String, Object>();
	static {
		initStatusParams.put("status", "0");
	}
	public static Map<String, Object> getInitstatusparams() {
		return initStatusParams;
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("select".equals(StatusEnum.Operation.select.toString()));
	}
}
