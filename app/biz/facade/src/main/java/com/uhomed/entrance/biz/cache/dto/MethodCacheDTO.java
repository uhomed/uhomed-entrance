package com.uhomed.entrance.biz.cache.dto;

import com.uhomed.entrance.biz.context.MethodTypeContext;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type MethodDTO cache dto.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Data
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
	private MethodDTO					methodInfo;
	/** 是否缓存 */
	private boolean						cache;
	/** 缓存秒数 */
	private Integer						second;
	/** 1=并发控制，0=不控制 */
	private Integer						concurrent;
	
	/**
	 * Instantiates a new MethodDTO cache dto.
	 *
	 * @param id the id
	 * @param apiMethodCode the api method code
	 * @param apiMethodVersion the api method version
	 * @param status the status
	 * @param verifiSso the verifi sso
	 * @param type the type
	 * @param mode the mode
	 * @param params the params
	 * @param methodInfo the method info
	 * @param cache the cache
	 * @param second the second
	 */
	public MethodCacheDTO(Integer id, String apiMethodCode, String apiMethodVersion, boolean status, boolean verifiSso,
			MethodTypeContext type, String mode, List<MethodParamCacheDTO> params, MethodDTO methodInfo, boolean cache,
			Integer second,Integer concurrent) {
		this.id = id;
		this.apiMethodCode = apiMethodCode;
		this.apiMethodVersion = apiMethodVersion;
		this.status = status;
		this.verifiSso = verifiSso;
		this.type = type;
		this.mode = mode;
		this.params = params;
		this.methodInfo = methodInfo;
		this.cache = cache;
		this.second = second;
		this.concurrent = concurrent;
	}
	

}
