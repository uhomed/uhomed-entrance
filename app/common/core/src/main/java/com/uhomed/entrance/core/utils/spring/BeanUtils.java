/**
 * @date 2014年9月15日 上午9:22:43
 * @author lim
 * @email qq79474256.cool@163.com
 * @version V1.0 
 */
package com.uhomed.entrance.core.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring bean utils
 * 
 * @date 2014年9月15日 上午9:22:43
 * @author lim
 * @email qq79474256.cool@163.com
 * @version V1.0
 */
public class BeanUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazs) {
		return clazs.cast(getBean(beanName));
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BeanUtils.applicationContext = applicationContext;
	}

}
