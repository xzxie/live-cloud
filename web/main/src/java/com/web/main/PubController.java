package com.web.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.service.PubService;
import com.util.Page;
import com.util.WebUtil;

/**
 * 开发者社区
 */
public class PubController extends MultiActionController {

	private static final Logger logger = Logger.getLogger(PubController.class);
	private PubService pubService;
	
	// 首页(文章列表)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return article_list(request, response);
	}
	
	// 文章(列表)
	public ModelAndView article_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("cur", "index");
		
		Page page = new Page();
		Map<String, Object> params = WebUtil.getInitstatusparams();
		List<Map<String, Object>> articleList = pubService.getArticleList(params, page);
		
		request.setAttribute("articleList", articleList);
		return new ModelAndView("pub/index");
	}
	
	// 文章(详细)
	public ModelAndView article_detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("cur", "index");
		return new ModelAndView("pub/article");
	}
	
	// 文章(发布)
	public ModelAndView article_post(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("cur", "index");
		
		String operate = request.getParameter("operate");
		
		if (StringUtils.isBlank(operate)) {
			return new ModelAndView("pub/article-post");
		}
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Map<String, Object> userinfo = (Map<String, Object>) request.getSession().getAttribute("userinfo");
		String author = userinfo.get("id") + "";
		
		Map<String, Object> ret = new HashMap<String, Object>();
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
			ret.put("RESULT", "标题和内容不能为空.");
			WebUtil.write(response, ret);
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("title", title);
		params.put("content", content);
		params.put("author", author);
		
		boolean result = pubService.saveArticle(params);
		
		ret.put("RESULT", result);
		WebUtil.write(response, ret);
		return null;
	}
	
	
	// 问答(列表)
	public ModelAndView question_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("cur", "question");
		
		return new ModelAndView("pub/question");
	}
	
	// 问题(详细)
	public ModelAndView question_detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("cur", "question");
		return new ModelAndView("pub/qa");
	}
	
	// 问题(发布)
	public ModelAndView question_post(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("cur", "question");
		return new ModelAndView("pub/qa_post");
	}
	
	// 问题回答(发布)
	public ModelAndView anwser_post(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("cur", "question");
		return new ModelAndView("pub/qa_post");
	}
	
	
	// 关于我们
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("cur", "about");
		
		return new ModelAndView("pub/about");
	}
	
	// 技术沙龙
	public ModelAndView techsalon(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("cur", "techsalon");
		
		return new ModelAndView("pub/techsalon");
	}
	
	
	
	// 搜索
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("cur", "index");
		
		String keyword = request.getParameter("keyword");
		if (StringUtils.isNotBlank(keyword)) {
			
		}
		
		return new ModelAndView("pub/search");
	}

	
	
	
	
	
	
	// getter/setter
	public PubService getPubService() {
		return pubService;
	}

	public void setPubService(PubService pubService) {
		this.pubService = pubService;
	}
	
}
