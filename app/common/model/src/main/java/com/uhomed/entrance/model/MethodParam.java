package com.uhomed.entrance.model;

import java.io.Serializable;

/**
 * 方法参数
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodParam implements Serializable {
	
	private static final long	serialVersionUID	= -130188381168190785L;
	/** 主键id */
	private Integer				id;
	/** 方法名 */
	private Integer				methodId;
	/** 参数code */
	private String				paramCode;
	/** 参数名 */
	private String				paramName;
	/** 参数类型 */
	private String				paramType;
	/** 参数顺序 */
	private Integer				paramIndex;
	/** 是否必传 */
	private String				paramRequire;
	/** 参数长度限制 */
	private Integer				length;
	
	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	 * Gets param name.
	 *
	 * @return the param name
	 */
	public String getParamName() {
		return paramName;
	}
	
	/**
	 * Sets param name.
	 *
	 * @param paramName the param name
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	/**
	 * Gets param type.
	 *
	 * @return the param type
	 */
	public String getParamType() {
		return paramType;
	}
	
	/**
	 * Sets param type.
	 *
	 * @param paramType the param type
	 */
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
	/**
	 * Gets param index.
	 *
	 * @return the param index
	 */
	public Integer getParamIndex() {
		return paramIndex;
	}
	
	/**
	 * Sets param index.
	 *
	 * @param paramIndex the param index
	 */
	public void setParamIndex(Integer paramIndex) {
		this.paramIndex = paramIndex;
	}
	
	/**
	 * Gets param require.
	 *
	 * @return the param require
	 */
	public String getParamRequire() {
		return paramRequire;
	}
	
	/**
	 * Sets param require.
	 *
	 * @param paramRequire the param require
	 */
	public void setParamRequire(String paramRequire) {
		this.paramRequire = paramRequire;
	}
	
	/**
	 * Gets length.
	 *
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	
	/**
	 * Sets length.
	 *
	 * @param length the length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
}
