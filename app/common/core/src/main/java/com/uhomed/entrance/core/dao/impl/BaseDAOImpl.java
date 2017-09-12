/**  
 * @Title: BaseDao.java 
 * @date 2013-7-30 下午01:30:32 
 * @version V1.0   
 */
package com.uhomed.entrance.core.dao.impl;

import com.uhomed.entrance.core.dao.BaseDAO;
import com.uhomed.entrance.core.utils.LoggerFactary;
import com.uhomed.entrance.core.utils.PropertyUtil;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础dao实现
 * 
 * @param <T>
 * @author liming
 * @version $Id$
 * @since 2.0
 * @date 2013-6-25 下午04:54:12
 * @updateInfo
 */
public abstract class BaseDAOImpl<T> extends BaseDAOSupport<T> implements
		BaseDAO<T> {

	/*** 构造函数 */
	public BaseDAOImpl(Class<T> clazz) {
		super(clazz);
	}

	/** 日志 */
	protected static final Logger logger = LoggerFactary
			.getLogger(BaseDAOImpl.class.getSimpleName());

	/**
	 * 创建po
	 * 
	 * @param t
	 * @throws Exception
	 * @lastUpdateTime 2013-6-25 下午04:44:11
	 * @updateInfo
	 */
	public Serializable createPO(T t) throws Exception {
		try {
			if (PropertyUtil.containsField(t.getClass(), "createTime")) {
				PropertyUtil.setProperty(t, "createTime", new Date());
			}
			if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
				PropertyUtil.setProperty(t, "modifyTime", new Date());
			}
		} catch (Exception e) {
			logger.error("can't get the property createTime in "
					+ t.getClass().getSimpleName());
		}
		return super.createPO(t);
	}

	/**
	 * 更新po
	 * 
	 * @param t
	 * @lastUpdateTime 2013-6-25 下午04:45:12
	 * @updateInfo
	 */
	public int updatePO(T t) throws Exception {
		try {
			if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
				PropertyUtil.setProperty(t, "modifyTime", new Date());
			}
		} catch (Exception e) {
			logger.error("can't get the property modifiTime in "
					+ t.getClass().getSimpleName());
		}
		return super.updatePO(t);
	}
}
