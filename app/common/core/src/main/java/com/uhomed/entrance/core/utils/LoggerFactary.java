/**
 * @date 2014年6月20日 下午2:48:16
 * @author lim
 * @version V1.0 
 */
package com.uhomed.entrance.core.utils;

import org.apache.log4j.Logger;

/**
 * 日志工厂
 * @date 2014年6月20日 下午2:48:16
 * @author lim
 * @version V1.0 
 */
public class LoggerFactary {
	
	
	/** 管理员日志 */
	public static final String ADMIN_LOGGER = "admininfo";
	/** 锁日志 */
	public static final String LOCK_LOGGER = "lockinfo";
	
	public static final String EMAIL_LOGGER = "lockinfo";
	
	public static Logger getLogger(String logName){
		return Logger.getLogger(logName);
	}
}
