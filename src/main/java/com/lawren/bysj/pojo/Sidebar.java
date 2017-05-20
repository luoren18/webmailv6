package com.lawren.bysj.pojo;

public class Sidebar {
	private int unreadMail;//未读邮件
	private int allmail;//所有邮件
	private int drafts;//草稿箱
	private int inbox;//收到的邮件
	private int sendmail;//已发送邮件
	private int delmail;//已删除邮件
	private int spam;//垃圾邮件
	private int virusMail;//病毒邮件
	
	
	public Sidebar() {
		super();
	}
	public Sidebar(int unreadMail, int allmail, int drafts, int inbox,int sendmail, int delmail, int spam, int virusMail) {
		super();
		this.unreadMail = unreadMail;
		this.allmail = allmail;
		this.drafts = drafts;
		this.inbox=inbox;
		this.sendmail = sendmail;
		this.delmail = delmail;
		this.spam = spam;
		this.virusMail = virusMail;
	}
	public int getUnreadMail() {
		return unreadMail;
	}
	public void setUnreadMail(int unreadMail) {
		this.unreadMail = unreadMail;
	}
	public int getAllmail() {
		return allmail;
	}
	public void setAllmail(int allmail) {
		this.allmail = allmail;
	}
	public int getDrafts() {
		return drafts;
	}
	public void setDrafts(int drafts) {
		this.drafts = drafts;
	}
	
	public int getInbox() {
		return inbox;
	}
	public void setInbox(int inbox) {
		this.inbox = inbox;
	}
	public int getSendmail() {
		return sendmail;
	}
	public void setSendmail(int sendmail) {
		this.sendmail = sendmail;
	}
	public int getDelmail() {
		return delmail;
	}
	public void setDelmail(int delmail) {
		this.delmail = delmail;
	}
	public int getSpam() {
		return spam;
	}
	public void setSpam(int spam) {
		this.spam = spam;
	}
	public int getVirusMail() {
		return virusMail;
	}
	public void setVirusMail(int virusMail) {
		this.virusMail = virusMail;
	}
	
}
