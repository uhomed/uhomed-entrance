package com.uhomed.entrance.biz.request;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.uhomed.entrance.biz.cache.GenericServiceFactory;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodDubboDTO;
import com.uhomed.entrance.biz.exception.ParamException;
import com.uhomed.entrance.core.utils.DateUtils;
import com.uhomed.entrance.core.utils.common.LoadConfig;
import com.uhomed.entrance.core.utils.logger.LoggerUtils;
import com.uhomed.entrance.core.utils.sizeof.RamUsageEstimator;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Component
public class RequestDubbo implements Request {
	
	private static final Logger DEFAULT_LOGGER = Logger.getLogger( RequestDubbo.class );


	public Object request(String sso, String bizParams, MethodCacheDTO methodDTO, String router,
						  HttpServletRequest request) throws ParamException {
		
		// router = "10.0.0.169";
		ModelAndView result = new ModelAndView();
		long allTime = System.currentTimeMillis();
		// 使用隐式传参方式 将sso token传入服务
		RpcContext.getContext().setAttachment( "sso", sso );
		//返回请求状态码
		RpcContext.getContext().setAttachment("Content-Type",request.getContentType());
		RpcContext.getContext().setAttachment("encoding",request.getCharacterEncoding());

		Map<String, Object> params = RequestUtil.convertParams( bizParams, methodDTO,request );

		//是否包含缓存  缓存key = (sso + methodName + version + router).hashCode() + "_" + params.hashCode();
		if(methodDTO.isCache()){
			Object t = this.getCache(sso,methodDTO.getApiMethodCode(),methodDTO.getApiMethodVersion(),router,params);
			if(t != null){
				return t;
			}
		}


		Registry registry = null;
		URL url = null;
		
		// 是否设置动态路由
		MethodDubboDTO dubbo = (MethodDubboDTO) methodDTO.getMethodInfo();

		if (StrUtil.isNotEmpty( router )) {
			RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader( RegistryFactory.class )
					.getAdaptiveExtension();
			registry = registryFactory.getRegistry(
					URL.valueOf( "zookeeper://" + LoadConfig.getInstance().getValue( "zookeeper.ip.config" ) ) );
			url = URL.valueOf( "condition://0.0.0.0/" + dubbo.getClassPath()
					+ "?category=routers&dynamic=false&enabled=true&force=true&name=ss&priority=12&router=condition&rule==> provider.host = "
					+ router + "&runtime=true" );
			registry.register( url );
			try {
				Thread.sleep( 100l );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			long rpcTime = System.currentTimeMillis();
			GenericService genericService = GenericServiceFactory.getInstance( methodDTO.getId().toString() );
			List<String> types = (List<String>) params.get( "types" );
			List<Object> values = (List<Object>) params.get( "values" );
			String[] strings = new String[types.size()];
			types.toArray( strings );
			//image
			Object o = genericService.$invoke( dubbo.getMethodName(), strings, values.toArray() );
			DEFAULT_LOGGER.info( "rpc request method [" + methodDTO.getApiMethodCode() + "] time ["
					+ (System.currentTimeMillis() - rpcTime) + "ms]" );
			if (o != null) {
				removeMapKeyIfClass( o );

				if(methodDTO.isCache()){
					RequestCache cache = new RequestCache();
					cache.setValue(o);
					cache.setCacheTime(new Date());
					cache.setExpiredTime(DateUtils.addSecond(new Date(),methodDTO.getSecond()));
					String key = this.buildCacheKey(sso,methodDTO.getApiMethodCode(),methodDTO.getApiMethodVersion(),router,params);
//					RequestUtil.CACHE.put(key,cache);
					RequestCacheUtils.put(key,cache);
					System.out.println("加入缓存！" + key);
				}

				return o;
			}
		} catch (RpcException e) {
			result.addObject( "status", false );
			if (e.getMessage().contains( "Please check if the providers have been started and registered" )) {
				if(StrUtil.isNotEmpty(router)){
					result.addObject("message","router:" + router + "未发现服务接口");
				}else {
					result.addObject( "message", "未在注册中心发现该接口" );
				}
			} else if (e.getMessage().contains( "Failed to invoke remote method" )) {
				result.addObject( "message", "发现服务提供者，未发现该方法" );
			} else {
				result.addObject( "message", "网络异常，请稍候再试！" );
			}
			result.addObject( "code", "100001" );
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiMethodCode() + "]" );
		} catch (Exception e) {
			LoggerUtils.defaultPrint( e, "rpc request method [" + methodDTO.getApiMethodCode() + "]" );
			throw new RpcException( "网络繁忙，请稍候再试！" );
		} finally {
			if (registry != null) {
				registry.unregister( url );
			}
			DEFAULT_LOGGER.info( "all request method [" + methodDTO.getApiMethodCode() + "] time ["
					+ (System.currentTimeMillis() - allTime) + "ms]" );
		}
		return result;
	}

	/**
	 * @param sso
	 * @param method
	 * @param version
	 * @param router
	 * @param params
	 * @return
	 */
	private Object getCache(String sso,String method,String version,String router,Map<String, Object> params){
		String key = buildCacheKey(sso, method, version, router, params);
		return RequestCacheUtils.get(key);
	}

	private String buildCacheKey(String sso,String method,String version,String router,Map<String, Object> params){
		StringBuilder key = new StringBuilder();
		key.append(sso).append(method).append(version).append(router);
		return key.toString().hashCode() + "_" + params.hashCode();
	}


	/**
	 * 移除返回map 结构数据 key 为class的值
	 *
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object removeMapKeyIfClass(Object object) {
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
