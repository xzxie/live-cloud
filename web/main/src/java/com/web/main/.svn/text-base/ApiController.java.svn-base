package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ApiController extends MultiActionController {

	// API文档
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("api/index");
	}
	
	// 开发指南
	public ModelAndView doc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("api/doc");
	}
}
