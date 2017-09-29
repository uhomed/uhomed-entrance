/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.uhomed.entrance.web.controller.portal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcException;
import com.uhomed.entrance.biz.cache.MethodCache;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.context.MethodTypeContext;
import com.uhomed.entrance.biz.exception.ParamException;
import com.uhomed.entrance.biz.request.Request;
import com.uhomed.entrance.web.controller.BaseController;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 
 * @author liming
 * @version $Id: GatewayController.java, v 0.1 2017年7月2日 下午2:54:08 liming Exp $
 */
@Controller
@Scope("prototype")
public class GatewayController extends BaseController {
	
	/***/
	private static final long	serialVersionUID	= -3830222581615420845L;
	@Resource
	private MethodCache			methodCache;
	@Resource
	private Request				requestDubbo;
	
	/**
	 * 网关入口
	 * 
	 * @param method
	 * @param bizParams
	 * @param version
	 * @param format
	 * @param sso
	 * @param timestamp
	 * @return
	 */
	@RequestMapping(value = "/gateway", method = { RequestMethod.POST, RequestMethod.GET })
	public Object gateway(String method, String bizParams, String version, String format, String sso, String timestamp, String client,
			String clientVersion, String router) {
		ModelAndView result = new ModelAndView();
		
		if (StringUtils.isEmpty( method )) {
			super.setFailMessage( result, "方法名不能为空！" );
			return result;
		}
		if (StringUtils.isEmpty( version )) {
			super.setFailMessage( result, "接口版本号不能为空！" );
			return result;
		}
		
		MethodCacheDTO methodDTO = this.methodCache.getMethod( method, version );
		if (methodDTO == null) {
			super.setFailMessage( result, "该方法不存在或未开放！" );
			return result;
		}
		
		if (!super.request.getMethod().equalsIgnoreCase( methodDTO.getMode() )) {
			super.setFailMessage( result, "方法不存在！" );
			return result;
		}
		
		if (methodDTO.isVerifiSso()) {
			if (StrUtil.isEmpty( sso )) {
				super.setFailMessage( result, "sso不能为空！" );
				return result;
			}
		}
		
		if (!methodDTO.isStatus()) {
			super.setFailMessage( result, "该方法未开放！" );
			return result;
		}
		Request request = null;
		if (methodDTO.getType() == MethodTypeContext.DUBBO) {
			request = requestDubbo;
		}
		
		try {
			Object o = request.request( sso, bizParams, methodDTO,router );
			if (o != null) {
				return o;
			}
		} catch (ParamException e) {
			super.setFailMessage( result, e.getMessage() );
		} catch (Exception e) {
			e.printStackTrace();
			super.setFailMessage( result, "网络异常，请稍候再试！", "100001" );
		}
		return result;
	}
	
}
