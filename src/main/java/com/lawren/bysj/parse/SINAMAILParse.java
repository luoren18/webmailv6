package com.lawren.bysj.parse;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import jodd.mail.ImapServer;
import jodd.mail.ImapSslServer;
import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;


/**
 * 新浪邮箱邮件解析
 * 
 * @author Administrator
 *
 */
@Service("sinamailParse")
public class SINAMAILParse {

	/**
	 * 慢速获取sina邮件
	 * 
	 * @param user
	 * @return
	 */
	public List<EMail> slowGetAllMail(User user) {
		ImapServer imapServer = new ImapSslServer("imap.sina.cn", user.getEmail(),
				user.getPassword());
		ReceiveMailSession session = imapServer.createSession();

		return Parse.prase(session, user);
	}

	/**
	 * 快速获取sina邮件
	 * 
	 * @param user
	 * @return
	 */
	public List<EMail> quickGetAllMail(User user) {
		Pop3Server popServer = new Pop3SslServer("pop.sina.cn", user.getEmail(),
				user.getPassword());
		ReceiveMailSession session = popServer.createSession();
		return Parse.prase(session, user);
	}

	/**
	 * sina获取未读邮件
	 * @param user
	 * @param maxdate
	 * @return
	 */
	public List<EMail> getUnreadMail(User user ,Date maxdate){
		Pop3Server popServer = new Pop3SslServer("pop.sina.cn", user.getEmail(),
				user.getPassword());
		ReceiveMailSession session = popServer.createSession();
		return Parse.prase(session, user, maxdate);
	}
}
