package com.lawren.bysj.parse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.tool.CreateFileUtil;
import com.lawren.bysj.tool.EmailTool;

import jodd.datetime.JDateTime;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailMessage;
import jodd.mail.MailAddress;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;

public class Parse {
	private static Logger logger = Logger.getLogger(Parse.class);
	public static List<EMail> prase(ReceiveMailSession session, User user, Date maxDate) {
		session.open();
		
		logger.debug("开始解析服务器未读邮件！");
		logger.debug("session\t" + session);
		List<EMail> list = null;
		ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		if (emails != null) {
			list = new ArrayList<>();
			for (ReceivedEmail email : emails) {
				Date date=email.getSentDate();
				if (date!=null&&date.compareTo(maxDate) > 0) {
					EMail myMail = new EMail();
					myMail.setUid(user.getId());
					myMail.setStateid(1);
					String subject = email.getSubject().replaceAll("\\s*", "");
					if (subject.length() > 225) {
						myMail.setSubject(subject.substring(0, 224));
					} else {
						myMail.setSubject(subject);
					}
					myMail.setSenddate(EmailTool.formatDate(email.getSentDate()));
					myMail.setIsnew(true);
					myMail.setReplysign(email.isAnswered());
					myMail.setAddresser(email.getFrom().getEmail());
					myMail.setReceiver(addressToStr(email.getTo()));
					myMail.setCc(addressToStr(email.getCc()));
					myMail.setBcc(addressToStr(email.getBcc()));
					List<EmailMessage> messages = email.getAllMessages();
					for (EmailMessage msg : messages) {
						String bodytext = msg.getContent();
						String plaincontent = Jsoup.clean(bodytext, Whitelist.none());
						if (bodytext.length() > 32700) {
							myMail.setBodytext(plaincontent);
						} else {
							myMail.setBodytext(bodytext);
						}
						myMail.setPlaincontent(plaincontent);
					}
					List<EmailAttachment> attachments = email.getAttachments();
					if (attachments != null) {
						StringBuffer strbuff = new StringBuffer();
						for (EmailAttachment attachment : attachments) {
							String path = "D:" + File.separator + "附件" + File.separator + user.getEmail()
									+ File.separator + email.getSubject();
							new File(path).mkdirs();
							attachment.writeToFile(new File(path, attachment.getName()));
							strbuff.append(path + ",");
						}
						myMail.setAttachment(strbuff.substring(0, strbuff.length() - 1));
					}
					list.add(myMail);
				}
			}
		}
		logger.debug("未读邮件解析完毕");
		session.close();
		return list;
	}

	public static List<EMail> prase(ReceiveMailSession session, User user)  {
		session.open();
		logger.debug("session\t" + session);
		List<EMail> list = null;
		ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		if (emails != null) {
			list = new ArrayList<>();
			int index = 0;
			for (ReceivedEmail email : emails) {
				EMail myMail = new EMail();
				myMail.setStateid(1);
				myMail.setUid(user.getId());
				logger.info("===" + (index++) + "===" + email.getSubject());
				String subject = email.getSubject().replaceAll("\\s*", "");
				if (subject.length() > 225) {
					myMail.setSubject(subject.substring(0, 224));
				} else {
					myMail.setSubject(subject);
				}
				myMail.setSenddate(EmailTool.formatDate(email.getSentDate()));
				myMail.setIsnew(!email.isSeen());
				myMail.setReplysign(email.isAnswered());
				myMail.setAddresser(email.getFrom().getEmail());
				myMail.setReceiver(addressToStr(email.getTo()));
				myMail.setCc(addressToStr(email.getCc()));
				myMail.setBcc(addressToStr(email.getBcc()));
				List<EmailMessage> messages = email.getAllMessages();
				for (EmailMessage msg : messages) {
					logger.info("===" + (index) + "===" + msg.getMimeType());
					String bodytext = msg.getContent();
					String plaincontent = Jsoup.clean(bodytext, Whitelist.none());
					if (bodytext.length() > 32700) {
						myMail.setBodytext(plaincontent);
					} else {
						myMail.setBodytext(bodytext);
					}
					myMail.setPlaincontent(plaincontent);
				}
				List<EmailAttachment> attachments = email.getAttachments();
				if (attachments != null) {
					StringBuffer strbuff = new StringBuffer();
					for (EmailAttachment attachment : attachments) {
						String path = "D:" +File.separator+"attachmentupload"+File.separator
								+user.getEmail()+File.separator
								+(new JDateTime(new Date())).getTimeInMillis()
								+attachment.getName();
						CreateFileUtil.createFile(path);
						try {
							FileOutputStream fos = new FileOutputStream(path);
							fos.write(attachment.toByteArray());
							fos.flush();
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						strbuff.append(path + ",");
					}
					myMail.setAttachment(strbuff.substring(0, strbuff.length() - 1));
				}
				list.add(myMail);
			}
		}
		session.close();
		return list;
	}

	private static String addressToStr(MailAddress[] mailAddresses) {
		StringBuffer strbuff = new StringBuffer();
		if (mailAddresses != null) {
			String temp = strbuff.toString();
			for (MailAddress addr : mailAddresses) {
				strbuff.append(addr.getEmail() + ",");
				if (strbuff.length() > 225) {
					strbuff = new StringBuffer(temp);
					break;
				} else {
					temp = strbuff.toString();
				}
			}
		}
		if (strbuff.length() > 1) {
			return strbuff.substring(0, strbuff.length() - 1);
		} else {
			return "";
		}
	}
}
