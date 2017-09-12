package com.uhomed.entrance.core.email.handler;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

/**
 * 发送email
 * @author liming
 */
public class MailHandler {
	
	private JavaMailSender sender;
	
	private VelocityConfigurer velocityConfigurer;
	
	private String fromUserName;
	
	private static String EN_CODEING="UTF-8";

	/**
	 * 发送纯文本形式的email
	 * 
	 * @param toEmail
	 *            收件人邮箱
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 */
	public void sendTextMail(String toEmail, String title, String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromUserName);
		msg.setTo(toEmail);
		msg.setSubject(title);
		msg.setText(content);
		sender.send(msg);
	}

	/**
	 * 发送带有html的内容
	 * 
	 * @param userId
	 * @throws MessagingException
	 */
	public void sendHtmlMail(String toEmail, String title, String htmlContent)
			throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, EN_CODEING);
		helper.setFrom(fromUserName);
		helper.setTo(toEmail);
		helper.setSubject(title);
		helper.setText(htmlContent, true);
		sender.send(msg);
	}

	/**
	 * 添加附件的email发送
	 * 
	 * @param toEmail
	 *            收件人地址
	 * @param title
	 *            邮件标题
	 * @param content
	 *            文本内容
	 * @param aboutFiles
	 *            附件信息 每个子项都是一个文件相关信息的map Map<String,String>: 1.filePath
	 *            2.fileName
	 * @throws Exception
	 *             异常
	 */
	public void sendAttachmentMail(String toEmail, String title,
			String content, List<Map<String, String>> aboutFiles)
			throws Exception {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, EN_CODEING);
		helper.setFrom(fromUserName);
		helper.setTo(toEmail);
		helper.setSubject(title);
		helper.setText(content);
		Resource resource = null;
		for (Map<String, String> file : aboutFiles) {
			resource = new FileSystemResource(file.get("filePath"));
			if (resource.exists()) {// 是否存在资源
				File attachmentFile = resource.getFile();
				helper.addAttachment(file.get("fileName"), attachmentFile);
			}
		}
		sender.send(msg);
	}

	/**
	 * 使用velocity模板进行发送
	 * 
	 * @param toEmail
	 *            收件人邮箱
	 * @param title
	 *            标题
	 * @param templateName
	 *            模板名称 模板都放在类路径下的mailtemp下
	 * @param templateNameParams
	 *            模板中的参数，其中${key} 要使用map去进行替换
	 * @throws MessagingException
	 *             异常
	 */
	public void sendTemplateMail(String toEmail, String title,
			String templateName, Map<String, Object> templateNameParams)
			throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, EN_CODEING);
		helper.setFrom(fromUserName);
		helper.setTo(toEmail);
		helper.setSubject(title);
		String htmlText = getMailText(templateName, templateNameParams);
		helper.setText(htmlText, true);
		sender.send(msg);
	}

	/**
	 * 使用freemarker模板+附件进行发送
	 * 
	 * @param toEmail
	 *            收件人邮箱
	 * @param title
	 *            标题
	 * @param templateName
	 *            模板名称 模板都放在类路径下的mailtemp下
	 * @param templateNameParams
	 *            模板中的参数，其中${key} 要使用map去进行替换
	 * @param aboutFiles
	 *            附件信息 每个子项都是一个文件相关信息的map Map<String,String>: 1.filePath
	 *            2.fileName
	 * @throws Exception
	 *             异常
	 */
	public void sendTemplateMail(String toEmail, String title,
			String templateName, Map<String, Object> templateNameParams,
			List<Map<String, String>> aboutFiles) throws Exception {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, EN_CODEING);
		helper.setFrom(fromUserName);
		helper.setTo(toEmail);
		helper.setSubject(title);
		String htmlText = getMailText(templateName, templateNameParams);
		Resource resource = null;
		for (Map<String, String> file : aboutFiles) {
			resource = new FileSystemResource(file.get("filePath"));
			if (resource.exists()) {// 是否存在资源
				File attachmentFile = resource.getFile();
				helper.addAttachment(file.get("fileName"), attachmentFile);
			}
		}
		helper.setText(htmlText, true);
		sender.send(msg);
	}

	/**
	 * 获取模板信息并且替换
	 * 
	 * @param userId
	 * @return
	 */
	private String getMailText(String templateName, Map<String, Object> templateNameParams) {
		try {
			Template tpl = velocityConfigurer.getVelocityEngine().getTemplate(
					templateName);
			Context context = this.templateContextFactary(templateNameParams);
			StringWriter writer = new StringWriter();
			tpl.merge(context, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Context templateContextFactary(Map<String,Object> params){
		Context context = new VelocityContext();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			context.put(entry.getKey(), entry.getValue());
		}
		return context;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	/**
	 * @param velocityConfigurer the velocityConfigurer to set
	 */
	public void setVelocityConfigurer(VelocityConfigurer velocityConfigurer) {
		this.velocityConfigurer = velocityConfigurer;
	}

	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	

}
