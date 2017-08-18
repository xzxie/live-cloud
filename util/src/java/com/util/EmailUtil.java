package com.util;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmailUtil {

	protected static Log logger = LogFactory.getLog(EmailUtil.class);
	
	public static final String host = "smtp.qq.com";					//smtp服务器
	public static final String port = "25";								//smtp端口号
	public static final boolean validate = true;						//是否需要身份验证
	public static final String username = "";							//用户名
	public static final String password = "";							//密码
	public static final String sys_mail = "service@xiexiaozhang.com";	//系统邮件地址
	
	public static Properties property = new Properties();
	static {
		property.put("mail.smtp.host", EmailUtil.host);
		property.put("mail.smtp.port", EmailUtil.port);
		property.put("mail.smtp.auth", EmailUtil.validate) ;
	}
	
	
	// 邮件模板
	public static String title = "";
	public static String content = "";
	
	// 发送
	public static boolean post(String from, String to, String title, String content, String[] params) {
		boolean success = false;
		if (StringUtils.isBlank(from)) {
			success = false;
		}
		if (StringUtils.isBlank(to)) {
			success = false;
		}
		if (!success) {
			return success;
		}
		content = EmailUtil.replace(content, params);
		
		// 验证
		MailAuthenticator authenticator = null;
		if (EmailUtil.validate) {
			authenticator = new MailAuthenticator(EmailUtil.username, EmailUtil.password);
		}
		
		// 发送邮件的会话
		Session session = Session.getInstance(EmailUtil.property, authenticator);
		try {
			// 编写消息
			Message message = new MimeMessage(session);
			InternetAddress fromAddress = new InternetAddress(from);
			InternetAddress toAddress = new InternetAddress(to);
			message.setFrom(fromAddress);
			message.addRecipient(RecipientType.TO, toAddress);
			message.setSentDate(Calendar.getInstance().getTime());
			message.setSubject(title);
			message.setContent(content, "text/html;charset=gb2312");
			
			// 发送
			Transport transport = session.getTransport("smtp");
			transport.connect(EmailUtil.host, EmailUtil.username, EmailUtil.password);
			transport.sendMessage(message, message.getRecipients(RecipientType.TO));
			success = true;
		} catch (Exception e) {
			success = false;
			logger.error("邮件发送失败, from: " + from + ", to: " + to);
		}
		
		return success;
	}
	
	private static String replace(String content, String[] placeholder) {
		if (StringUtils.isBlank(content)) {
			return "";
		}
		content = String.format(content, placeholder);
		return content;
	}
	
	
	
	// 测试
	public static void main(String[] args) {
		String content = "hello, my name is %s, my age is %s!";
		String[] placeholder = {"xiexiaozhang", "20"};
		content = EmailUtil.replace(content, placeholder);
		System.out.println(content);
	}
}


// 邮箱验证器
class MailAuthenticator extends Authenticator {
	private String username;
	private String password;
	
	public MailAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
	}
}
