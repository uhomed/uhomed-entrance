/**
 * mario.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.uhomed.entrance.dal.datasource;

/**
 * 
 * @author liming
 * @version $Id: DataBaseContextHolder.java, v 0.1 2016年7月6日 下午4:46:05 liming Exp $
 */
public class DataBaseContextHolder {
	
	private static final ThreadLocal<DataSourceContext> contextHolder = new ThreadLocal<DataSourceContext>();
	
	
	public static void setCustomerType(DataSourceContext source){
		contextHolder.set(source);
	}
	
	public static DataSourceContext getCustomerType(){
		return contextHolder.get();
	}
	
	public static void clearCustomerType() {  
        contextHolder.remove();  
    } 

}
