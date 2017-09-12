package com.uhomed.entrance.dal.dao.impl;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.MethodInfoDAO;
import com.uhomed.entrance.model.MethodInfo;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("methodInfoDAO")
public class MethodInfoDAOImpl extends BaseDAOImpl<MethodInfo> implements MethodInfoDAO{

    public MethodInfoDAOImpl() {
        super(MethodInfo.class);
    }
}
