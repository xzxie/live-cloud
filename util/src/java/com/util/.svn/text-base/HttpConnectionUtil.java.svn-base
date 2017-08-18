package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 请求第三方平台连接
 */
public class HttpConnectionUtil {

	
	private static final Log logger = LogFactory.getLog(HttpConnectionUtil.class);
	
	// post请求
	public static String httpPost(String requestUrl, Map<String, Object> requestParams) {
		
		if (StringUtils.isBlank(requestUrl)) {
			return "";
		}
		
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		StringBuffer responseResult = new StringBuffer();
		StringBuffer params = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        
        //组织请求参数
        if (MapUtils.isNotEmpty(requestParams)) {
        	Iterator it = requestParams.entrySet().iterator();
            while (it.hasNext()) {
            	Map.Entry element = (Map.Entry) it.next();
            	params.append(element.getKey());
            	params.append("=");
            	params.append(element.getValue());
            	params.append("&");
            }
            if (params.length() > 0) {
            	params.deleteCharAt(params.length()-1);
            }
        }
        
        try {
        	URL url = new URL(requestUrl);
        	//打开url连接
        	httpURLConnection = (HttpURLConnection) url.openConnection();
        	//设置通用请求属性
        	httpURLConnection.setRequestProperty("accept", "*/*");
        	httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        	httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
        	//发送post数据必须设置如下两行
        	httpURLConnection.setDoOutput(true);
        	httpURLConnection.setDoInput(true);
        	//获取URLConnection对象对应的输出流
        	printWriter = new PrintWriter(httpURLConnection.getOutputStream());
        	//发送请求参数
        	printWriter.write(params.toString());
        	//flush输出流的缓冲
        	printWriter.flush();
        	//判断连接是否成功
        	int responseCode = httpURLConnection.getResponseCode();
        	if (responseCode != 200) {
        		logger.error("error code: " + responseCode);
        	} else {
        		logger.info("POST SUCCESS!");
        	}
        	//输入流读取数据
        	bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        	String line = "";
        	while ((line=bufferedReader.readLine()) != null) {
        		responseResult.append("\n").append(line);
        	}
        } catch(Exception e) {
        	logger.error("send post request error!" + e);
        } finally {
        	httpURLConnection.disconnect();
        	try {
        		if (printWriter != null) {
        			printWriter.close();
        		}
        		if (bufferedReader != null) {
        			bufferedReader.close();
        		}
        	} catch(IOException ex) {
        		ex.printStackTrace();
        	}
        }
        
        logger.info(responseResult.toString());
        
		return responseResult.toString();
	}
	
	// get请求
	public static String httpGet(String url) {
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
	
	
	
	// 测试
	public static void main(String[] args) {
		String html = HttpConnectionUtil.httpPost("http://www.xiexiaozhang.com", null);
		System.out.println(html);
	}
}
