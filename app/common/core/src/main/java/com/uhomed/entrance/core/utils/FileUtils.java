/**  
 * @Title: FileUtil.java 
 * @Package cn.com.topinfo.exam.util 
 * @Company:浙江图讯科技
 * @author Administrator  
 * @date 2013-9-9 下午02:06:50 
 * @version V1.0   
 */
package com.uhomed.entrance.core.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;


/**
 * 文件工具类
 * 
 * @ClassName: FileUtils
 * @version 1.0 2013-9-9 下午02:06:52
 * @lastUpdateTime 2013-9-9 下午02:06:52
 */
public class FileUtils {

	/** 文件路径 */
	private String filePath;

	/**
	 * @param fileName
	 *            路径
	 */
	public FileUtils(String fileName) {
		this.filePath = fileName;
	}

	/**
	 * 根据内容生成file文件
	 * 
	 * @param content
	 * @param ext
	 *            文件后缀
	 * @return
	 * @author lim
	 */
	public String writeFile(String content, String ext, String fileName)
			throws IOException {
		String path = filePath + fileName + "." + ext;
		File file = new File(path);
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				file), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(content);
		writer.close();
		return path;
	}
	
	/**
	 * 根据内容生成file文件
	 * 
	 * @param content
	 * @param ext
	 *            文件后缀
	 * @return
	 * @author lim
	 */
	public static void writeFile(String content, String filePath)
			throws IOException {
		String saveDir = filePath.substring(0,filePath.lastIndexOf(File.separator));
		File file = new File(saveDir);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(filePath);
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(content);
		writer.close();
	}

	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @return
	 */
	public String writeFile(byte[] b, String fileName, String ext)
			throws IOException {
		String path = filePath + fileName + "." + ext;
		File ret = null;
		BufferedOutputStream stream = null;
		ret = new File(path);
		FileOutputStream fstream = new FileOutputStream(ret);
		stream = new BufferedOutputStream(fstream);
		stream.write(b);
		if (stream != null) {
			stream.close();
		}
		return path;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return
	 * @author lim
	 */
	public boolean deleteFile(String filePath) {
		boolean result = false;

		File file = new File(filePath);
		if (!file.exists()) {
			return result;
		} else {
			if (file.isFile()) {
				result = file.delete();
			}
		}

		return result;
	}

	/**
	 * 写文件
	 * 
	 * @param handler
	 *            文件
	 * @param fileName
	 *            名称
	 * @param ext
	 *            文件类型后缀
	 * @return path
	 * @author lim
	 */
	public String writeFile(DataHandler handler, String fileName, String ext)
			throws IOException {
		InputStream is = null;
		OutputStream os = null;
		fileName = fileName + "." + ext;
		is = handler.getInputStream();
		os = new FileOutputStream(filePath + fileName);
		byte[] data = new byte[is.available()];
		int bytesRead = 0;
		while ((bytesRead = is.read(data)) != -1) {
			os.write(data, 0, bytesRead);
		}
		os.flush();
		os.close();
		is.close();
		return fileName;
	}

	/**
	 * 解析txt 编码utf-8
	 * 
	 * @param fileName
	 *            全路径
	 * @return
	 * @author lim
	 */
	public static String readText(String fileName) {
		StringBuffer sb = new StringBuffer("");
		InputStream is;
		// 必须设置成UTF-8，否则将出现乱码
		try {
			is = new FileInputStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\r");
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void writeFile(InputStream stream, String path,
			String filename) {
		FileOutputStream fs = null;
		try {
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File file = new File(path + File.separator + filename);
			if (file.isDirectory()) {
				file.createNewFile();
			}
			fs = new FileOutputStream(path + File.separator + filename);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 将临时路径下的文件写入指定目录文件夹下
	 * 
	 * @param localFile
	 * @param targetFile
	 * @author fj
	 */
	public static void copyFile(File localFile, File targetFile) {
		try {
			org.apache.commons.io.FileUtils.copyFile(localFile, targetFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<String> getStaticString(String vmText, String regex) {
		List<String> urls = new LinkedList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(vmText);
		while (matcher.find()) {
			urls.add(matcher.group());
		}

		if (urls.size() > 0) {
			for (String url : urls) {
				System.out.println(url);
			}
		}
		return urls;
	}

	/**
	 * 删除文件，绝对路径
	 * 
	 * @param path
	 * @return
	 */
	public static boolean delFile(String path) {
		File file = new File(path);

		if (file.exists()) {
			return file.delete();
		}
		return false;
	}
	
}
