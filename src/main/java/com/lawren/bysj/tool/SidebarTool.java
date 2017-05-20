package com.lawren.bysj.tool;

import java.util.List;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.Sidebar;

public class SidebarTool {
	public static Sidebar statistics(List<EMail> list) {
		int unreadMail = 0;// 未读邮件
		int allmail = 0;// 所有邮件
		int drafts = 0;// 草稿箱
		int sendmail = 0;// 已发送邮件
		int inbox = 0;//收到的邮件
		int delmail = 0;// 已删除邮件
		int spam = 0;// 垃圾邮件
		int virusMail = 0;// 病毒邮件
		if (list != null) {
			for (EMail mail : list) {
				int stateid = mail.getStateid();
				allmail++;
				if (mail.getIsnew()!=null&&mail.getIsnew()&&stateid==1) {
					unreadMail++;
				}
				if (stateid == 1) {
					inbox++;
					continue;
				}
				if (stateid == 2) {
					sendmail++;
					continue;
				}
				if (mail.getStateid() == 3) {
					drafts++;
					continue;
				}
				if (stateid == 4) {
					delmail++;
					continue;
				}
				if (stateid == 5 || stateid == 6) {
					spam++;
					continue;
				}
				if (stateid == 7) {
					virusMail++;
					continue;
				}
			}
		}
		return new Sidebar(unreadMail, allmail, drafts, inbox, sendmail, delmail, spam, virusMail);
	}
}
