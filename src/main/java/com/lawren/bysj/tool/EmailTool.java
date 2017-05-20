package com.lawren.bysj.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.lawren.bysj.pojo.HostType;

public class EmailTool {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Properties getHostProp(String path){
		 String host=path.split("@")[1].split("\\.")[0];
		 System.out.println(host);
		 Properties properties=null;
		 
		switch (host) {
		case "sina":
			properties=HostType.SINA.getProperties();
			break;
		case "qq":
			properties=HostType.TENCENT.getProperties();
			break;
		case "163":
			properties=HostType.NETEASE163.getProperties();
			break;
		case "123":
			properties=HostType.NETEASE123.getProperties();
			break;
		case "gmail":
			properties=HostType.GMAIL.getProperties();
			break;
		case "outlook":
			break;
		default:
			break;
		}
		return properties;
	}
	public static String  formatDate(Date date){
		if(date==null){
			date=new Date();
		}
		return SIMPLE_DATE_FORMAT.format(date);
	}
	
	public static Date strToDate(String date) {
		Date d=null;
		try {
			d= SIMPLE_DATE_FORMAT.parse(date);
		} catch (ParseException|NullPointerException e) {
			d=new Date();
		}
		return d;
	}
}
