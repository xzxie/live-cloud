package com.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AIController extends MultiActionController {

	
	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/index");
	}
	
	// 文字识别
	public ModelAndView text(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		return new ModelAndView("ai/text");
	}
	
	// 图像识别
	public ModelAndView image(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/image");
	}
	
	// 人脸识别
	public ModelAndView face(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/face");
	}
	
	// 语音识别
	public ModelAndView voice(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		return new ModelAndView("ai/voice");
	}
	
	// 推荐引擎
	public ModelAndView recommend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/recommend");
	}
	
	// 机器学习
	public ModelAndView machine(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/machine");
	}
}
