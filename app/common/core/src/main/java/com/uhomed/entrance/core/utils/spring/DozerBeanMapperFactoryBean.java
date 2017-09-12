package com.uhomed.entrance.core.utils.spring;


import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;


/**
 * 重写Dozer的spring实现
 * @author lim
 * @version $Id$   
 * @since 2.0
 * @date 2013-6-28 下午01:43:20
 * @updateInfo
 */
public class DozerBeanMapperFactoryBean extends DozerBeanMapper {

	private final static Logger logger = Logger.getLogger(DozerBeanMapperFactoryBean.class);

	
	/**
	 *初始化
	 */
	public void init() {
		if (logger.isInfoEnabled()) {
			logger.info("init dozer config.");
		}
		super.getMappingProcessor();
	}
	
	
}
