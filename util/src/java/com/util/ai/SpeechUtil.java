package com.util.ai;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.util.FileUtil;

/**
 * 语音识别
 *	原始语音的录音格式目前只支持评测 8k/16k 采样率 16bit 位深的单声道语音
 *	压缩格式支持：pcm（不压缩）、wav、opus、speex、amr
 *	系统支持语言种类：中文（zh）、粤语（ct）、英文（en）。
 *	依赖的jar：aip-core-1.3.8.jar、json-20160810.jar、speech_sdk-1.0.1.jar
 */
public class SpeechUtil {

	private static final Logger logger = Logger.getLogger(SpeechUtil.class);
	
	public static final String appid = "10042174";
	public static final String apikey = "0fG9zNF9juGZ67SnlkKAlY1e";
	public static final String secretkey = "egozPaXIieMLRkmmv32kpmv3fQyB7xeQ";
	
	
	
	public static void main(String[] args) {
		
		AipSpeech client = new AipSpeech(appid, apikey, secretkey);
		
		// 对本地语音文件进行识别
		String path = "D:\\code\\java-sdk\\speech_sdk\\src\\test\\resources\\16k_test.pcm";
		JSONObject asrRes = client.asr(path, "pcm", 16000, null);
	    System.out.println(asrRes);
	    
	    // 对语音二进制数据进行识别
	    byte[] data = null;
		try {
			data = FileUtil.readFileByBytes(path);
		} catch (IOException e) {
			logger.error("语音二进制数据读取错误.", e);
		}
	    JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
	    System.out.println(asrRes2);
		
		// 对网络上音频进行识别
	    String url = "http://somehost/res/16k_test.pcm";
	    String callback = "http://callbackhost/aip/dump";
	    JSONObject res = client.asr(url, callback, "pcm", 16000, null);
	    System.out.println(res);
	    
	}
}
