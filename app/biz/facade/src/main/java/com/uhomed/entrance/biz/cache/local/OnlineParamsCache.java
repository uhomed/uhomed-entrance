package com.uhomed.entrance.biz.cache.local;

import com.alibaba.druid.util.StringUtils;
import com.google.common.base.Joiner;
import com.uhomed.entrance.biz.cache.GenericServiceFactory;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import com.uhomed.entrance.biz.context.MethodTypeContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Component("onlineParamsCache")
public class OnlineParamsCache {
	
	private ConcurrentHashMap<String, String> methodCache = new ConcurrentHashMap<>();
	
	public String getValue(String group, String code) {
		return methodCache.get( this.buildKey( group, code ) );
	}
	
	public void putValue(String group, String code, String value) {
		this.methodCache.put( this.buildKey( group, code ), value );
	}
	
	private String buildKey(String group, String code) {
		return Joiner.on( "_" ).join( group, code );
	}
	
}
