package com.uhomed.entrance.model;

import java.io.Serializable;

/**
 * The type Online params.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class OnlineParams implements Serializable {
	
	private static final long	serialVersionUID	= 5918625434882825077L;
	/** 参数key，唯一 */
	private String				paramCode;
	/** 参数value */
	private String				paramValue;
	/** 备注 */
	private String				remark;
	/** 分组code */
	private String				groupCode;
	
	/**
	 * Gets group code.
	 *
	 * @return the group code
	 */
	public String getGroupCode() {
		return groupCode;
	}
	
	/**
	 * Sets group code.
	 *
	 * @param groupCode the group code
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	/**
	 * Gets param code.
	 *
	 * @return the param code
	 */
	public String getParamCode() {
		return paramCode;
	}
	
	/**
	 * Sets param code.
	 *
	 * @param paramCode the param code
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	/**
	 * Gets param value.
	 *
	 * @return the param value
	 */
	public String getParamValue() {
		return paramValue;
	}
	
	/**
	 * Sets param value.
	 *
	 * @param paramValue the param value
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	/**
	 * Gets remark.
	 *
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * Sets remark.
	 *
	 * @param remark the remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
