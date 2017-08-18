package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WeixinUtil {

	/***************************** 微信开放平台登录 begin *****************************/
	
	private static final Log logger = LogFactory.getLog(WeixinUtil.class);
	
	/**
	 * 授权跳转地址
	 */
	public static String getAuthorizationUrlJump(String redirect_uri, String state) {
		if (StringUtils.isNotBlank(redirect_uri)) {
			try {
				redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("------------WeixinUtil.getAuthorizationCode() URLEncoder失败:" + redirect_uri, e);
			}
		}
		String requestUrl = "https://open.weixin.qq.com/connect/qrconnect?";
		requestUrl += "appid=" + Constant.weixin_AppID + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_login&state=" + state + "#wechat_redirect";
		return requestUrl;
	}
	
	/**
	 * 获取Access Token / openid / unionid
	 */
	public static JSONObject getAccessToken4login(String code, String redirect_uri) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";
		requestUrl += "appid=" + Constant.weixin_AppID + "&secret=" + Constant.weixin_AppSecret + "&code=" + code + "&grant_type=authorization_code";
		String resultStr = HttpConnectionUtil.httpGet(requestUrl);
		if (StringUtils.isNotBlank(resultStr) && resultStr.contains("access_token") && resultStr.contains("openid") && resultStr.contains("unionid")) {
			JSONObject jsonObj = JSONObject.fromObject(resultStr);
			return jsonObj;
		} else if (StringUtils.isNotBlank(resultStr)) {
			logger.error("------------WeixinUtil.getAccessToken4login error--backstr:" + resultStr);
		}
		return null;
	}
	
	/**
	 * 获取微信用户信息
	 */
	public static JSONObject getUserinfo(String accessToken, String openId) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
			return null;
		}
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?";
		requestUrl += "access_token=" + accessToken + "&openid=" + openId;
		String resultStr = HttpConnectionUtil.httpGet(requestUrl);
		JSONObject jsonObj = JSONObject.fromObject(resultStr);
		return jsonObj;
	}
	
	/**************************** 微信开放平台登录 end ***************************/
}
