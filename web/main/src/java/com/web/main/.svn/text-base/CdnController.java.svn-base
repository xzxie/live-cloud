package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.util.Constant;
import com.util.WebUtil;
import com.util.StatusEnum.UserRole;

public class CdnController extends MultiActionController {

	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("cdn/index");
	}
	
	// 申请试用
	public ModelAndView apply(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		boolean isLogin = WebUtil.isLogin(request, UserRole.web);
		if (!isLogin) {
			return new ModelAndView("user/login");
		}
		
		return new ModelAndView("cdn/apply");
	}
}
