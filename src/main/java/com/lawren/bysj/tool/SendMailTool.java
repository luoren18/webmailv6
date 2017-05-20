package com.lawren.bysj.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.send.Send;

@Component("sendMailTool")
public class SendMailTool {

	@Autowired
	private Send send;
	public String send(EMail msg, User user) {
		String host = user.getEmail().split("@")[1].split("\\.")[0];
		String MSG_ID=null;
		switch (host) {
		case "163":
			MSG_ID=send.netease163SendMail(msg, user);
			break;
		case "qq":
			MSG_ID=send.qqSslSendMail(msg, user);
			break;
		case "sina":
			MSG_ID=send.sinaSendMail(msg, user);
			break;
		case "gmail":
			MSG_ID=send.gmailSendMail(msg, user);
			break;
		case "123":
			MSG_ID=send.gmailSendMail(msg, user);
			break;
		default:
			break;
		}
		return MSG_ID;
	}
}
