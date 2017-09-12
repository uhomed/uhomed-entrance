/**
 * mario.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.uhomed.entrance.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.uhomed.entrance.dal.result.Result;


/**
 * 
 * @author liming
 * @version $Id: BaseController.java, v 0.1 2017年2月13日 下午2:34:45 liming Exp $
 */
public class BaseController implements Serializable {
	
	private static final long		serialVersionUID	= 8419119703003204949L;
	
	protected static String FAIL				= "fail";
	
	protected static String SUCCESSFUL			= "successful";
	/** 重定向 */
//	private final String			REDIRECT			= "redirect:";
	
	/** request */
	@Autowired
	protected HttpServletRequest	request;
	/** session */
	@Autowired
	protected HttpSession			session;
	
	/**
	 * set信息
	 * 
	 * @param message
	 */
	protected void setFailMessage(ModelAndView result, String message, String... code) {
		result.addObject("success",false);
		result.addObject("message",message);
		if (code.length != 0) {
			result.addObject( "code", code[ 0 ] );
		}
	}
	
	protected void setSuccessful(ModelAndView result, String message) {
		result.addObject("success",true);
		result.addObject("message",message);
		result.addObject( "code", "000000" );
	}
	
	
	/**
	 * get client real ip address
	 * <p>
	 * need tengine proxy settings
	 * </p>
	 * <tengine> location /{ proxy_set_header X-Real-IP $remote_addr;
	 * proxy_set_header Host $host; } </tengine>
	 * 
	 * @return
	 */
	public String getClientAddress() {
		String ip = request.getHeader( "X-Real-IP" );
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip )) {
			ip = request.getHeader( "x-forwarded-for" );
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip )) {
			ip = request.getHeader( "Proxy-Client-IP" );
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip )) {
			ip = request.getHeader( "WL-Proxy-Client-IP" );
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip )) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	protected String getSSOToken() {
		String sso = this.request.getHeader( "sso" );
		if (StringUtils.isEmpty( sso )) {
			sso = this.request.getParameter( "sso" );
		}
		return sso;
	}
	
	protected String getVersion() {
		return this.request.getHeader( "version" );
	}


	protected void setResultModel(ModelAndView result , Result r){
		if(r.isSuccess()){
			this.setSuccessful(result,r.getMessage());
		}else {
			this.setFailMessage(result,r.getMessage());
		}
	}
	
}