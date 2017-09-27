package com.uhomed.entrance.biz.request;

import java.util.*;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.util.TypeUtils;
import com.uhomed.entrance.biz.cache.MethodCache;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.uhomed.entrance.biz.cache.GenericServiceFactory;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodDubboDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import com.uhomed.entrance.biz.exception.ParamException;
import com.uhomed.entrance.core.utils.logger.LoggerUtils;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Component
public class RequestDubbo implements Request {
	
	private static final Logger DEFAULT_LOGGER = Logger.getLogger( RequestDubbo.class );
	
	public Object request(String sso, String bizParams, MethodCacheDTO methodDTO) throws ParamException {

		ModelAndView result = new ModelAndView();
		long allTime = System.currentTimeMillis();
		List<Object> paramsValue = new ArrayList<>();
		List<String> paramsType = new ArrayList<>();
		// 使用隐式传参方式 将sso token传入服务
		RpcContext.getContext().setAttachment( "sso", sso );
		if (CollectionUtils.isNotEmpty( methodDTO.getParams() )) {
			
			List<MethodParamCacheDTO> paramList = methodDTO.getParams();
			JSONObject json = null;
			try {
				json = JSONObject.parseObject( bizParams );
			} catch (Exception e) {
				throw new ParamException( "bizParams解析错误！" );
			}
			for (MethodParamCacheDTO p : paramList) {
				paramsType.add( p.getClazzStr() );
				// 是否是自定义对象
				try {
					Object value = json.get(p.getCode());
					if(value != null && value instanceof JSONArray){
						paramsValue.add( value );
					}else if (p.getClazz() instanceof Number || p.getClazz() instanceof Date || p.getClazz() instanceof String  || p.getClazz() instanceof Boolean || p.getClazz() instanceof Collection) {
//						value = json.getObject( p.getCode(), p.getClazz().getClass() );
						value = TypeUtils.castToJavaBean(value, p.getClazz().getClass());

						if(StrUtil.isNotEmpty(p.getDefaultValue()) && value == null){
							value = p.getDefaultValue();
						}

						if (p.isRequire() && value == null) {
							throw new ParamException( p.getCode() + "不能为空！" );
						}
						if (value instanceof String) {
							if (p.getLength() != null && p.getLength() < String.valueOf( value ).length()) {
								throw new ParamException( p.getCode() + "长度大于" + p.getLength() + "位字符！" );
							}
						}
						paramsValue.add( value );
					} else {
						Map<String, Object> domain = JSON.parseObject( json.getString( p.getCode() ), new TypeReference<Map<String, Object>>() {
						} );
						paramsValue.add( domain );
					}
				} catch (NullPointerException e) {
					if (p.isRequire()) {
						throw new ParamException( p.getCode() + "不能为空！" );
					}
//				} catch (JSONException e){
//					if(e.getMessage().equalsIgnoreCase("can not cast to : java.util.ArrayList")){
//						//数据格式
//						paramsValue.add(json.get(p.getCode()));
//					}
				}
			}
		}
		
		try {
			long rpcTime = System.currentTimeMillis();
			// String genericKey = Joiner.on( '_' ).join( methodDTO.getApiMethodCode(),
			// methodDTO.getApiMethodVersion() );
			GenericService genericService = GenericServiceFactory.getInstance( methodDTO.getId().toString() );
			String[] strings = new String[paramsType.size()];
			paramsType.toArray( strings );
			Object o = genericService.$invoke( ((MethodDubboDTO) methodDTO.getMethodInfo()).getMethodName(), strings, paramsValue.toArray() );
			DEFAULT_LOGGER
					.info( "rpc request method [" + methodDTO.getApiMethodCode() + "] time [" + (System.currentTimeMillis() - rpcTime) + "ms]" );
			if (o != null) {
				removeMapKeyIfClass( o );
				return o;
			}
		} catch (RpcException e) {
			result.addObject("status",false);
			if(e.getMessage().contains("Please check if the providers have been started and registered")){
                result.addObject("message","未在注册中心发现该接口");
            }else if(e.getMessage().contains("Failed to invoke remote method")){
				result.addObject("message","发现服务提供者，未发现该方法");
            }else {
				result.addObject("message","网络异常，请稍候再试！" );
            }
			result.addObject("code","100001");
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiMethodCode() + "]" );
		} catch (Exception e) {
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiMethodCode() + "]" );
			throw new RpcException( "网络繁忙，请稍候再试！" );
		} finally {
			DEFAULT_LOGGER
					.info( "all request method [" + methodDTO.getApiMethodCode() + "] time [" + (System.currentTimeMillis() - allTime) + "ms]" );
		}
		return result;
	}
	
	/**
	 * 移除返回map 结构数据 key 为class的值
	 *
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object removeMapKeyIfClass(Object object) {
		if (object == null) {
			return null;
		}
		
		if (object instanceof Map) {// 对象删除
			Map<Object, Object> objMap = (Map) object;
			objMap.remove( "class" );
			Set<Object> keys = objMap.keySet();
			for (Object key : keys) {
				Object value = objMap.get( key );
				Object fixValue = removeMapKeyIfClass( value );
				objMap.put( key, fixValue );
			}
			return objMap;
		} else if (object instanceof Collection) {// 集合删除
			Collection<Object> c = (Collection) object;
			for (Object obj : c) {
				removeMapKeyIfClass( obj );
			}
			return c;
		} else if (object.getClass().isArray()) {// 数组删除
			Object[] objs = (Object[]) object;
			for (Object obj : objs) {
				removeMapKeyIfClass( obj );
			}
			return objs;
		} else {// 其他直接返回
			return object;
		}
		
	}


}
