package com.web.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.model.User;

import com.service.UserService;
import com.util.JsonUtil;
import com.util.MsmUtil;
import com.util.QQUtil;
import com.util.RegExpUtil;
import com.util.WebUtil;
import com.util.StatusEnum.UserRole;
import com.util.crypto.Md5Util;

/**
 * 用户
 */
public class UserController extends MultiActionController  {

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private static String domain_prefix = "https://www.xiexiaozhang.com";
	private static int ifDebug = 1; // 1:本地  2:测试环境  3:正式环境
	static {
		if (ifDebug == 1) {
			domain_prefix = "http://localhost";
		} else if (ifDebug == 2) {
			domain_prefix = "https://www.xiexiaozhang.com";
		}
	}
	
	
	private UserService userService;

	// 去登录
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("user/login");
	}
	
	// 异步登录
	public void login_ajax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(username)) {
			result.put("RESULT", "用户不能为空");
			WebUtil.write(response, result);
			return;
		}
		if (StringUtils.isBlank(password)) {
			result.put("RESULT", "密码不能为空");
			WebUtil.write(response, result);
			return;
		}
		
		String salt = userService.getSalt(username);
		password = Md5Util.md5(password, salt);
		
		params.put("username", username);
		params.put("password", password);
		int status = userService.login(params);
		if (status == 1) {
			result.put("RESULT", "用户不存在");
			WebUtil.write(response, result);
			return;
		} else if (status == 2) {
			result.put("RESULT", "密码错误");
			WebUtil.write(response, result);
			return;
		} else if (status == 3) {
			result.put("RESULT", "用户失效");
			WebUtil.write(response, result);
			return;
		} else if (status == -1) {
			result.put("RESULT", "用户无法登录");
			WebUtil.write(response, result);
			return;
		}
		
		Map<String, Object> userinfo = userService.getUserInfoList(params, null).get(0);
		request.getSession().setAttribute("userinfo", userinfo);
		result.put("RESULT", "登录成功");
		WebUtil.write(response, result);
	}
	
	// QQ登录
	public ModelAndView login_qq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=utf-8");
		
		boolean isWap = WebUtil.isMobile(request);
		
		String referer = request.getHeader("Referer");
		try {
			referer = URLEncoder.encode(referer, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String redirect_callback = domain_prefix + "/user/login_qq_callback.do" + "?referer=" + referer;
		String redirect = QQUtil.getAuthorizationUrlJump(isWap, redirect_callback, "test");
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// QQ登录回调
	public ModelAndView login_qq_callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			int type = 1;
			String referer = request.getParameter("referer");
			String referer_decode = URLDecoder.decode(referer, "UTF-8");
			
			boolean isWap = WebUtil.isMobile(request);
			String authorization_code = request.getParameter("code");
			String access_token = QQUtil.getAccessToken(isWap, authorization_code, referer);
			String openID = QQUtil.getOpenId(false, access_token);
		
			if (StringUtils.isBlank(openID)) {
				logger.error("login_qq_callback  ---  QQ登录异常.");
				response.sendRedirect(referer_decode);
				return null;
			}
			
			JSONObject qqUserinfo = QQUtil.getUserinfo(access_token, openID);
			String username = "qq_" + qqUserinfo.getString("nikename");
			
			// 通过openID查询是否绑定，决定跳转页面
			Map<String, Object> userinfo = userService.getUserinfoByOpenId(openID, type);
			String hasBinding = (String) userinfo.get("hasBinding");
			
			if ("0".equals(hasBinding)) { //未绑定，跳转至绑定页面
				return new ModelAndView("user/binding");
			} else if ("1".equals(hasBinding)) { //已绑定，登录操作，跳转至个人中心页面
				String userId = (String) userinfo.get("USER_ID");
				String phone = (String) userinfo.get("PHONE");
				String passwordMD5 = (String) userinfo.get("PASSWORD");
				String userType = (String) userinfo.get("USER_TYPE");
				
				Map<String, Object> params = new HashMap<String, Object>();
				int status = userService.login(params);
				if (status == 0) {
					return new ModelAndView("user/login");
				} else {
					response.sendRedirect(referer_decode);
					return null;
				}
			} else { //调用失败
				response.sendRedirect(referer_decode);
				return null;
			}
		} catch(Exception e) {
			logger.error("QQ2.0 loginWithQQCallBack 失败", e);
		}
		
		return null;
	}
		
		
	
	// 微信登录
	public ModelAndView login_weixin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String url = request.getParameter("url");
		if (StringUtils.isNotBlank(url)) {
			String getCodeUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
			request.getRequestDispatcher(url).forward(request, response);
			return null;
		} else {
			String getTokenByCode = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			return new ModelAndView("user/login");
		}
	}
	
	// 微信登录回调
	public ModelAndView login_weixin_callback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return null;
	}
	
	
	// 微博登录
	public ModelAndView login_weibo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 请求跳转获取code
		String url = request.getParameter("url");
		if (StringUtils.isNotBlank(url)) {
			weibo4j.Oauth oauth = new weibo4j.Oauth();
			response.sendRedirect(oauth.authorize("code"));
			return null;
		}
		
		// 回调
		String accesstoken = "";
		String code = request.getParameter("code");
		if (code != null) {
			weibo4j.Oauth oauth = new weibo4j.Oauth();
			weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
			accesstoken = accessToken.getAccessToken();
			if (accesstoken != null) {
				Weibo weibo=new Weibo();
				weibo.setToken(accessToken.getAccessToken());
				Users users=new Users();
				User weiboUser=users.showUserById(accessToken.getUid());
				String weiboUserinfo = weiboUser.getId() + ", " + weiboUser.getGender() + ", " 
						+ weiboUser.getName() + ", " + accesstoken;
				response.getWriter().print(weiboUserinfo);
				logger.error(weiboUserinfo);
				//填充本网站微博账户资料
				//查询本站数据库,设置微博账户为登录状态
				//request.getSession().setAttribute("weiboUserinfo", weiboUser.getId());
				return new ModelAndView("user/home");
			}
		} else {
			logger.error("新浪oauth 认证请求非法！");
		}
		
		return new ModelAndView("user/login");
	}
	
	// 微博登录回调
	public ModelAndView login_weibo_callback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return null;
	}
	
	public void isLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isLogin = WebUtil.isLogin(request, UserRole.web);
		if (isLogin) {
			result.put(JsonUtil.MAP_KEY, "1");
		} else {
			result.put(JsonUtil.MAP_KEY, "0");
		}
		WebUtil.write(response, result);
	}
	
	// 退出
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().removeAttribute("userinfo");
		response.sendRedirect("/app/index.do");
		return null;
	}
	
	// 去注册
	public ModelAndView reg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("user/reg");
	}
	
	// 注册
	public void reg_ajax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		params.put("username", username);
		params.put("password", password);
		
		String validate = "";
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(username)) {
			validate = "账号不能为空";
		}
		boolean isEmail = RegExpUtil.isEmail(username);
		boolean isPhone = RegExpUtil.isEmail(username);
		if (!isEmail || !isPhone) {
			validate = "账号为邮箱或手机号";
		}
		if (StringUtils.isBlank(password)) {
			validate = "密码为空";
		}
		
		if (StringUtils.isNotBlank(validate)) {
			result.put("result", validate);
			WebUtil.write(response, result);
			return;
		}
		
		int ret = userService.reg(params);
		if (ret == -1) {
			result.put("RESULT", "用户名已存在");
		} else if (ret == 0) {
			result.put("RESULT", "注册失败");
		} else if (ret == 1) {
			result.put("RESULT", "注册成功");
		}
		
		result.put("RESULT", ret);
		WebUtil.write(response, result);
	}
	
	// QQ注册
	public ModelAndView reg_qq(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView();
	}
	
	// 微博注册
	public ModelAndView reg_weibo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView();
	}
	
	// 微信注册
	public ModelAndView reg_weixin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView();
	}
	
	// 注册时邮箱验证
	public ModelAndView reg_email_verify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String token = request.getParameter("token");
		if (StringUtils.isBlank(token)) {
			UUID uuid = UUID.randomUUID();
			token = uuid.toString().replace("-", "");
			request.getSession().setAttribute("email_verity_token", token);
		} else {
			String email_verity_token = request.getSession().getAttribute("email_verity_token")+"";
			if (token.equals(email_verity_token)) {
				request.getSession().removeAttribute("email_verity_token");
				response.sendRedirect("/app/index.do");
			}
		}
		
		return new ModelAndView("");
	}
	
	// 注册时短信验证
	public ModelAndView reg_msm_verify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String phone = request.getParameter("phone");
		String text = "123456";
		String code = MsmUtil.sendSMS(phone, text);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("RESULT", "1");
		WebUtil.write(response, result);
		
		return null;
	}
	
	// 个人中心
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return home(request, response);
	}
	
	// 个人中心
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("panel", "main");
		return new ModelAndView("user/home");
	}
	
	// 个人中心 - api/sdk
	public ModelAndView api(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		request.setAttribute("panel", "api-sdk");
		return new ModelAndView("user/api");
	}
	
	// 个人中心  - 数据中心
	public ModelAndView data(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		request.setAttribute("panel", "data");
		return new ModelAndView("user/data");
	}
	
	// 个人中心  - 网站管理
	public ModelAndView website(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		request.setAttribute("panel", "website");
		return new ModelAndView("user/website");
	}
	
	// 个人中心  - 升级/付费
	public ModelAndView vip(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("panel", "vip");
		
		
		
		return new ModelAndView("user/vip");
	}
	
	// 个人中心  - 策略/设置
	public ModelAndView setting(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		request.setAttribute("panel", "setting");
		return new ModelAndView("user/setting");
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
	
}
