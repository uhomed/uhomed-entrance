package com.uhomed.entrance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Method info.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
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
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
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
	 * Gets api method code.
	 *
	 * @return the api method code
	 */
	public String getApiMethodCode() {
		return apiMethodCode;
	}
	
	/**
	 * Sets api method code.
	 *
	 * @param apiMethodCode the api method code
	 */
	public void setApiMethodCode(String apiMethodCode) {
		this.apiMethodCode = apiMethodCode;
	}
	
	/**
	 * Gets api method name.
	 *
	 * @return the api method name
	 */
	public String getApiMethodName() {
		return apiMethodName;
	}
	
	/**
	 * Sets api method name.
	 *
	 * @param apiMethodName the api method name
	 */
	public void setApiMethodName(String apiMethodName) {
		this.apiMethodName = apiMethodName;
	}
	
	/**
	 * Gets api method version.
	 *
	 * @return the api method version
	 */
	public String getApiMethodVersion() {
		return apiMethodVersion;
	}
	
	/**
	 * Sets api method version.
	 *
	 * @param apiMethodVersion the api method version
	 */
	public void setApiMethodVersion(String apiMethodVersion) {
		this.apiMethodVersion = apiMethodVersion;
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
	 * Gets verifi sso.
	 *
	 * @return the verifi sso
	 */
	public String getVerifiSso() {
		return verifiSso;
	}
	
	/**
	 * Sets verifi sso.
	 *
	 * @param verifiSso the verifi sso
	 */
	public void setVerifiSso(String verifiSso) {
		this.verifiSso = verifiSso;
	}
	
	/**
	 * Gets mode.
	 *
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	
	/**
	 * Sets mode.
	 *
	 * @param mode the mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
	 * Gets method desc.
	 *
	 * @return the method desc
	 */
	public String getMethodDesc() {
		return methodDesc;
	}
	
	/**
	 * Sets method desc.
	 *
	 * @param methodDesc the method desc
	 */
	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
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
