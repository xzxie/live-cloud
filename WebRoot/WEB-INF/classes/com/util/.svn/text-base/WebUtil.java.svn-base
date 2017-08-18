package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class WebUtil {
	
	/**
	 * 页面传递的操作类型
	 */
	public static String getRequestOperation(HttpServletRequest request) {
		String operation = request.getParameter("operation");
		if (StringUtils.isBlank(operation)) {
			return StatusEnum.Operation.select.toString();
		} else if (operation.equals(StatusEnum.Operation.select.toString())) {
			return StatusEnum.Operation.select.toString();
		} else if (operation.equals(StatusEnum.Operation.insert.toString())) {
			return StatusEnum.Operation.insert.toString();
		} else if (operation.equals(StatusEnum.Operation.update.toString())) {
			return StatusEnum.Operation.update.toString();
		} else if (operation.equals(StatusEnum.Operation.delete.toString())) {
			return StatusEnum.Operation.delete.toString();
		}
		return StatusEnum.Operation.select.toString();
	}
	
	/**
	 * 全局错误信息捕获
	 */
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
	
	@SuppressWarnings("rawtypes")
	public static Map getParameterMap(HttpServletRequest request) {
		return getParameterMap(request, (String[]) null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	// 验证adminUserSession过期
	public static boolean checkAdminUserSession(HttpServletRequest request) {
		Map<String, Object> adminUser = (Map<String, Object>) request.getSession().getAttribute("adminUserinfo");
		if (adminUser == null) {
			return false;
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("select".equals(StatusEnum.Operation.select.toString()));
	}
}
