package com.util;

public class Constant {

	// 普通资源访问
	public static String STATIC_PREFIX = "//www.xiexiaozhang.com";
	public static boolean DEBUG = Boolean.FALSE;
	static {
		if (DEBUG) {
			STATIC_PREFIX = "";
		}
	}
	
	// 文件服务器资源访问
	public static String IMG1 = "http://img1.xiexiaozhang.com";
	public static String IMG2 = "http://img2.xiexiaozhang.com";
	public static String IMG3 = "http://img3.xiexiaozhang.com";
	public static String IMG4 = "http://img4.xiexiaozhang.com";
	
	
	public static final String VERSION = System.currentTimeMillis()+"";
	public static final char split = 129;
	public static final String split_comma = ",";
	public static final String charset = "UTF-8";
	public static final boolean isTrue = Boolean.TRUE;
	public static final boolean isFalse = Boolean.FALSE;
	
	// 网站配置
	public static String site_open_close_switch = "open";
	public static String cache_open_close_swtich = "open";
	
	// 注册秘钥/默认盐值
	public static final String password_secretkey = "!23$5^7*(0";
	public static final String password_default_salt = "!(()&#";
	
	// 第三方验证码API
	//geetest验证码(未使用)
	public static final String geetest_id = "321c6a0496e33bf2282fd0124bdf3887";
	public static final String geetest_key = "2de0fe40c351714d1df2d59b104ce6e1";
	//touclick验证码(当前使用)
	public static final String touclick_http_pubKey = "0ee10985-e533-49d3-8051-527f6f0dec20";
	public static final String touclick_http_priKey = "b231ffee-3566-4af8-8363-445de2735d23";
	public static final String touclick_https_pubKey = "10c81c84-99e6-469e-84a1-14096c0b30d5";
	public static final String touclick_https_priKey = "45932399-dd79-438c-bf38-f36623f97f6c";
	
	//QQ登录(还未开通)
	public static final String qq_app_id = "1106091966";
	public static final String qq_app_key = "aMLac9xcYNVDu6kR";
	public static final String qq_scope = "get_info,get_user_info";
	
	//微信登录(还未开通)
	public static final String weixin_AppID = "wx35420ff8a0e653d3";
	public static final String weixin_AppSecret = "b4eee50ca3cc08eea137a1433a185a73";
	
	//微博登录(账户：845098567@qq.com)
	public static final String weibo_appkey = "1570558682";
	public static final String weibo_appsecret = "abac7f77e7b7f52f8786cbb4f69e81df";
	public static final String weibo_redirect_URI = "/user/login_weibo.do";
	
	//MustPay聚合支付
	public static final String mustpay_app_id = "44b01eaf16f54bfebdf9d1573e137d4a";
	public static final String mustpay_trade_no = "17041616495947036";
	public static final String mustpay_client_secret = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDO7CQpYHhEonv1g9YjRVGJDaCOu0bXogD7pBLQu2dDvJ8TGROCEw6ArIWgAWEEE1uEShPBa4MpCP4ZMjT5RMj45o0pb0Z8s4k6CpS9D1LFK9msNpsN8PyaRDQC86R6jxAVQMWgfIZ9cxfZR8Ple3GJGjwBfeRnzh75rE1DHCBOcwIDAQAB";
	
	//支付宝商户
	public static final String alipay_APPID = "2017041506744858";
	public static final String alipay_ali_geteway = "https://openapi.alipay.com/gateway.do";	//
	public static final String alipay_geteway = SeoUtil.domain + "/alipay/notify.do";		//应用网关(服务商网络地址，用于接收支付宝异步通知)
	public static final String alipay_callback = SeoUtil.domain + "/alipay/callback.do";	//回调地址(服务商网络地址，第三方授权或用户信息授权后的回调地址)
	
	//微信商户
	
	//第三方短信平台
	//中国网建
	public static final String webchinese_Uid = "aaronxyt";
	public static final String webchinese_Key = "5a5589490d91caae63fb";
	
	//leancloud短信验证
	public static final String leancloud_app_id = "wG3GXqtFER5eCPa5TOQJbP1s-gzGzoHsz";
	public static final String leancloud_app_key = "6TSPMFFkrSXSY43qEfAVNxlv";
	public static final String leancloud_master_key = "JpJ4fz4sWHICIMnwS8BOx7TI";

	//移动短信
	public static final String chinamobile_Uid = "";
	public static final String chinamobile_Key = "";
	
	
	// 百度开发者(图片色情识别、语音识别、LBS地理服务)
	public static final String baidu_app_id = "9751562";
	public static final String baidu_api_key = "mqVqKw6NTDOB4ylHo02ZXlGH";
	public static final String baidu_Secret_key = "Dsnd0MlEUpQ1wbcpF4jnmEeLhDz2oWZg";
	
	
	
	public static void main(String[] args) {
		
	}
}
