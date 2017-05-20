package com.lawren.bysj.send;

import java.util.Properties;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.tool.EmailTool;
import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailAttachmentBuilder;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;
@Service(value="send")
public class Send {

	/**
	 * 封装一封邮件
	 * @param msg
	 * @return
	 */
	protected  Email createEmail(EMail msg){
		String receiver=msg.getReceiver();
		String tos[]=receiver.split(",");
		Email email=Email.create()
				.subject(msg.getSubject(), "utf-8")
				.from(msg.getAddresser())
				.to(tos)
				.setCurrentSentDate()
				.addHtml(msg.getBodytext(), "utf-8")
				.priority(Email.PRIORITY_NORMAL);
		
		//附件功能
		if (msg.getAttachment()!=null&&!msg.getAttachment().equals("")) {
			EmailAttachmentBuilder attach=EmailAttachment.attachment().file(msg.getAttachment());
			email.attach(attach);
		}
		return email;
	}
	
	/**
	 * 使用SMTP加密协议发送邮件
	 * @param email
	 * @param user
	 * @return
	 */
	protected  String sendSslmail(Email email,User user){
		Properties hostProp=EmailTool.getHostProp(user.getEmail());
		SmtpServer<?> smtpServer = SmtpSslServer.create(hostProp.getProperty("mail.smtp.host"))
				 .authenticateWith(user.getEmail(), user.getPassword());
		SendMailSession session = smtpServer.createSession();
		session.open();
		session.sendMail(email);
		session.close();
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 使用SMTP协议发送邮件
	 * @param email
	 * @param user
	 * @return
	 */
	protected  String sendmail(Email email,User user){
		Properties hostProp=EmailTool.getHostProp(user.getEmail());
		@SuppressWarnings("rawtypes")
		SmtpServer smtpServer = SmtpServer
	                .create(hostProp.getProperty("mail.smtp.host"))
	                .authenticateWith(user.getEmail(), user.getPassword());
		SendMailSession session = smtpServer.createSession();
		session.open();
		session.sendMail(email);
		session.close();
		return UUID.randomUUID().toString();
	}
	
	/**
	 * gmail 发送邮件
	 * @param msg
	 * @param user
	 * @return 邮件ID
	 */
	public String gmailSendMail(EMail msg,User user){
		return sendSslmail(createEmail(msg), user);
	}
	/**
	 * qq 发送邮件
	 * @param msg
	 * @param user
	 * @return 邮件ID
	 */
	public String qqSendMail(EMail msg,User user){
		return sendmail(createEmail(msg), user);
	}
	
	
	/**
	 * qq 通过Ssl发送邮件
	 * @param msg
	 * @param user
	 * @return 邮件ID
	 */
	public String qqSslSendMail(EMail msg,User user){
		return sendSslmail(createEmail(msg), user);
	}
	
	/**
	 * 163  发送邮件
	 * 此种方式效率较高
	 * @param msg
	 * @param user
	 * @return 邮件ID
	 */
	public String netease163SendMail(EMail msg,User user){
		return sendmail(createEmail(msg), user);
	}
	
	/**
	 * 163 通过 Ssl发送邮件 
	 * @param msg
	 * @param user
	 * @return 邮件ID
	 */
	public String netease163SslSendMail(EMail msg,User user){
		return sendSslmail(createEmail(msg), user);
	}
	
	
	/**
	 * sina 发送邮件 使用不加密的方式，此种方式效率更高
	 * @param msg
	 * @param user
	 * @return
	 */
	public String sinaSendMail(EMail msg,User user){
		return sendmail(createEmail(msg), user);
	}
	
}
