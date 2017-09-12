package com.uhomed.entrance.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 分组
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class GroupView implements Serializable {
	
	private static final long	serialVersionUID	= -2386195253429163636L;
	/** code */
	private String				code;
	/** 名称 */
	private String				name;
	/** 方法数量 */
	private Integer				methodCount;
	/** 创建时间 */
	private Date				createTime;
	
	/**
	 * Instantiates a new Group view.
	 *
	 * @param code the code
	 * @param name the name
	 * @param methodCount the method count
	 * @param createTime the create time
	 */
	public GroupView(String code, String name, Integer methodCount, Date createTime) {
		this.code = code;
		this.name = name;
		this.methodCount = methodCount;
		this.createTime = createTime;
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
	
	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets method count.
	 *
	 * @return the method count
	 */
	public Integer getMethodCount() {
		return methodCount;
	}
	
	/**
	 * Sets method count.
	 *
	 * @param methodCount the method count
	 */
	public void setMethodCount(Integer methodCount) {
		this.methodCount = methodCount;
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
}
