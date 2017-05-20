package com.lawren.bysj.tool;

import java.util.Properties;
import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;

public class UserCheck {

	public static boolean userCheck(String username, String password) {
		boolean flag=true;
		Properties hostProp = EmailTool.getHostProp(username);
		Pop3Server popServer = new Pop3SslServer(hostProp.getProperty("mail.pop3.host"), username,
				password);
		ReceiveMailSession session = popServer.createSession();
		try {
			session.open();
		} catch (Exception e) {
			flag=false;
		}finally {
			session.close();
		}
		return flag;
	}

}
