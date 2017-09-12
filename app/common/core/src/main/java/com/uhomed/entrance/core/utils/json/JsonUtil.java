/**  
 * @Title: JsonUtil.java 
 * @author lim  
 * @date 2013-8-1 下午04:59:45 
 * @version V1.0   
 */
package com.uhomed.entrance.core.utils.json;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json转换工具类
 * @author lim
 * @version $Id$   
 * @since 2.0
 * @date 2012-6-25 下午05:32:09
 * @updateInfo
 */
public class JsonUtil {
	/**
	 * json转换javaBean
	 * @param <T>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @lastUpdateTime 2012-6-25 下午05:33:30
	 * @updateInfo
	 */
	public static <T> T json2JavaBean(String jsonStr, Class<T> clazz){
		try {
			String destr = URLDecoder.decode(jsonStr, "UTF-8");
			GsonBuilder gsonb = new GsonBuilder();
			Gson gson = gsonb.create();
			T retObj = gson.fromJson(destr, clazz);
			return retObj;
		} catch(Exception ex) {
			return null;
		}
	}
	/**
	 * javaBean转换json
	 * @param obj
	 * @param type
	 * @return
	 * @lastUpdateTime 2012-6-25 下午05:35:26
	 * @updateInfo
	 */
	public static String javaBean2Json(Object obj, Type type){
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
//			json = mapper.writeValueAsString(obj);
			mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		/*GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		
		String jsonStr = gson.toJson(obj,type);
		return jsonStr;*/
	}
	
	public static String javaBean2Json(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * json集合转换javaList
	 * new TypeToken<List<AdveMo>>() {}.getType()
	 * @param <T>
	 * @param jsonStr
	 * @param type
	 * @return
	 * @lastUpdateTime 2012-6-25 下午05:34:47
	 * @updateInfo
	 */
	public static <T> List<T> jsonArray2Java(String jsonStr, Type type){
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		List<T> retList = gson.fromJson(jsonStr, type);
		return retList;
	}
	
	/**
	 * json转换map
	 * @param s
	 * @return
	 * @lastUpdateTime 2012-6-25 下午05:37:05
	 * @updateInfo
	 */
	public static Map<Object,Object> parser2Map(String s){
		Map<Object,Object> map = new HashMap<Object,Object>();
		JSONObject json = JSONObject.fromObject(s);
		@SuppressWarnings("rawtypes")
		Iterator keys = json.keys();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, parser2Map(value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
	
}
