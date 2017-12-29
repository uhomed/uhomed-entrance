package com.uhomed.entrance.biz.router;

import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public interface Router {

    /**
     * 路由规则，以及路由设置
     * @param methodDTO 方法详情
     * @param router 路由ip
     */
    void router(MethodCacheDTO methodDTO,String router);
}
