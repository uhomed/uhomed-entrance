/**
 * mario.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.uhomed.entrance.dal.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author liming
 * @version $Id: DynamicDataSource.java, v 0.1 2016年7月6日 下午4:49:27 liming Exp $
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	
	@Override  
    protected Object determineCurrentLookupKey() {  
        return DataBaseContextHolder.getCustomerType();   
    }  

}
