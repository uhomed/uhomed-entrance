package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.MethodStatisticsService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.MethodStatisticsDAO;
import com.uhomed.entrance.model.MethodStatistics;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("methodStatistics")
public class MethodStatisticsServiceImpl extends BaseServiceImpl<MethodStatistics> implements MethodStatisticsService {

    @Resource
    private MethodStatisticsDAO methodStatisticsDAO;

    @PostConstruct
    public void init(){
        super.setBaseDao(methodStatisticsDAO);
    }

}
