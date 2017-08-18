package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * 第三方平台对接
 */
public class PlatformController extends MultiActionController {

	
	// 中国网建
	public ModelAndView webchinse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView();
	}
}
