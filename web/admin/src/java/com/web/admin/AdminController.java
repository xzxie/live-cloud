package com.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.service.AdminService;
import com.service.UserService;
import com.util.StatusEnum;
import com.util.WebUtil;

public class AdminController extends MultiActionController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	
	public AdminService adminService;
	public UserService userService;
	

	// 登录
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean islogin = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Map<String, Object> adminUserinfo = (Map<String, Object>) session.getAttribute("adminUserinfo");
		if (MapUtils.isNotEmpty(adminUserinfo)) {
			islogin = true;
		} else if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			islogin = false;
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username", username);
			param.put("password", password);
			List<Map<String, Object>> list = adminService.getSysUserList(param);
			if (CollectionUtils.isNotEmpty(list)) {
				adminUserinfo = list.get(0);
				if (MapUtils.isNotEmpty(adminUserinfo)) {
					islogin = true;
				}
			}
		}
		if (islogin) {
			session.setAttribute("adminUserinfo", adminUserinfo);
			statistics(request);
			return new ModelAndView("admin/home");
		}
		return new ModelAndView("admin/index");
	}
	
	// 全站统计数据
	private void statistics(HttpServletRequest request) {
		// 统计用户注册量
		int regCountWeek = 0;
		int regCountTwoWeek = 0;
		int regCountMonth = 0;
		// 统计套餐购买量
		int packageCountWeek = 0;
		int packageCountTwoWeek = 0;
		int packageCountMonth = 0;
		Map<String, Object> statistics = new HashMap<String, Object>();
		statistics.put("regCountWeek", regCountWeek);
		statistics.put("regCountTwoWeek", regCountTwoWeek);
		statistics.put("regCountMonth", regCountMonth);
		statistics.put("packageCountWeek", packageCountWeek);
		statistics.put("packageCountTwoWeek", packageCountTwoWeek);
		statistics.put("packageCountMonth", packageCountMonth);
		request.setAttribute("statistics", statistics);
	}
	
	// 退出
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("adminCateList");
		session.removeAttribute("jsonCategoryCurrent");
		session.removeAttribute("adminUserinfo");
		return new ModelAndView("admin/index");
	}
	
	// 目录管理
	public ModelAndView category(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operation = WebUtil.getRequestOperation(request);
		Map<String, Object> params = null;
		// 查询
		if (operation.equals(StatusEnum.Operation.select.toString())) {
			params = new HashMap<String, Object>();
			params.put("status", "0");
			params.put("level", "1,2");
			List<Map<String, Object>> cateList = adminService.getCategoryList(params);
			request.setAttribute("cateList", cateList);
		// 新增
		} else if (operation.equals(StatusEnum.Operation.insert.toString())) {
			params = WebUtil.getParameterMap(request);
			adminService.insertCategory(params);
		// 修改
		} else if (operation.equals(StatusEnum.Operation.update.toString())) {
			params = WebUtil.getParameterMap(request);
			adminService.updateCategory(params);
		// 删除
		} else if (operation.equals(StatusEnum.Operation.delete.toString())) {
			params = new HashMap<String, Object>();
			params.put("id", request.getParameter("id"));
			params.put("status", "0");
			adminService.updateCategory(params);
		}
		
		return new ModelAndView("admin/category");
	}
	
	// 异步获取子目录信息
	public ModelAndView subCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String parent_id = request.getParameter("parent_id");
		
		Map<String ,Object> params = new HashMap<String, Object>();
		params.put("parent_id", parent_id);
		params.put("status", "0");
		
		List<Map<String, Object>> subCateList = adminService.getCategoryList(params);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("DATA", subCateList);
		String jsonString = JSON.toJSONString(result);
		WebUtil.write(response, jsonString);
		
		return null;
	}
	
	// 系统用户管理
	public ModelAndView sysUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = null;
		if (operation.equals(StatusEnum.Operation.select.toString())) {
			param = new HashMap<String, Object>();
			List<Map<String, Object>> list = adminService.getSysUserList(param);
			request.setAttribute("list", list);
		} else if (operation.equals(StatusEnum.Operation.insert.toString())) {
			
		} else if (operation.equals(StatusEnum.Operation.update.toString())) {
			
		} else if (operation.endsWith(StatusEnum.Operation.delete.toString())) {
			
		}
		return new ModelAndView("admin/sys-user");
	}
	
	// 修改当前登录后台账号密码
	public ModelAndView sysUserPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
		String operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = WebUtil.getParameterMap(request);
		if (operation.equals(StatusEnum.Operation.update.toString())) {
			int result = adminService.updateSysUserPwd(param);
			if (result > 0) {
				HttpSession session = request.getSession();
				List<Map<String, Object>> adminUserinfo = adminService.getSysUserList(param);
				session.setAttribute("adminUserinfo", adminUserinfo.get(0));
				map.put("RESULT", "success");
			} else {
				map.put("RESULT", "fail");
			}
			String jsonString = JSON.toJSONString(map);
			WebUtil.write(response, jsonString);
			return null;
		}
		return new ModelAndView("/admin/sys-user-pwd");
	}
	
	// 用户管理
	public ModelAndView user(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int result = 0;
		String operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = WebUtil.getParameterMap(request);
		if (operation.equals(StatusEnum.Operation.select.toString())) {
			List<Map<String, Object>> list = adminService.getUserList(param);
			request.setAttribute("list", list);
		} else if (operation.equals(StatusEnum.Operation.insert.toString())) {
			result = adminService.insertUser(param);
		} else if (operation.equals(StatusEnum.Operation.update.toString())) {
			result = adminService.insertUser(param);
		} else if (operation.equals(StatusEnum.Operation.delete.toString())) {
			param.put("status", "1");
			result = adminService.updareUser(param);
		}
		request.setAttribute("result", result);
		
		return new ModelAndView("/admin/user");
	}
	
	// 产品管理
	public ModelAndView product(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/product");
	}
	
	// 套餐管理
	public ModelAndView packages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/package");
	}
	
	// 订单管理
	public ModelAndView order(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/order");
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

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
