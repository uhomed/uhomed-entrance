/**  
 * @Title: ReflectUtil.java 
 * @author Administrator  
 * @date 2013-8-8 下午03:58:16 
 * @version V1.0   
 */
package com.uhomed.entrance.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

/** 
 * 注入接口方法util
 * @author lim
 * @ClassName: ReflectUtil 
 * @version 1.0 2013-8-9 上午10:11:20
 * @lastUpdateTime 2013-8-9 上午10:11:20 
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ReflectUtil {
	
	private static final Logger logger = Logger.getLogger(ReflectUtil.class);

	public static void setFieldValue(Object target, String fname, Class ftype,
			Object fvalue) {
		if (target == null
				|| fname == null
				|| "".equals(fname)
				|| (fvalue != null && !ftype
						.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod("set"
					+ Character.toUpperCase(fname.charAt(0))
					+ fname.substring(1), ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);

		} catch (Exception me) {
			if (logger.isDebugEnabled()) {
				logger.debug(me);
			}
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				if (logger.isDebugEnabled()) {
					logger.debug(fe);
				}
			}
		}
	}

	public static Object getFieldValue(Object target, String fname) {
		Object reslut = null;
		if (target == null || fname == null || "".equals(fname)) {
			return null;
		}
		Class clazz = target.getClass();
		try {
			Field field = clazz.getDeclaredField(fname);
			reslut = field.get(fname);

		} catch (Exception me) {
			if (logger.isDebugEnabled()) {
				logger.debug(me);
			}
		}
		return reslut;
	}

}
