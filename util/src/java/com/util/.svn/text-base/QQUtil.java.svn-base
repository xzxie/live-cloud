package com.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QQUtil {

	private static final Log logger = LogFactory.getLog(QQUtil.class);
	
	/***************************** QQ互联登录 begin *****************************/
	
	/**
	 * 授权跳转地址
	 */
	public static String getAuthorizationUrlJump(boolean isWap, String redirect_uri, String state) {
		if (StringUtils.isNotBlank(redirect_uri)) {
			try {
				redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("------------QQ2.0----getAuthorizationCode URLEncoder失败:" + redirect_uri, e);
			}
		}
		String display = isWap ? "mobile" : "pc";
		String requestUrl = "https://graph.qq.com/oauth2.0/authorize?";
		requestUrl += "response_type=code&client_id=" + Constant.qq_app_id + "&redirect_uri=" + redirect_uri + "&state=" + state + "&scope=" + Constant.qq_scope + "&display=" + display;
		return requestUrl;
	}
	
	/**
	 * 获取Access Token
	 * @param authorization_code 授权第一步获得的authorization_code
	 * @param redirect_uri 回调地址和初次请求的回调地址保持一致
	 */
	public static String getAccessToken(boolean isWap, String authorization_code, String redirect_uri) {
		if (StringUtils.isBlank(authorization_code)) {
			return null;
		}
		String accessToken = "";
		String requestUrl = "https://graph.qq.com/oauth2.0/token?";
		if (isWap) {
			requestUrl = "https://graph.z.qq.com/moc2/token?";
		}
		requestUrl += "grant_type=authorization_code&client_id=" + Constant.qq_app_id + "&client_secret=" + Constant.qq_app_key + "&code=" + authorization_code + "&redirect_uri=" + redirect_uri;
		String resultStr = httpGet(requestUrl);
		if (StringUtils.isNotBlank(resultStr) && resultStr.contains("access_token")) {
			String[] array = resultStr.split("&");
			String[] pair = array[0].split("=");
			accessToken = pair[1];
		} else if (StringUtils.isNotBlank(resultStr)) {
			logger.error("------------QQ2.0----getAccessToken error--backstr:" + resultStr);
		}
		return accessToken;
	}
	
	/**
	 * 获取用户的OpenID
	 */
	public static String getOpenId(boolean isWap, String access_token) {
		if (StringUtils.isBlank(access_token)) {
			return null;
		}
		String openId = "";
		String requestUrl = "https://graph.qq.com/oauth2.0/me?";
		if (isWap) {
			requestUrl = "https://graph.z.qq.com/moc2/me?";
		}
		requestUrl += "access_token=" + access_token;
		String resultStr = httpGet(requestUrl);
		if (StringUtils.isBlank(resultStr)) {
			return null;
		}
		Pattern p = Pattern.compile("\\{.*?\\}");// 返回格式为：callback(json数据);
		Matcher m = p.matcher(resultStr);
		if (!m.find()) {
			return null;
		}
		resultStr = m.group();
		JSONObject jsonObj = JSONObject.fromObject(resultStr);
		openId = (String) jsonObj.getString("openid");
		return openId;
	}
	
	/**
	 * 获取QQ用户信息
	 */
	public static JSONObject getUserinfo(String accessToken, String openId) {
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
			return null;
		}
		String requestUrl = "https://graph.qq.com/user/get_user_info?";
		requestUrl += "access_token=" + accessToken + "&oauth_consumer_key=" + Constant.qq_app_key + "&openid=" + openId;
		String resultStr = httpGet(requestUrl);
		JSONObject jsonObj = JSONObject.fromObject(resultStr);
		return jsonObj;
	}
	
	/***************************** QQ互联登录 end *****************************/
	
	/**
	 * 发送HttpGet请求
	 */
	private static String httpGet(String url) {
		String resultStr = "";
		try {
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(url);
			method.getParams().setParameter("http.socket.timeout", new Integer(5000));
			client.executeMethod(method);
			resultStr = method.getResponseBodyAsString();
			method.releaseConnection();
		} catch (IOException e) {
			logger.error("get请求失败:" + url, e);
		}
		return resultStr;
	}
	
	
	
	public static void main(String[] args) {
		String url = QQUtil.getAuthorizationUrlJump(false, "http://www.baidu.com", "test");
		System.out.println(url);
	}
}
