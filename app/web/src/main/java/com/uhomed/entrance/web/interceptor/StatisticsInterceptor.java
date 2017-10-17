package com.uhomed.entrance.web.interceptor;

import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.local.MethodCache;
import com.uhomed.entrance.biz.cache.local.StatisticsCache;
import com.uhomed.entrance.core.utils.DateUtils;
import com.uhomed.entrance.model.MethodStatistics;
import com.xiaoleilu.hutool.util.StrUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public class StatisticsInterceptor implements MethodInterceptor {
	
	@Resource
	private MethodCache methodCache;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String method = request.getParameter( "method" );
		String version = request.getParameter( "version" );
		
		MethodCacheDTO methodDTO = this.methodCache.getMethod( method, version );
		MethodStatistics statistics = new MethodStatistics();
		if (methodDTO != null) {
			statistics.setMethodCode( method );
			statistics.setMethodVersion( version );
			statistics.setRequestParam( request.getParameter( "bizParams" ) );
			Date date = new Date();
			statistics.setCreateTime( date );
			statistics.setId( Long.valueOf( (DateUtils.format( date, "yyyyMMddHHmmss" ) + fairNextInt( 10000, 99999 )) ) );
        }
		long startTime = System.currentTimeMillis();
		Object o = invocation.proceed();
		statistics.setHoldTime( Integer.parseInt( (System.currentTimeMillis() - startTime) + "" ) );

        if(o instanceof ModelAndView){
        	ModelAndView result = (ModelAndView) o;
        	if(StrUtil.equals(String.valueOf(result.getModel().get("code")),"100001")){
        		statistics.setStatus("ERROR");
			}else {
				statistics.setStatus("SUCCESS");
			}
		} else {
        	Map<String,Object> result = (HashMap)o;
			if(StrUtil.equals(String.valueOf(result.get("code")),"100001")){
				statistics.setStatus("ERROR");
			}else {
				statistics.setStatus("SUCCESS");
			}
		}
		StatisticsCache.add(statistics);
		return o;
	}
	
	public static int fairNextInt(final int min, final int max) {
		Random rand = new Random();
		int tmp = Math.abs( rand.nextInt( max ) );
		return tmp == 0 ? tmp = min : tmp;
	}

}
