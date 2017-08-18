package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.model.UserBean;

public class JsonUtil {

	/**
	 * json 转 list
	 */
	public static List<Map<String, Object>> convertJsonStrToList(String jsonStr) {
		List<Map<String, Object>> list = JSON.parseObject(
				jsonStr, new TypeReference<List<Map<String, Object>>>(){});
		return list;
	}
	
	/**
	 * json 转 map
	 */
	public static Map<String, Object> convertJsonStrToMap(String jsonStr) {
		Map<String, Object> map = JSON.parseObject(
                jsonStr,new TypeReference<Map<String, Object>>(){} );
		return map;
	}
	
	/**
	 * json 转 bean
	 */
	public static Object convertJsonStrToBean(String jsonStr, Class clazz) {
		return JSONObject.parseObject(jsonStr, clazz);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		// map => jsonStr
		Map map = new HashMap();
		map.put("id", "11");
		map.put("username", "xxz");
		map.put("password", "pass");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		
		Map map2 = new HashMap();
		map2.put("id", "22");
		map2.put("username", "yxx");
		map2.put("password", "pass");
		
		// list => jsonStr
		List list = new ArrayList();
		list.add(map);
		list.add(map2);
		
		jsonStr = JSON.toJSONString(list);
		System.out.println(jsonStr);
		
		// jsonStr => list
		jsonStr = "[{\"id\":\"11\",\"password\":\"pass\",\"username\":\"xxz\"}]";
		List newList = JsonUtil.convertJsonStrToList(jsonStr);
		System.out.println(newList);
		
		// jsonStr => bean
		jsonStr = "{\"id\":\"11\",\"password\":\"pass\",\"username\":\"xxz\"}";
		UserBean userBean = (UserBean) JsonUtil.convertJsonStrToBean(jsonStr, UserBean.class);
		System.out.println(userBean.getId() + "\t" + userBean.getUsername() + "\t" + userBean.getPassword());
	}
}
