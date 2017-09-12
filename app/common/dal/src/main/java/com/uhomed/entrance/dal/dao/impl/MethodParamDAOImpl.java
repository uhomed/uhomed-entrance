package com.uhomed.entrance.dal.dao.impl;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.MethodParamDAO;
import com.uhomed.entrance.model.MethodParam;
import org.springframework.stereotype.Repository;

/**
 * The type Method param dao.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Repository("methodParamDAO")
public class MethodParamDAOImpl extends BaseDAOImpl<MethodParam> implements MethodParamDAO {
	/***
	 * 构造函数
	 * 
	 * @param
	 */
	public MethodParamDAOImpl() {
		super( MethodParam.class );
	}
}
