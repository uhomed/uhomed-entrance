package com.uhomed.entrance.dal.dao.impl;

import org.springframework.stereotype.Component;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.OnlineParamsDAO;
import com.uhomed.entrance.model.OnlineParams;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Component("onlineParamsDAO")
public class OnlineParamsDAOImpl extends BaseDAOImpl<OnlineParams> implements OnlineParamsDAO{


    /*** 构造函数
     * @param */
    public OnlineParamsDAOImpl() {
        super(OnlineParams.class);
    }
}
