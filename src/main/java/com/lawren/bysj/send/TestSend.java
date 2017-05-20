package com.lawren.bysj.send;

import java.util.Properties;

import org.junit.Test;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.tool.EmailTool;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;

public class TestSend {

	
	protected static Email createEmail(EMail msg){
		String receiver=msg.getReceiver();
		String tos[]=receiver.split(",");
		Email email=Email.create()
				.subject(msg.getSubject(), "utf-8")
				.from(msg.getAddresser())
				.to(tos)
				.setCurrentSentDate()
				/*.addText(msg.getBodytext(), "utf-8")*/
				.addHtml(msg.getPlaincontent(), "utf-8")
				.priority(Email.PRIORITY_NORMAL);
		return email;
	}
	
	
	protected static String sendSslmail(Email email,User user){
		String MSG_ID="";
		Properties hostProp=EmailTool.getHostProp(user.getEmail());
		SmtpServer<?> smtpServer = SmtpSslServer.create(hostProp.getProperty("mail.smtp.host"))
				 .authenticateWith(user.getEmail(), user.getPassword());
		SendMailSession session = smtpServer.createSession();
		session.open();
		MSG_ID=session.sendMail(email);
		session.close();
		return MSG_ID;
	}
	
	protected static String sendmail(Email email,User user){
		String MSG_ID="";
		Properties hostProp=EmailTool.getHostProp(user.getEmail());
		@SuppressWarnings("rawtypes")
		SmtpServer smtpServer = SmtpServer
	                .create(hostProp.getProperty("mail.smtp.host"))
	                .authenticateWith(user.getEmail(), user.getPassword());
		SendMailSession session = smtpServer.createSession();
		session.open();
		MSG_ID=session.sendMail(email);
		session.close();
		return MSG_ID;
	}
	
	/**
	 * 通过gmail发送邮件
	 * 只能通过ssl 发送
	 */
	@Test
	public void test01(){
		long start=System.currentTimeMillis();
		User user=new User();
		user.setEmail("lawren653815@gmail.com");
		user.setPassword("1435590129");
		EMail eMail2=new EMail();
		eMail2.setSubject("sgugdwiugdug");
		eMail2.setAddresser("lawren653815@gmail.com");
		eMail2.setReceiver("luoren18@sina.cn");
		eMail2.setPlaincontent("<h1>HELLO</h1>");
		Email email=createEmail(eMail2);
		System.out.println(sendSslmail(email, user));
		//System.out.println(sendmail(email, user));//发送失败
		long end=System.currentTimeMillis();
		System.out.println(end-start); //ssl:5574
	}
	
	/**
	 * 通过新浪 发送邮件
	 * 新浪暂时无法发送邮件
	 */
	@Test
	public void test02(){
		long start=System.currentTimeMillis();
		User user=new User();
		user.setEmail("luoren18@sina.cn");
		user.setPassword("abcd1234e");
		EMail eMail2=new EMail();
		eMail2.setSubject("sgugdwiugdug");
		eMail2.setAddresser("luoren18@sina.cn");
		eMail2.setReceiver("lawren653815@gmail.com");
		eMail2.setPlaincontent("<h1>HELLO</h1>");
		Email email=createEmail(eMail2);
		//System.out.println(sendSslmail(email, user));//  3177
		System.out.println(sendmail(email, user));//1018
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	/**
	 * 通过网易163 发送邮件
	 * 两种方式均可以发送邮件，不加密效率更高
	 */
	@Test
	public void test03(){
		long start=System.currentTimeMillis();
		User user=new User();
		user.setEmail("lawren653815@163.com");
		user.setPassword("abcd1234E");
		EMail eMail2=new EMail();
		eMail2.setSubject("sgugdwiugdug");
		eMail2.setAddresser("lawren653815@163.com");
		eMail2.setReceiver("1435590129@qq.com");
		eMail2.setPlaincontent("<h1>HELLO</h1>");
		Email email=createEmail(eMail2);
		//System.out.println(sendSslmail(email, user));
		System.out.println(sendmail(email, user));//发送失败
		long end=System.currentTimeMillis();
		System.out.println(end-start); //ssl:1856  smtp:832
	}
	
	/**
	 * 通过腾讯qq 发送邮件
	 * 两种方式均可以发送邮件
	 * 效率相差不大
	 */
	@Test
	public void test04(){
		long start=System.currentTimeMillis();
		User user=new User();
		user.setEmail("1435590129@qq.com");
		user.setPassword("ayqmikfwevdlgaca");
		EMail eMail2=new EMail();
		eMail2.setSubject("sgugdwiugdug");
		eMail2.setAddresser("1435590129@qq.com");
		eMail2.setReceiver("luoren18@sina.cn");
		eMail2.setPlaincontent("<h1>HELLO</h1>");
		Email email=createEmail(eMail2);
		System.out.println(sendSslmail(email, user));
		//System.out.println(sendmail(email, user));//发送失败
		long end=System.currentTimeMillis();
		System.out.println(end-start); //ssl:1782  smtp:1908
	}
}
