package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.service.UserService;

/**
 * 用户
 */
public class UserController extends MultiActionController  {

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private UserService userService;

	// 登录
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("user/login");
	}
	
	public ModelAndView login_qq(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("user/login");
	}
	
	public ModelAndView login_phone(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("user/login");
	}
	
	// 退出
	public ModelAndView loginout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("/");
	}
	
	// 注册
	public ModelAndView reg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("user/reg");
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
