package com.lawren.bysj.tool;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawren.bysj.parse.GMAILEParse;
import com.lawren.bysj.parse.NETEASE163Parse;
import com.lawren.bysj.parse.QQMAILParse;
import com.lawren.bysj.parse.SINAMAILParse;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;

@Component("parseUnreadMailTool")
public class ParseUnreadMailTool {
	@Autowired
	private GMAILEParse gmaileParse;
	@Autowired
	private NETEASE163Parse netease163Parse;
	@Autowired
	private QQMAILParse qqmailParse;
	@Autowired
	private SINAMAILParse sinamailParse;
	
	public List<EMail> parseUnread(User user,Date maxDate){
		String host = user.getEmail().split("@")[1].split("\\.")[0];
		List<EMail> list=null;
		switch (host) {
		case "163":
			list=netease163Parse.getUnReadMail(user, maxDate);
			break;
		case "qq":
			list=qqmailParse.getUnReadMail(user,maxDate);
			break;
		case "sina":
			list=sinamailParse.getUnreadMail(user, maxDate);
			break;
		case "gmail":
			list=gmaileParse.getUnReadMail(user);
			break;
		case "123":
			list=null;
			break;
		default:
			break;
		}
		return list;
		
	}

}
