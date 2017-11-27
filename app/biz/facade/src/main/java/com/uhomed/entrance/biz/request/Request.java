package com.uhomed.entrance.biz.request;

import com.alibaba.dubbo.rpc.RpcException;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.exception.ParamException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public interface Request {
	
	/**
	 * 调用
	 * 
	 * @param sso
	 * @param bizParams
	 * @param methodDTO
	 * @return
	 * @throws ParamException
	 * @throws RpcException
	 */
	Object request(String sso, String bizParams, MethodCacheDTO methodDTO, String router, String requestBody,
			HttpServletRequest request) throws ParamException, RpcException;
}
