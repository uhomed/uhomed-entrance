package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.MethodInfoService;
import com.uhomed.entrance.core.service.BaseService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.MethodInfoDAO;
import com.uhomed.entrance.model.MethodInfo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("methodInfoService")
public class MethodInfoServiceImpl extends BaseServiceImpl<MethodInfo> implements MethodInfoService{


    @Resource
    private MethodInfoDAO methodInfoDAO;

    @PostConstruct
    public void init(){
        super.setBaseDao(methodInfoDAO);
    }

}
