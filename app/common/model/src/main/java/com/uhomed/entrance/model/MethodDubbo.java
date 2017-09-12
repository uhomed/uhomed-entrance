package com.uhomed.entrance.model;

import java.io.Serializable;

/**
 * The type Method dubbo.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodDubbo implements Serializable {
	
	private static final long	serialVersionUID	= -2475023589582369873L;
	/** 方法id */
	private Integer				methodId;
	/** 路径 */
	private String				classPath;
	/** 方法名 */
	private String				methodName;
	
	/**
	 * Gets method id.
	 *
	 * @return the method id
	 */
	public Integer getMethodId() {
		return methodId;
	}
	
	/**
	 * Sets method id.
	 *
	 * @param methodId the method id
	 */
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	
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
