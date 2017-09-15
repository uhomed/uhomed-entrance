/**
 * created liming.lm 2014年9月28日
 */
package com.uhomed.entrance;

import java.io.Serializable;

/**
 * 通用返回对象
 *
 * @param <T> the type parameter
 * @author liming.lm
 * @version $Id :ResultObject.java,v 0.1 2014年9月28日 上午9:20:11 liming.lm Exp $
 */
public class ResultModel<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7048572285301850293L;
	/** 数据 */
	private T					data;
	/** 结果消息 */
	private String				message;
	/** 结果 */
	private boolean				success;
	/** 编码 */
	private String				code;
	/** 异常信息 */
	private Exception			exception;
	
	/**
	 * Gets exception.
	 *
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}
	
	/**
	 * Sets exception.
	 *
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	/**
	 * Instantiates a new Result model.
	 */
	public ResultModel() {
		super();
	}
	
	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Sets data.
	 *
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Is success boolean.
	 *
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * Sets success.
	 *
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
