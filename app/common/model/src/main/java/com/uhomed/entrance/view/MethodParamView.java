package com.uhomed.entrance.view;

import lombok.Data;

import java.io.Serializable;

/**
 * 方法参数
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Data
public class MethodParamView implements Serializable {
	
	private static final long	serialVersionUID	= -5951749603241860767L;
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
	/** 默认值 */
	private String				defaultValue;
	/** 参数描述 */
	private String				paramDesc;
	
	private Integer				minLength;
	
	private String				resource;
	
}
