package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SolutionController extends MultiActionController {

	
	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("solution/index");
	}
	
	public ModelAndView edu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("solution/edu");
	}
}
