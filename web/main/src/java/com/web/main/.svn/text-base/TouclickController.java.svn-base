package com.web.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.touclick.captcha.model.Status;
import com.touclick.captcha.service.TouClick;
import com.util.Constant;
import com.util.WebUtil;

/**
 * 第三方验证码
 */
public class TouclickController extends MultiActionController{

	private TouClick touclick = new TouClick();
    
    
    public void index(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	/*
    	*  token 二次验证口令，单次有效
    	*  checkAddress 二次验证地址，二级域名
    	*  checkCode 校验码，开发者自定义，一般采用手机号或者用户ID，用来更细致的频次控制
    	*/
    	String checkAddress = request.getParameter("checkAddress");
    	String token = request.getParameter("token");
    	String sid = request.getParameter("sid");
    	
    	Status status = touclick.check(checkAddress, sid, token,
    			Constant.touclick_http_pubKey, Constant.touclick_http_priKey);
    	
    	System.out.println("checkAddress :"+checkAddress + ",token:" + token);
        System.out.println("code :"+status.getCode() + ",message:" + status.getMessage());
    	
        Map<String, Object> result = new HashMap<String, Object>();
        if (status!=null && status.getCode()==0) {
        	//执行自己的程序逻辑
        	result.put("RESULT", "success");
        } else {
        	result.put("RESULT", "fail");
        }
        WebUtil.write(response, result);
        
    }
}
