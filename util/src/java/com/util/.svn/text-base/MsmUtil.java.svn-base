package com.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 发短信
 */
public class MsmUtil {

	
	private static final Log logger = LogFactory.getLog(MsmUtil.class);
	
	/**
	 * 中国网建  
	 * http://sms.webchinese.com.cn
	 */
	public static String sendSMS(String phone, String text) {
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
        		new NameValuePair("Uid", Constant.webchinese_Uid), // 注册的用户名
                new NameValuePair("Key", Constant.webchinese_Key), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phone), // 手机号码
                new NameValuePair("smsText", text) // 设置短信内容
        };
        post.setRequestBody(data);
        
        String resultCode = "";
        try {
        	client.executeMethod(post);
    	    Header[] headers = post.getResponseHeaders();
    	    int statusCode = post.getStatusCode();
    	    System.out.println("statusCode:" + statusCode);
    	    for (Header h : headers) {
    	        System.out.println(h.toString());
    	    }
    	    resultCode = new String(post.getResponseBodyAsString().getBytes("gbk"));
    	    post.releaseConnection();
        } catch(Exception e) {
        	logger.error(phone + "短信发送失败!" + e);
        }
	    
	    return resultCode;
	}
}
