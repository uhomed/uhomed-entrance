package com.uhomed.entrance.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Method info.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Data
public class MethodInfo implements Serializable {
	
	private static final long	serialVersionUID	= 5486656479036401418L;
	/** id */
	private Integer				id;
	/** 方法code */
	private String				apiMethodCode;
	/** 方法名 */
	private String				apiMethodName;
	/** 版本号 */
	private String				apiMethodVersion;
	/** 状态码 */
	private String				status;
	/** 校验sso */
	private String				verifiSso;
	/** 请求方式 */
	private String				mode;
	/** 方法描述 */
	private String				methodDesc;
	/** 方法类型 */
	private String				type;
	/** 创建时间 */
	private Date				createTime;
	/** 分组code */
	private String				groupCode;
	/** 是否缓存 */
	private String				cache;
	/** 缓存秒数 */
	private Integer				second;
	/** 并发控制 */
	private Integer				concurrent;

}
