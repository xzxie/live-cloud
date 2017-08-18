package com.web.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.service.PackageService;
import com.service.UserService;
import com.util.CookieUtil;

/**
 * 首页
 */
public class IndexController extends MultiActionController {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	private UserService userService;
	private PackageService packageService;
	
	
	public ModelAndView index(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		this.handleAnimalCookie(request, response);
		return new ModelAndView("index/index");
	}

	// 首页字符动态动画显示控制：首次加载显示
	private void handleAnimalCookie(HttpServletRequest httpRequest, HttpServletResponse response) {
		String cookieValue = CookieUtil.getCookieValue(httpRequest, CookieUtil.key_index_animal);
		if (StringUtils.isBlank(cookieValue)) {
			CookieUtil.addCookie(response, CookieUtil.key_index_animal, "first-load", CookieUtil.expire_month);
		} else {
			CookieUtil.addCookie(response, CookieUtil.key_index_animal, "has-load", CookieUtil.expire_month);
		}
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

	public PackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
}
