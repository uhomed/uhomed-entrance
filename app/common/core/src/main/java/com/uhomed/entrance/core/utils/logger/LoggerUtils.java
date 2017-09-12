/**
 * mario.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.uhomed.entrance.core.utils.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * 
 * @author liming
 * @version $Id: LoggerUtils.java, v 0.1 2016年9月13日 下午3:35:18 liming Exp $
 */
public class LoggerUtils {
	
	private static final Logger DEFAULT_LOGGER = Logger.getLogger(LoggerUtils.class);
	
	public static void defaultPrint(Throwable e){
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        DEFAULT_LOGGER.error(str);
        try{
        	sw.close();
        } catch (IOException ie){
        }
	}
	
	public static void defaultPrint(Throwable e,String message){
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        DEFAULT_LOGGER.error(message + "\n" + str,e);
        try{
        	sw.close();
        } catch (IOException ie){
        }
	}
	
	public static void print(Throwable e,String message ,Logger log){
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        log.error(message + "\n" +str,e);
        try{
        	sw.close();
        } catch (IOException ie){
        }
	}
	

}
