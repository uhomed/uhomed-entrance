package com.uhomed.entrance.biz.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.druid.util.StringUtils;
import com.uhomed.entrance.biz.cache.dto.MethodDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.context.MethodTypeContext;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Component("methodCache")
public class MethodCache {


    private ConcurrentHashMap<String,MethodCacheDTO> methodCache = new ConcurrentHashMap<>();


    public MethodCacheDTO getMethod(String apiMethodCode,String apiMethodVersion){
        return methodCache.get(this.buildKey(apiMethodCode,apiMethodVersion));
    }

    public void putMethod(Integer id,String apiMethodCode, String apiMethodVersion, String status, String verifiSso, MethodTypeContext type, String mode, List<MethodParamCacheDTO> params,MethodDTO method){
        MethodCacheDTO dto = new MethodCacheDTO(id,apiMethodCode, apiMethodVersion, StringUtils.equals(status,"Y"), StringUtils.equals(verifiSso,"Y"), type, mode,params,method);
        GenericServiceFactory.buildGenericService(dto);
        this.methodCache.put(this.buildKey(apiMethodCode,apiMethodVersion),dto);
    }

    public String buildKey(String apiMethodCode,String apiMethodVersion){
        return Joiner.on("_").join(apiMethodCode,apiMethodVersion);
    }

    public void putMethodParams(String apiMethodCode,String apiMethodVersion,List<MethodParamCacheDTO> params){
        MethodCacheDTO cache = this.methodCache.get(this.buildKey(apiMethodCode,apiMethodVersion));
        cache.setParams(params);
    }


}
