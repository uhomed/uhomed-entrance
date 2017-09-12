/**
 * created liming.lm 2014年9月28日
 */
package com.uhomed.entrance.dal.result;

import java.io.Serializable;

/**
 * 通用返回对象
 * @author liming.lm
 * @version $Id:ResultObject.java,v 0.1 2014年9月28日 上午9:20:11 liming.lm Exp $
 */
public class Result<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7048572285301850293L;
	/** 数据 */
	private T data;
	/** 结果消息 */
	private String message;
	/** 结果 */
	private boolean success;
	/** 编码 */
	private int code;
	/** 异常信息 */
	private Exception exception;

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Result(){
		super();
	}
	
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code value to be assigned to property code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
}
