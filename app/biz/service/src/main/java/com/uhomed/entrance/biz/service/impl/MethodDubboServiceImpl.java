package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.MethodDubboService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.MethodDubboDAO;
import com.uhomed.entrance.model.MethodDubbo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("methodDubboService")
public class MethodDubboServiceImpl extends BaseServiceImpl<MethodDubbo> implements MethodDubboService {

    @Resource
    private MethodDubboDAO methodDubboDAO;


    @PostConstruct
    public void init(){
        super.setBaseDao(methodDubboDAO);
    }
}
