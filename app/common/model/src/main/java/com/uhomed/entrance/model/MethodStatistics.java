package com.uhomed.entrance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 方法调用统计
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodStatistics implements Serializable {
	
	private static final long	serialVersionUID	= -4121055377807809446L;
	/** 主键id */
	private Long				id;
	/** 方法code */
	private String				methodCode;
	/** bizParam */
	private String				requestParam;
	/** 状态 */
	private String				status;
	/** 请求时间 */
	private Date				createTime;
	/** 耗时，ms */
	private Integer				holdTime;
	/** 接口版本号 */
	private String				methodVersion;
	
	/**
	 * Gets method version.
	 *
	 * @return the method version
	 */
	public String getMethodVersion() {
		return methodVersion;
	}
	
	/**
	 * Sets method version.
	 *
	 * @param methodVersion the method version
	 */
	public void setMethodVersion(String methodVersion) {
		this.methodVersion = methodVersion;
	}
	
	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets method code.
	 *
	 * @return the method code
	 */
	public String getMethodCode() {
		return methodCode;
	}
	
	/**
	 * Sets method code.
	 *
	 * @param methodCode the method code
	 */
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	
	/**
	 * Gets request param.
	 *
	 * @return the request param
	 */
	public String getRequestParam() {
		return requestParam;
	}
	
	/**
	 * Sets request param.
	 *
	 * @param requestParam the request param
	 */
	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	
	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets create time.
	 *
	 * @return the create time
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * Sets create time.
	 *
	 * @param createTime the create time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * Gets hold time.
	 *
	 * @return the hold time
	 */
	public Integer getHoldTime() {
		return holdTime;
	}
	
	/**
	 * Sets hold time.
	 *
	 * @param holdTime the hold time
	 */
	public void setHoldTime(Integer holdTime) {
		this.holdTime = holdTime;
	}
}
