package com.uhomed.entrance.biz.cache.dto;

import com.uhomed.entrance.biz.context.MethodParamContext;
import lombok.Data;

import java.io.Serializable;

/**
 * The type MethodDTO param cache dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Data
public class MethodParamCacheDTO implements Serializable {
	
	private static final long			serialVersionUID	= 6246815945348911291L;
	
	/** 参数名 */
	private String						code;
	
	private Object						clazz;
	/** 最大长度（string时生效） */
	private Integer						length;
	/** 是否必传 */
	private boolean						require;
	/** 默认值 */
	private String						defaultValue;
	/** 包名类名 */
	private String						clazzStr;
	/** 参数名称 */
	private String						name;
	
	private Integer						minLength;

	private MethodParamContext.Resource	resource;
	
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
	public MethodParamCacheDTO(String code, Object clazz, Integer length, boolean require, String defaultValue,
			String clazzStr, String name, Integer minLength, MethodParamContext.Resource resource) {
		this.code = code;
		this.clazz = clazz;
		this.length = length;
		this.require = require;
		this.defaultValue = defaultValue;
		this.clazzStr = clazzStr;
		this.name = name;
		this.minLength = minLength;
		this.resource = resource;
	}
	
}
