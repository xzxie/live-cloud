package com.util.ai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.baidu.aip.util.Base64Util;
import com.util.FileUtil;
import com.util.HttpUtil;


/**
 * OCR文字识别
 * 调用百度AI的API：http://ai.baidu.com
 * 依赖的jar：json-20160810.jar(json基础包) 、
 * 			 aip-core-1.3.8.jar(百度AI核心包) 、
 * 			 ocr_sdk-1.3.9.jar(ocr文字识别包)
 */
public class OCRUtil {
	
	private static final Logger logger = Logger.getLogger(OCRUtil.class);

	public static final String apikey = "0fG9zNF9juGZ67SnlkKAlY1e";
	public static final String secretkey = "egozPaXIieMLRkmmv32kpmv3fQyB7xeQ";
	
	// 通用文字识别
	public static final String requestUrl_general_basic = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
	// 通用文字识别（高精度版）
	public static final String requestUrl_accurate_basic = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
	// 通用文字识别（含位置信息版）
	public static final String requestUrl_general = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
	// 网络图片文字识别
	public static final String requestUrl_webimage = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
	// 身份证识别
	public static final String requestUrl_idcard = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
	// 银行卡识别
	public static final String requestUrl_bankcard = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
	// 驾驶证识别
	public static final String requestUrl_driving_license = "https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license";
	// 行驶证识别
	public static final String requestUrl_vehicle_license = "https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license";
	// 车牌识别
	public static final String requestUrl_license_plate = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
	// 表格文字识别
	public static final String requestUrl_form_ocr = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";
	// 通用票据识别
	public static final String requestUrl_receipt = "https://aip.baidubce.com/rest/2.0/ocr/v1/receipt";
	
	
	/**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param apikey - 百度云官网获取的 API Key
     * @param secretkey - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAccessToken(String apikey, String secretkey) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + apikey
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + secretkey;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
            logger.error("获取token失败！", e);
        }
        return null;
    }
    
    /**
     * 通用文字识别请求处理
     * @param filePath 文件路径
     * @return 识别的字符串
     */
    public static String handleRequest_general_basic(String filePath) {
    	try {
            byte[] imgData = null;
            if (filePath.startsWith("http") || filePath.startsWith("https")) {
            	imgData = FileUtil.readRemoteFileByBytes(filePath);
            } else {
            	imgData = FileUtil.readFileByBytes(filePath);
            }
            
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = getAccessToken(OCRUtil.apikey, OCRUtil.secretkey);
            String result = HttpUtil.post(OCRUtil.requestUrl_general_basic, accessToken, params);
            return result;
        } catch (Exception e) {
        	logger.error("通用文字识别请求处理失败.", e);
        }
    	return "";
    }
    
	
	
	/**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
	public static void main(String[] args) {
        
        //----------------------------requestUrl_general_basic---------------------------
        // 本地图片路径
        String filePath = "e:\\che4.jpg";// #####本地文件路径#####
        String result = OCRUtil.handleRequest_general_basic(filePath);
        System.out.println(result);
        
        filePath = "http://img.bss.csdn.net/201708301124264435.png";// #####远程文件路径#####
        result = OCRUtil.handleRequest_general_basic(filePath);
        System.out.println(result);
        
        JSONObject json = new JSONObject(result);
        org.json.JSONArray jsonArray = json.getJSONArray("words_result");
        System.out.println(jsonArray);
        for (Object value : jsonArray.toList()) {
        	System.out.println(value);
        }
        //----------------------------requestUrl_general_basic---------------------------
	}

}
