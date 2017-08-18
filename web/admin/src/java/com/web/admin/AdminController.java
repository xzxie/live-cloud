package com.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.service.AdminService;
import com.service.UserService;
import com.util.StatusEnum;
import com.util.WebUtil;
import com.util.StatusEnum.Operation;
import com.util.StatusEnum.UserRole;

public class AdminController extends MultiActionController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	
	public AdminService adminService;
	public UserService userService;
	
	
	// 后台首页
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		boolean isLogin = WebUtil.isLogin(request, UserRole.admin);
		if (isLogin) {
			return new ModelAndView("admin/home");
		}
		return new ModelAndView("admin/index");
	}

	// 后台登录
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		boolean isLogin = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("password", password);
		
		if (WebUtil.isLogin(request, UserRole.admin)) {
			isLogin = true;
		} else {
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				isLogin = false;
				request.setAttribute("RESULT", "用户名密码不能为空.");
			} else {
				isLogin = adminService.login(param);
				if (!isLogin) {
					request.setAttribute("RESULT", "登录失败.");
				}
			}
		}
		
		if (isLogin) {
			//设置session
			Map<String, Object> adminUserinfo = adminService.getSysUserList(param).get(0);
			request.getSession().setAttribute("adminUserinfo", adminUserinfo);
			
			statistics(request);
			return new ModelAndView("admin/home");
		}
		return new ModelAndView("admin/index");
	}
	
	// 全站统计数据
	private void statistics(HttpServletRequest request) {
		// 统计用户注册量
		int regCountDay = 0;
		int regCountWeek = 0;
		int regCountTwoWeek = 0;
		int regCountMonth = 0;
		// 统计用户登录量
		int loginCountDay = 0;
		int loginCountWeek = 0;
		int loginCountTwoWeek = 0;
		int loginCountMonth = 0;
		// 统计套餐购买量
		int packageCountDay = 0;
		int packageCountWeek = 0;
		int packageCountTwoWeek = 0;
		int packageCountMonth = 0;
		
		Map<String, Object> statistics = new HashMap<String, Object>();
		
		statistics.put("regCountDay", regCountDay);
		statistics.put("regCountWeek", regCountWeek);
		statistics.put("regCountTwoWeek", regCountTwoWeek);
		statistics.put("regCountMonth", regCountMonth);
		
		statistics.put("loginCountDay", loginCountDay);
		statistics.put("loginCountWeek", loginCountWeek);
		statistics.put("loginCountTwoWeek", loginCountTwoWeek);
		statistics.put("loginCountMonth", loginCountMonth);
		
		statistics.put("packageCountDay", packageCountDay);
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
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		Enum<?> operation = WebUtil.getRequestOperation(request);
		Map<String, Object> params = null;
		// 查询
		if (operation == Operation.select) {
			params = new HashMap<String, Object>();
			params.put("status", "0");
			params.put("level", "1,2");
			List<Map<String, Object>> cateList = adminService.getCategoryList(params);
			request.setAttribute("cateList", cateList);
		// 新增
		} else if (operation == Operation.insert) {
			params = WebUtil.getParameterMap(request);
			adminService.insertCategory(params);
		// 修改
		} else if (operation == Operation.update) {
			params = WebUtil.getParameterMap(request);
			adminService.updateCategory(params);
		// 删除
		} else if (operation == Operation.delete) {
			params = new HashMap<String, Object>();
			params.put("id", request.getParameter("id"));
			params.put("status", "0");
			adminService.updateCategory(params);
		}
		
		return new ModelAndView("admin/category");
	}
	
	// 异步获取子目录信息
	public void subCategory(HttpServletRequest request,
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
	}
	
	// 系统用户管理
	public ModelAndView sysUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		Enum<?> operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = null;
		if (operation == Operation.select) {
			param = new HashMap<String, Object>();
			List<Map<String, Object>> list = adminService.getSysUserList(param);
			request.setAttribute("list", list);
		} else if (operation == Operation.insert) {
			
		} else if (operation == Operation.update) {
			
		} else if (operation == Operation.delete) {
			
		}
		return new ModelAndView("admin/sys-user");
	}
	
	// 修改当前登录后台账号密码
	public ModelAndView sysUserPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		
		Enum<?> operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = WebUtil.getParameterMap(request);
		if (operation == Operation.update) {
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
	@SuppressWarnings("unchecked")
	public ModelAndView user(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		int result = 0;
		Enum<?> operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = WebUtil.getParameterMap(request);
		if (operation == Operation.select) {
			List<Map<String, Object>> list = adminService.getUserList(param);
			request.setAttribute("list", list);
		} else if (operation == Operation.insert) {
			result = adminService.insertUser(param);
		} else if (operation == Operation.update) {
			result = adminService.insertUser(param);
		} else if (operation == Operation.delete) {
			param.put("status", "1");
			result = adminService.updareUser(param);
		}
		request.setAttribute("result", result);
		
		return new ModelAndView("/admin/user");
	}
	
	// 产品管理
	public ModelAndView product(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		return new ModelAndView("admin/product");
	}
	
	// 套餐管理
	public ModelAndView packages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		int result = 0;
		Enum<?> operation = WebUtil.getRequestOperation(request);
		Map<String, Object> param = WebUtil.getParameterMap(request);
		
		// 查询
		if (operation == Operation.select) {
			List<Map<String, Object>> list = adminService.getPackageList(param);
			request.setAttribute("list", list);
		// 去新增
		} else if (operation == Operation.toInsert) {
			return new ModelAndView("admin/package-new");
		// 新增
		} else if (operation == Operation.insert) {
			result = adminService.insertPackage(param);
		// 去修改
		} else if (operation == Operation.toUpdate) {
			return new ModelAndView("admin/package-update");
		// 修改
		} else if (operation == Operation.update) {
			result = adminService.insertPackage(param);
		// 删除
		} else if (operation == Operation.delete) {
			param.put("status", "1");
			result = adminService.updarePackage(param);
		}
		
		return new ModelAndView("admin/package");
	}
	
	// 订单管理
	public ModelAndView order(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 登录检测
		ModelAndView view = WebUtil.isLoginSecurityCheck(request, StatusEnum.UserRole.admin);
		if (view != null) {
			return view;
		}
		
		return new ModelAndView("admin/order");
	}
	
	
	// 综合统计
	public ModelAndView statistics(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/statistics");
	}
	
	// 用户统计
	public ModelAndView statistics_user(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/statistics_user");
	}
	
	// 套餐统计
	public ModelAndView statistics_packages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/statistics_packages");
	}
	
	// 订单统计
	public ModelAndView statistics_order(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("admin/statistics_order");
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
