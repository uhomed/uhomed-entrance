/**
 * created liming.lm 2014年11月5日
 */
package com.uhomed.entrance.core.email;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.uhomed.entrance.core.email.handler.MailHandler;
import com.uhomed.entrance.core.email.thread.SendEmailThread;

/**
 * 异步发送邮件
 * @author liming.lm
 * @version $Id:SendEmailAsync.java,v 0.1 2014年11月5日 下午4:31:00 liming.lm Exp $
 */
public class SendEmailAsync {

	private MailHandler mailHandler;
	/** 线程池 */
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	/**
	 * 异步发送
	 * @param emailModel
	 */
	public void sendEmail(EmailModel emailModel){
		SendEmailThread task = new SendEmailThread(mailHandler, emailModel);
		this.threadPoolTaskExecutor.execute(task);
	}

	/**
	 * @param mailHandler the mailHandler to set
	 */
	public void setMailHandler(MailHandler mailHandler) {
		this.mailHandler = mailHandler;
	}

	/**
	 * @param threadPoolTaskExecutor the threadPoolTaskExecutor to set
	 */
	public void setThreadPoolTaskExecutor(
			ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}
	
}
