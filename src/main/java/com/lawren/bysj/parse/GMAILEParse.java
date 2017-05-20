package com.lawren.bysj.parse;

import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.tool.EmailTool;
import jodd.mail.ImapServer;
import jodd.mail.ImapSslServer;

import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;

/**
 * google 邮箱邮件的解析
 * 
 * @author Administrator
 *
 */
@Service("gmailParse")
public class GMAILEParse {
	/**
	 *	获取谷歌邮箱的所有邮件
	 * @param user
	 * @return
	 */
	public List<EMail> getAllMail(User user) {
		ImapServer imapServer = new ImapSslServer("imap.gmail.com", user.getEmail(), user.getPassword());
		ReceiveMailSession session = imapServer.createSession();

		return Parse.prase(session, user);
	}

	/**
	 * 获取谷歌邮箱的未读邮件
	 * @param user
	 * @return
	 */
	public List<EMail> getUnReadMail(User user) {
		Properties hostProp = EmailTool.getHostProp(user.getEmail());
		Pop3Server popServer = new Pop3SslServer(hostProp.getProperty("mail.pop3.host"), user.getEmail(),
				user.getPassword());
		ReceiveMailSession session = popServer.createSession();
		List<EMail> list=Parse.prase(session, user);
		if (list!=null) {
			for(int i=0;i<list.size();i++){
				list.get(i).setIsnew(true);
			}
		}
		return  list;
	}
}
