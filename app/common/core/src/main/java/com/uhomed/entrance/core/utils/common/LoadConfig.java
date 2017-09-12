package com.uhomed.entrance.core.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 加载sererInfo.propertie配置文件 配置文件存放目录为class目录
 * 
 * @author lim
 * 
 */
public class LoadConfig {

	private Properties serverInfo = null;

	private static LoadConfig loadServiceInfo = new LoadConfig();

//	private final static String DEFAULT_NAME = "/root/antx.properties";
	
//	private final static String DEFAULT_NAME = "D:/workspace/antx.properties";
	//config antx地址
	private final static String DEFAULT_NAME = "/Users/liming/conf/uhomed-entrance/antx.properties";

	public LoadConfig() {
		this.init(DEFAULT_NAME);
	}

	public LoadConfig(String filePath) {
		this.init(filePath);
	}

	private void init(String filePath) {
//		File propertiesFile = new File(LoadConfig.class.getClassLoader()
//				.getResource(filePath).getPath());
		File propertiesFile = new File(filePath);
		if (propertiesFile.isFile()) {
			try {
				serverInfo = new Properties();
				FileInputStream fis = new FileInputStream(propertiesFile);
				serverInfo.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建实例
	 */
	private static synchronized void creatInstance() {
		loadServiceInfo = new LoadConfig();
	}

	/**
	 * 获取
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (serverInfo != null) {
			Object value = serverInfo.get(key);
			if(value != null){
				return value.toString();
			}
			return null;
		} else {
			return null;
		}
	}

	/**
	 * 获取单例实例
	 * 
	 * @return
	 */
	public static LoadConfig getInstance() {
		if (loadServiceInfo == null) {
			creatInstance();
		}
		return loadServiceInfo;
	}

}
