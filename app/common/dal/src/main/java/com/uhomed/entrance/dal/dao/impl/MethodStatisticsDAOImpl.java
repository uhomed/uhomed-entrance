package com.uhomed.entrance.dal.dao.impl;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.MethodStatisticsDAO;
import com.uhomed.entrance.model.MethodStatistics;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("methodStatisticsDAO")
public class MethodStatisticsDAOImpl extends BaseDAOImpl<MethodStatistics> implements MethodStatisticsDAO{


    public MethodStatisticsDAOImpl() {
        super(MethodStatistics.class);
    }
}
