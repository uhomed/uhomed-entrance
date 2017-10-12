package com.uhomed.entrance.biz.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.TypeUtils;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import com.uhomed.entrance.biz.exception.ParamException;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.util.*;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class RequestUtil {

    public static Map<String,Object> convertParams(String bizParams,MethodCacheDTO methodDTO) throws ParamException{
        Map<String,Object> result = new HashMap<>();
        if(CollectionUtil.isEmpty(methodDTO.getParams())){
            return result;
        }

        List<String> types = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        List<MethodParamCacheDTO> paramList = methodDTO.getParams();
        JSONObject json = null;
        try {
            json = JSONObject.parseObject( bizParams );
        } catch (Exception e) {
            throw new ParamException( "bizParams解析错误！" );
        }
        for (MethodParamCacheDTO p : paramList) {
            types.add(p.getClazzStr());
            // 是否是自定义对象
            try {
                Object value = json.get( p.getCode() );
                if (value != null && value instanceof JSONArray) {
                    values.add(value);
                } else if (p.getClazz() instanceof Number || p.getClazz() instanceof Date || p.getClazz() instanceof String
                        || p.getClazz() instanceof Boolean || p.getClazz() instanceof Collection) {
                    // value = json.getObject( p.getCode(), p.getClazz().getClass() );
                    value = TypeUtils.castToJavaBean( value, p.getClazz().getClass() );

                    if (StrUtil.isNotEmpty( p.getDefaultValue() ) && value == null) {
                        value = p.getDefaultValue();
                    }

                    // 是否必传并且为空时直接抛出异常
                    if (p.isRequire() && value == null) {
                        throw new ParamException( p.getName() + "不能为空！" );
                    }

                    // 验证value长度
                    if (value instanceof String) {
                        String tempValue = String.valueOf( value );
                        if (p.getLength() != 0 && p.getLength() < tempValue.length()) {
                            throw new ParamException( p.getName() + "长度大于" + p.getLength() + "！" );
                        } else if (p.getMinLength() != 0 && tempValue.length() < p.getMinLength()) {
                            throw new ParamException( p.getName() + "长度小于" + p.getLength() + "！" );
                        }
                    }
                    values.add(value);
                } else {
                    Map<String, Object> domain = JSON.parseObject( json.getString( p.getCode() ), new TypeReference<Map<String, Object>>() {
                    } );
                    values.add(domain);
                }
            } catch (NullPointerException e) {
                if (p.isRequire()) {
                    throw new ParamException( p.getCode() + "不能为空！" );
                }
            }
        }
        result.put("types",types);
        result.put("values",values);
        return result;
    }

}
