package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.service.UserService;

/**
 * 首页
 */
public class IndexController extends MultiActionController {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	private UserService userService;
	
	
	public ModelAndView index(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("index/index");
	}

	
	
	/**
	 * getter/setter
	 */
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
