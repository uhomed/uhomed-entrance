package com.uhomed.entrance.biz.context;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class MethodParamContext {

    /**
     * 参数获取来源
     */
    public enum Resource{
        BIZ_PARAMS,REQUEST_BODY,URL,HEADERS
    }
}
