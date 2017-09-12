package com.uhomed.entrance.view;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Method info view.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodInfoView implements Serializable {
	
	private static final long	serialVersionUID	= 1621953895388343610L;
	/** 主键id */
	private Integer				id;
	/** 方法code */
	private String				apiMethodCode;
	/** 方法名 */
	private String				apiMethodName;
	/** 方法版本号 */
	private String				apiMethodVersion;
	/** 状态 */
	private String				status;
	/** 状态中文 */
	private String				statusCH;
	/** 是否需要检验sso */
	private String				verifiSso;
	/** 明文 */
	private String				verifiSsoCH;
	/** 请求方式 */
	private String				mode;
	/** 接口类型 */
	private String				type;
	/** 创建时间 */
	private Date				createTime;
	/** 参数数量 */
	private Integer				paramCount;
	
	/**
	 * Instantiates a new Method info view.
	 *
	 * @param id the id
	 * @param apiMethodCode the api method code
	 * @param apiMethodName the api method name
	 * @param apiMethodVersion the api method version
	 * @param status the status
	 * @param statusCH the status ch
	 * @param verifiSso the verifi sso
	 * @param verifiSsoCH the verifi sso ch
	 * @param mode the mode
	 * @param type the type
	 * @param createTime the create time
	 * @param paramCount the param count
	 */
	public MethodInfoView(Integer id, String apiMethodCode, String apiMethodName, String apiMethodVersion, String status, String statusCH,
			String verifiSso, String verifiSsoCH, String mode, String type, Date createTime, Integer paramCount) {
		this.id = id;
		this.apiMethodCode = apiMethodCode;
		this.apiMethodName = apiMethodName;
		this.apiMethodVersion = apiMethodVersion;
		this.status = status;
		this.statusCH = statusCH;
		this.verifiSso = verifiSso;
		this.verifiSsoCH = verifiSsoCH;
		this.mode = mode;
		this.type = type;
		this.createTime = createTime;
		this.paramCount = paramCount;
	}
	
	/**
	 * Gets param count.
	 *
	 * @return the param count
	 */
	public Integer getParamCount() {
		return paramCount;
	}
	
	/**
	 * Sets param count.
	 *
	 * @param paramCount the param count
	 */
	public void setParamCount(Integer paramCount) {
		this.paramCount = paramCount;
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
	 * Gets status ch.
	 *
	 * @return the status ch
	 */
	public String getStatusCH() {
		return statusCH;
	}
	
	/**
	 * Sets status ch.
	 *
	 * @param statusCH the status ch
	 */
	public void setStatusCH(String statusCH) {
		this.statusCH = statusCH;
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
	 * Gets verifi sso ch.
	 *
	 * @return the verifi sso ch
	 */
	public String getVerifiSsoCH() {
		return verifiSsoCH;
	}
	
	/**
	 * Sets verifi sso ch.
	 *
	 * @param verifiSsoCH the verifi sso ch
	 */
	public void setVerifiSsoCH(String verifiSsoCH) {
		this.verifiSsoCH = verifiSsoCH;
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
