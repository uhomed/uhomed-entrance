/**
 * mario.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.uhomed.entrance.dal.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.MethodBeforeAdvice;


/**
 * 
 * @author liming
 * @version $Id: DataSourceInterceptor.java, v 0.1 2016年7月6日 下午4:44:56 liming Exp $
 */
public class DataSourceInterceptor implements MethodBeforeAdvice{
	
	public void setPortalDataSource(JoinPoint jp){
		DataBaseContextHolder.setCustomerType(DataSourceContext.PROTAL);
	}
	
	public void setManagerDataSource(JoinPoint jp){
		DataBaseContextHolder.setCustomerType(DataSourceContext.MANAGER);
	}

	/** 
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		DataBaseContextHolder.setCustomerType(DataSourceContext.MANAGER);
	}


}
