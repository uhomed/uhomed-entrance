package com.uhomed.entrance.biz.request;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Request cache.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class RequestCache implements Serializable {
	
	/** 缓存对象 */
	private Object	value;
	/** 缓存时间 */
	private Date	cacheTime;
	/** 过期时间 */
	private Date	expiredTime;
	
	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Sets value.
	 *
	 * @param value the value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * Gets cache time.
	 *
	 * @return the cache time
	 */
	public Date getCacheTime() {
		return cacheTime;
	}
	
	/**
	 * Sets cache time.
	 *
	 * @param cacheTime the cache time
	 */
	public void setCacheTime(Date cacheTime) {
		this.cacheTime = cacheTime;
	}
	
	/**
	 * Gets expired time.
	 *
	 * @return the expired time
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}
	
	/**
	 * Sets expired time.
	 *
	 * @param expiredTime the expired time
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
}
