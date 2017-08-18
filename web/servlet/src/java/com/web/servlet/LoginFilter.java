package com.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;
	private FilterConfig filterConfig;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		try{
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			long dateTime = System.currentTimeMillis();
			httpRequest.setAttribute("dateTime",dateTime);
			
			/*ApplicationContext applicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
			UserService userService = (UserService) applicationContext.getBean("userService");
			
			String url = httpRequest.getScheme()+"://"+httpRequest.getServerName();*/
			
			
			filterChain.doFilter(httpRequest, httpResponse);
		}catch (Exception ex) {
			ex.printStackTrace();
			filterConfig.getServletContext().log(ex.getMessage());
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
