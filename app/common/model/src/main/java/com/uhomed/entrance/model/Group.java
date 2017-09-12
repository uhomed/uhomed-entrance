package com.uhomed.entrance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Group.
 *  分组
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class Group implements Serializable {
	
	private static final long	serialVersionUID	= -6614623074795156597L;
	/** 主键code */
	private String				code;
	/** 分组名称 */
	private String				name;
	/** 创建时间 */
	private Date				createTime;
	
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
