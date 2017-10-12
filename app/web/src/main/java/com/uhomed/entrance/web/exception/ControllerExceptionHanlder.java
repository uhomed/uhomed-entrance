/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.uhomed.entrance.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uhomed.entrance.core.utils.logger.LoggerUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author liming
 * @version $Id: ControllerExceptionHanlder.java, v 0.1 2015年5月23日 下午5:47:47
 *          liming Exp $
 */
public class ControllerExceptionHanlder implements HandlerExceptionResolver {
	
	/**
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoggerUtils.defaultPrint( ex );
		ModelAndView result = new ModelAndView();
		result.addObject( "success", false );
		result.addObject( "message", "程序异常，请联系管理员" );
		result.addObject( "code", "100001" );
		return result;
	}
	
}
