/**  
 * @Title: PropertyUtil.java 
 * @author Administrator  
 * @date 2013-8-1 下午04:59:45 
 * @version V1.0   
 */
package com.uhomed.entrance.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 属性工具类
 * @author liming
 * @version $Id$   
 * @since 2.0
 * @date 2013-6-25 下午05:22:46
 * @updateInfo
 */
public class PropertyUtil {
	/**
	 * 设置相关bean的属性值
	 * @param bean javabean的对象实例
	 * @param name 属性名称
	 * @param value 值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		PropertyUtils.setProperty(bean, name, value);
	}

	public static Object getProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return PropertyUtils.getProperty(bean, name);
	}

	/**
	 * 判断Bean中是否包含指定的属性
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	public static boolean containsField(Class<?> clazz, String fieldName) {
		Field field = getClassField(clazz, fieldName);
		if (field != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获得指定class的Field
	 * @param clazz 指定的class信息
	 * @param fieldName field名称
	 * @return
	 */
	public static Field getClassField(Class<?> clazz, String fieldName) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != Object.class) {
			return getClassField(superclass, fieldName);
		}
		return null;
	}

}
