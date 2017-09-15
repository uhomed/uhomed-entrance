package com.uhomed.entrance.biz.cache.dto;

import java.io.Serializable;

/**
 * The type MethodDTO param cache dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodParamCacheDTO implements Serializable {
	
	private static final long	serialVersionUID	= 6246815945348911291L;
	
	/** 参数名 */
	private String				code;
	/** 参数类型 */
	private String				clazz;
	/** 最大长度（string时生效） */
	private Integer				length;
	/** 是否必传 */
	private boolean				require;
	/** 默认值 */
	private String				defaultValue;
	
	/**
	 * Instantiates a new MethodDTO param cache dto.
	 *
	 * @param code the code
	 * @param clazz the clazz
	 * @param length the length
	 * @param require the require
	 * @param defaultValue the default value
	 */
	public MethodParamCacheDTO(String code, String clazz, Integer length, boolean require, String defaultValue) {
		this.code = code;
		this.clazz = clazz;
		this.length = length;
		this.require = require;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Gets default value.
	 *
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	
	/**
	 * Sets default value.
	 *
	 * @param defaultValue the default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	 * Sets clazz.
	 *
	 * @param clazz the clazz
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
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
	
	/**
	 * Is require boolean.
	 *
	 * @return the boolean
	 */
	public boolean isRequire() {
		return require;
	}
	
	/**
	 * Sets require.
	 *
	 * @param require the require
	 */
	public void setRequire(boolean require) {
		this.require = require;
	}
	
	/**
	 * Gets clazz.
	 *
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	
}
