package com.uhomed.entrance.biz.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.TypeUtils;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import com.uhomed.entrance.biz.context.MethodParamContext;
import com.uhomed.entrance.biz.exception.ParamException;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public class RequestUtil {
	
	public static Map<String, Object> convertParams(String bizParams, MethodCacheDTO methodDTO,
			HttpServletRequest request) throws ParamException {
		Map<String, Object> result = new HashMap<>();
		
		List<String> types = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		if (CollectionUtil.isNotEmpty( methodDTO.getParams() )) {
			List<MethodParamCacheDTO> paramList = methodDTO.getParams();
			JSONObject json = null;
			try {
				json = JSONObject.parseObject( bizParams );
			} catch (Exception e) {
				throw new ParamException( "bizParams解析错误！" );
			}
			// 获取url后参数
			Map<String, String> urlParams = toMap( request.getQueryString() );
			for (MethodParamCacheDTO p : paramList) {
				types.add( p.getClazzStr() );
				// 获取到参数， 暂时忽视类型
				Object value = null;
				if (MethodParamContext.Resource.HEADERS == p.getResource()) {
					value = request.getHeader( p.getCode() );
				} else if (MethodParamContext.Resource.REQUEST_BODY == p.getResource()) {
					BufferedReader reader = null;
					try {
						if (request.getInputStream() != null) {
							reader = new BufferedReader( new InputStreamReader( request.getInputStream() ) );
							value = IOUtils.read( reader );

						}
					} catch (IOException e) {
						throw new ParamException( "requestBody获取失败！" );
					} finally {
						if(reader != null){
							try {
								reader.close();
							} catch (IOException e) {
								throw new ParamException( "requestBody获取失败！" );
							}
						}
					}
				} else if (MethodParamContext.Resource.URL == p.getResource()) {
					value = urlParams.get( p.getCode() );
				} else if (MethodParamContext.Resource.BIZ_PARAMS == p.getResource()) {
					value = json.get( p.getCode() );
				}
				// 放入默认值
				if (StrUtil.isNotEmpty( p.getDefaultValue() ) && value == null) {
					value = p.getDefaultValue();
				}
				
				// 是否必传并且为空时直接抛出异常
				if (p.isRequire() && value == null) {
					throw new ParamException( p.getName() + "不能为空！" );
				}
				
				if (value != null) {
					if (p.getClazz() instanceof String) {
						// 兼容 前台传数字，后台字符串问题 {"test":123}
						String tempValue = TypeUtils.castToJavaBean( value, String.class );
						// 验证string长度
						if (p.getLength() != 0 && p.getLength() < tempValue.length()) {
							throw new ParamException( p.getName() + "长度大于" + p.getLength() + "！" );
						} else if (p.getMinLength() != 0 && tempValue.length() < p.getMinLength()) {
							throw new ParamException( p.getName() + "长度小于" + p.getLength() + "！" );
						}
						value = tempValue;
					} else if (p.getClazz() instanceof Number && !(value instanceof Number)) {
						//兼容  {"test":"123"}
						value = TypeUtils.castToJavaBean( value, p.getClazz().getClass() );
					} else if (p.getClazz().getClass().getName().equalsIgnoreCase( Object.class.getName() )) {
						// 是否是object类型
						Map<String, Object> domain = JSON.parseObject( String.valueOf( value ),
								new TypeReference<Map<String, Object>>() {
								} );
						value = domain;
					}
				}
				
				values.add( value );
			}
		}
		result.put( "types", types );
		result.put( "values", values );
		return result;
	}
	
	public static Map<String, String> toMap(String url) {
		Map<String, String> map = null;
		if (url != null && url.indexOf( "&" ) > -1 && url.indexOf( "=" ) > -1) {
			map = new HashMap<>();
			String[] arrTemp = url.split( "&" );
			for (String str : arrTemp) {
				String[] qs = str.split( "=" );
				map.put( qs[ 0 ], qs[ 1 ] );
			}
		}
		return map;
	}
	
}
