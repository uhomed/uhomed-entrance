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
	private Object				clazz;
	/** 最大长度（string时生效） */
	private Integer				length;
	/** 是否必传 */
	private boolean				require;
	/** 默认值 */
	private String				defaultValue;
	/** 包名类名 */
	private String				clazzStr;
	/** 参数名称 */
	private String				name;
	
	private Integer				minLength;
	
	/**
	 * Instantiates a new MethodDTO param cache dto.
	 *
	 * @param code the code
	 * @param clazz the clazz
	 * @param length the length
	 * @param require the require
	 * @param defaultValue the default value
	 * @param clazzStr the clazz str
	 * @param name the name
	 * @param minLength the min length
	 */
	public MethodParamCacheDTO(String code, Object clazz, Integer length, boolean require, String defaultValue, String clazzStr, String name,
			Integer minLength) {
		this.code = code;
		this.clazz = clazz;
		this.length = length;
		this.require = require;
		this.defaultValue = defaultValue;
		this.clazzStr = clazzStr;
		this.name = name;
		this.minLength = minLength;
	}
	
	/**
	 * Gets min length.
	 *
	 * @return the min length
	 */
	public Integer getMinLength() {
		return minLength;
	}
	
	/**
	 * Sets min length.
	 *
	 * @param minLength the min length
	 */
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
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
	 * Gets clazz str.
	 *
	 * @return the clazz str
	 */
	public String getClazzStr() {
		return clazzStr;
	}
	
	/**
	 * Sets clazz str.
	 *
	 * @param clazzStr the clazz str
	 */
	public void setClazzStr(String clazzStr) {
		this.clazzStr = clazzStr;
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
	public Object getClazz() {
		return clazz;
	}
	
	/**
	 * Sets clazz.
	 *
	 * @param clazz the clazz
	 */
	public void setClazz(Object clazz) {
		this.clazz = clazz;
	}
}
