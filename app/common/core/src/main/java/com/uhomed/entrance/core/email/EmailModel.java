/**
 * created liming.lm 2014年11月5日
 */
package com.uhomed.entrance.core.email;

import java.io.Serializable;
import java.util.Map;

/**
 * 邮件发送对象
 * @author liming.lm
 * @version $Id:EmailModel.java,v 0.1 2014年11月5日 下午4:14:36 liming.lm Exp $
 */
public class EmailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592802869215976452L;
	/** 邮件标题 */
	private String emailTitle;
	/** 目标邮箱 */
	private String targetEmail;
	/** 模板路径 */
	private String tempaltePath;
	/** 模板参数 */
	private Map<String,Object> templateParams;
	
	
	/**
	 * @param 
	 * @return 
	 * @throws
	 */
	public EmailModel() {
		super();
	}
	
	public EmailModel(String emailTitle,String targetEmail,String templatePath,Map<String,Object> templateParams) {
		super();
		this.emailTitle = emailTitle;
		this.targetEmail = targetEmail;
		this.tempaltePath = templatePath;
		this.templateParams = templateParams;
	}
	/**
	 * @return the emailTitle
	 */
	public String getEmailTitle() {
		return emailTitle;
	}
	/**
	 * @param emailTitle the emailTitle to set
	 */
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	/**
	 * @return the targetEmail
	 */
	public String getTargetEmail() {
		return targetEmail;
	}
	/**
	 * @param targetEmail the targetEmail to set
	 */
	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}
	/**
	 * @return the tempaltePath
	 */
	public String getTempaltePath() {
		return tempaltePath;
	}
	/**
	 * @param tempaltePath the tempaltePath to set
	 */
	public void setTempaltePath(String tempaltePath) {
		this.tempaltePath = tempaltePath;
	}
	/**
	 * @return the templateParams
	 */
	public Map<String, Object> getTemplateParams() {
		return templateParams;
	}
	/**
	 * @param templateParams the templateParams to set
	 */
	public void setTemplateParams(Map<String, Object> templateParams) {
		this.templateParams = templateParams;
	}
	
}
