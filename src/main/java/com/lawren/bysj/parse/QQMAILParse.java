package com.lawren.bysj.parse;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;


import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;


@Service("qqmailParse")
public class QQMAILParse {

	/**
	 * 当前方式只支持qq邮箱的未读邮件的解析
	 * @param user
	 * @return
	 */
	public  List<EMail> getAllMail(User user){
		Pop3Server popServer = new Pop3SslServer("pop.qq.com", user.getEmail(), user.getPassword());
	    ReceiveMailSession session = popServer.createSession();
	    return Parse.prase(session,user);
	}
	
	/**
	 * 获取qq邮箱的未读邮件
	 * @param user
	 * @param maxDate
	 * @return
	 */
	public  List<EMail> getUnReadMail(User user,Date maxDate){
		Pop3Server popServer = new Pop3SslServer("pop.qq.com", user.getEmail(), user.getPassword());
	    ReceiveMailSession session = popServer.createSession();
	    return Parse.prase(session,user,maxDate);
	}
	
	
}
