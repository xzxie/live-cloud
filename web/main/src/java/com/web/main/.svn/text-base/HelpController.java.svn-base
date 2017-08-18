package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.service.HelpService;

/**
 * 帮助关于
 */
public class HelpController extends MultiActionController {

	private HelpService helpService;

	// 首页
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String page = "index";
		String current = request.getParameter("t");
		if ("1".equals(current)) {
			page = "about-us";
		} else if ("2".equals(current)) {
			page = "api";
		} else if ("3".equals(current)) {
			page = "advantage";
		} else if ("4".equals(current)) {
			page = "company";
		} else if ("5".equals(current)) {
			page = "media";
		} else if ("6".equals(current)) {
			page = "jion-us";
		} else if ("7".equals(current)) {
			page = "contact-us";
		} else if ("8".equals(current)) {
			page = "index";
		} else if ("9".equals(current)) {
			page = "fre-question";
		} else if ("10".equals(current)) {
			page = "disclaimer";
		} else if ("11".equals(current)) {
			page = "privacy";
		} else if ("12".equals(current)) {
			page = "rights";
		}
		request.setAttribute("cur", current);
		
		//page = "help/" + page;
		page = "help/index";
		return new ModelAndView( page );
	}
	
	// 搜索
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String key = request.getParameter("key");
		
		request.setAttribute("key", key);
		
		return new ModelAndView("help/index");
	}
	
	
	
	public HelpService getHelpService() {
		return helpService;
	}

	public void setHelpService(HelpService helpService) {
		this.helpService = helpService;
	}
}
