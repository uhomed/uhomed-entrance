package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.MethodParamService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.MethodParamDAO;
import com.uhomed.entrance.model.MethodParam;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("methodParamService")
public class MethodParamServiceImpl extends BaseServiceImpl<MethodParam> implements MethodParamService{


    @Resource
    private MethodParamDAO methodParamDAO;

    @PostConstruct
    public void init(){
        super.setBaseDao(this.methodParamDAO);
    }
}
