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

public class LoginOutListener extends HttpServlet implements Filter {

	private static final long serialVersionUID = 8799440210028527877L;
	private FilterConfig filterConfig;
	

	@Override
	public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) httpRequest;
		HttpServletResponse response = (HttpServletResponse) httpResponse;
		
		// 过滤
		
		filterChain.doFilter(httpRequest, httpResponse);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
