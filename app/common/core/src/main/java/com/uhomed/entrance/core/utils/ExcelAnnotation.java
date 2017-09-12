/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.uhomed.entrance.core.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author jing
 * @version $Id: ExcelAnnocation.java, v 0.1 2015年3月25日 下午5:45:17 jing Exp $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface ExcelAnnotation{
	/**
	 * 在Excel要显示的字段名
	 * @return
	 */
	public abstract String name() default "列";
}

