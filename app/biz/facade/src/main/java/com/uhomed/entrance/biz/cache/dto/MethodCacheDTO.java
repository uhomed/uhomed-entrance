package com.uhomed.entrance.biz.cache.dto;

import com.uhomed.entrance.biz.context.MethodTypeContext;

import java.io.Serializable;
import java.util.List;

/**
 * The type MethodDTO cache dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
public class MethodCacheDTO implements Serializable {
	
	private static final long			serialVersionUID	= -5839329860068411060L;
	
	/** 主键id */
	private Integer						id;
	/** 方法code */
	private String						apiMethodCode;
	/** 版本号 */
	private String						apiMethodVersion;
	/** Y=open,N=close */
	private boolean						status;
	/** Y=yes,N=no */
	private boolean						verifiSso;
	/** 方法类型 */
	private MethodTypeContext			type;
	/** 请求方式 */
	private String						mode;
	/** 参数集合 */
	private List<MethodParamCacheDTO>	params;
	/** dubbo方法信息 */
	private MethodDTO methodInfo;
	
	/**
	 * Instantiates a new MethodDTO cache dto.
	 *
	 * @param apiMethodCode the api method code
	 * @param apiMethodVersion the api method version
	 * @param status the status
	 * @param verifiSso the verifi sso
	 * @param type the type
	 * @param mode the mode
	 * @param params the params
	 */
	public MethodCacheDTO(Integer id,String apiMethodCode, String apiMethodVersion, boolean status, boolean verifiSso, MethodTypeContext type, String mode,
			List<MethodParamCacheDTO> params,MethodDTO methodInfo) {
		this.id = id ;
		this.apiMethodCode = apiMethodCode;
		this.apiMethodVersion = apiMethodVersion;
		this.status = status;
		this.verifiSso = verifiSso;
		this.type = type;
		this.mode = mode;
		this.params = params;
		this.methodInfo = methodInfo;
	}


	
	/**
	 * Gets method info.
	 *
	 * @return the method info
	 */
	public MethodDTO getMethodInfo() {
		return methodInfo;
	}
	
	/**
	 * Sets method info.
	 *
	 * @param methodInfo the method info
	 */
	public void setMethodInfo(MethodDTO methodInfo) {
		this.methodInfo = methodInfo;
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
	 * Is status boolean.
	 *
	 * @return the boolean
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Sets status.
	 *
	 * @param status the status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Is verifi sso boolean.
	 *
	 * @return the boolean
	 */
	public boolean isVerifiSso() {
		return verifiSso;
	}
	
	/**
	 * Sets verifi sso.
	 *
	 * @param verifiSso the verifi sso
	 */
	public void setVerifiSso(boolean verifiSso) {
		this.verifiSso = verifiSso;
	}
	
	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public MethodTypeContext getType() {
		return type;
	}
	
	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(MethodTypeContext type) {
		this.type = type;
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
	 * Gets params.
	 *
	 * @return the params
	 */
	public List<MethodParamCacheDTO> getParams() {
		return params;
	}
	
	/**
	 * Sets params.
	 *
	 * @param params the params
	 */
	public void setParams(List<MethodParamCacheDTO> params) {
		this.params = params;
	}
}
