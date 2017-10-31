package com.uhomed.entrance.biz.request;

import java.util.*;

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
import com.uhomed.entrance.core.utils.common.LoadConfig;
import com.uhomed.entrance.core.utils.logger.LoggerUtils;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Component
public class RequestDubbo implements Request {
	
	private static final Logger DEFAULT_LOGGER = Logger.getLogger( RequestDubbo.class );
	
	public Object request(String sso, String bizParams, MethodCacheDTO methodDTO, String router) throws ParamException {
		
		// router = "10.0.0.169";
		ModelAndView result = new ModelAndView();
		long allTime = System.currentTimeMillis();
		// 使用隐式传参方式 将sso token传入服务
		RpcContext.getContext().setAttachment( "sso", sso );
		
		Map<String, Object> params = RequestUtil.convertParams( bizParams, methodDTO );
		
		Registry registry = null;
		URL url = null;
		
		// 是否设置动态路由
		MethodDubboDTO dubbo = (MethodDubboDTO) methodDTO.getMethodInfo();
		
		if (StrUtil.isNotEmpty( router )) {
			RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader( RegistryFactory.class ).getAdaptiveExtension();
			registry = registryFactory.getRegistry( URL.valueOf( "zookeeper://" + LoadConfig.getInstance().getValue( "zookeeper.ip.config" ) ) );
			url = URL.valueOf( "condition://0.0.0.0/" + dubbo.getClassPath()
					+ "?category=routers&dynamic=false&enabled=true&force=true&name=ss&priority=12&router=condition&rule==> provider.host = " + router
					+ "&runtime=true" );
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
			
			Object o = genericService.$invoke( dubbo.getMethodName(), strings, values.toArray() );
			DEFAULT_LOGGER
					.info( "rpc request method [" + methodDTO.getApiMethodCode() + "] time [" + (System.currentTimeMillis() - rpcTime) + "ms]" );
			if (o != null) {
				removeMapKeyIfClass( o );
				return o;
			}
		} catch (RpcException e) {
			result.addObject( "status", false );
			if (e.getMessage().contains( "Please check if the providers have been started and registered" )) {
				result.addObject( "message", "未在注册中心发现该接口" );
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
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();
		params.put( "java.lang.String", "123123" );
		params.put( "java.lang.String", "321321" );
		
		System.out.println( params.keySet().toArray() );
		System.out.println( params.entrySet().toArray() );
	}
	
}
