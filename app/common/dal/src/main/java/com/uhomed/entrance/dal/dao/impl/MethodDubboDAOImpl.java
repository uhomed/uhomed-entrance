package com.uhomed.entrance.dal.dao.impl;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.MethodDubboDAO;
import com.uhomed.entrance.model.MethodDubbo;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("methodDubboDAO")
public class MethodDubboDAOImpl extends BaseDAOImpl<MethodDubbo> implements MethodDubboDAO {


    /*** 构造函数
     * @param */
    public MethodDubboDAOImpl() {
        super(MethodDubbo.class);
    }
}
