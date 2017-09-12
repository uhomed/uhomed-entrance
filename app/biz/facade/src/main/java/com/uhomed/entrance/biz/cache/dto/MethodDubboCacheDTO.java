package com.uhomed.entrance.biz.cache.dto;

import java.io.Serializable;

/**
 * The type MethodDTO dubbo cache dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodDubboCacheDTO implements Serializable {
	
	/** 类名 */
	private String	classPath;
	/** 方法名 */
	private String	methodName;
	
	/**
	 * Gets class path.
	 *
	 * @return the class path
	 */
	public String getClassPath() {
		return classPath;
	}
	
	/**
	 * Sets class path.
	 *
	 * @param classPath the class path
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	/**
	 * Gets method name.
	 *
	 * @return the method name
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Sets method name.
	 *
	 * @param methodName the method name
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
