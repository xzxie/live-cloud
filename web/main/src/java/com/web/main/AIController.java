package com.web.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.util.WebUtil;
import com.util.ai.OCRUtil;

public class AIController extends MultiActionController {

	
	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("ai/index");
	}
	
	// 文字识别
	public ModelAndView text(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("ai/text");
	}
	
	public void text_handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String jsonStr = "{}";
		Map<String, Object> result = new HashMap<String, Object>();
		String filePath = request.getParameter("filePath");
		if (StringUtils.isNotBlank(filePath)) {
			jsonStr = OCRUtil.handleRequest_general_basic(filePath.trim());
		}
		if (StringUtils.isNotBlank(jsonStr)) {
			org.json.JSONObject json = new org.json.JSONObject(jsonStr);
			org.json.JSONArray jsonArray = json.getJSONArray("words_result");
		}
		result.put("RESULT", jsonStr);
		WebUtil.write(response, result);
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
