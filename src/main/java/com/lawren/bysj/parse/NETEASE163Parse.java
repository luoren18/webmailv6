package com.lawren.bysj.parse;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;


@Service("netease163Parse")
public class NETEASE163Parse {

	/**
	 * 获取所有163邮件
	 * @param user
	 * @return
	 */
	public  List<EMail> getUnReadMail(User user,Date maxDate){
		Pop3Server popServer = new Pop3SslServer("pop.163.com", user.getEmail(), user.getPassword());
	    ReceiveMailSession session = popServer.createSession();
	    return Parse.prase(session,user,maxDate);
	}

	public List<EMail> getAllMail(User user) {
		Pop3Server popServer = new Pop3SslServer("pop.163.com", user.getEmail(), user.getPassword());
	    ReceiveMailSession session = popServer.createSession();
	    return Parse.prase(session,user);
	}
	
	
}
