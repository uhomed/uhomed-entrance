/**
 * created liming.lm 2014年11月5日
 */
package com.uhomed.entrance.core.email.thread;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.uhomed.entrance.core.email.EmailModel;
import com.uhomed.entrance.core.email.handler.MailHandler;
import com.uhomed.entrance.core.utils.LoggerFactary;

/**
 * 多线程发送邮件
 * @author liming.lm
 * @version $Id:SendEmailThread.java,v 0.1 2014年11月5日 下午4:20:46 liming.lm Exp $
 */
public class SendEmailThread implements Runnable{

	private EmailModel emailModel;
	
	private MailHandler mailHandler;
	
	private Logger EMAIL_LOGGER = LoggerFactary.getLogger(LoggerFactary.EMAIL_LOGGER);
	
	public SendEmailThread(MailHandler mailHandler,EmailModel emailModel){
		this.emailModel = emailModel;
		this.mailHandler = mailHandler;
	}
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			mailHandler.sendTemplateMail(emailModel.getTargetEmail(),
					emailModel.getEmailTitle(), emailModel.getTempaltePath(),
					emailModel.getTemplateParams());
		} catch (MessagingException e) {
			EMAIL_LOGGER.info(e.getStackTrace());
		}
	}

}
