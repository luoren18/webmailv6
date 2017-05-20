package com.lawren.bysj.tool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawren.bysj.parse.GMAILEParse;
import com.lawren.bysj.parse.NETEASE163Parse;
import com.lawren.bysj.parse.QQMAILParse;
import com.lawren.bysj.parse.SINAMAILParse;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.User;

@Component("parseMailTool")
public class ParseMailTool {

	@Autowired
	private GMAILEParse gmaileParse;
	@Autowired
	private NETEASE163Parse netease163Parse;
	@Autowired
	private QQMAILParse qqmailParse;
	@Autowired
	private SINAMAILParse sinamailParse;
	
	public List<EMail> parse(User user){
		String host = user.getEmail().split("@")[1].split("\\.")[0];
		System.out.println("parse\t"+host);
		List<EMail> list=null;
		switch (host) {
		case "163":
			list=netease163Parse.getAllMail(user);
			break;
		case "qq":
			list=qqmailParse.getAllMail(user);
			break;
		case "sina":
			list=sinamailParse.quickGetAllMail(user);
			break;
		case "gmail":
			list=gmaileParse.getAllMail(user);
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
