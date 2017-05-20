package com.lawren.bysj.pojo;

import com.lawren.bysj.tool.EmailTool;

public class EMail implements Comparable<EMail>{
    private Integer id;

    private String subject;

    private String senddate;

    private Boolean replysign;

    private Boolean isnew;

    private String addresser;

    private String receiver;

    private String cc;

    private String bcc;

    private String messageid;

    private String attachment;

    private Integer uid;
    
    private String bodytext;//html内容

    private String plaincontent;//文字内容
    
    private Integer stateid;
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? "" : subject.trim();
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate == null ? "" : senddate.trim();
    }

    public Boolean getReplysign() {
        return replysign;
    }

    public void setReplysign(Boolean replysign) {
        this.replysign = replysign;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser == null ? "" : addresser.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? "" : receiver.trim();
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? "" : cc.trim();
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc == null ? "" : bcc.trim();
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? "" : messageid.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? "" : attachment.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext == null ? "" : bodytext.trim();
    }

    public String getPlaincontent() {
        return plaincontent;
    }

    public void setPlaincontent(String plaincontent) {
        this.plaincontent = plaincontent == null ? "" : plaincontent.trim();
    }
    
    

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	@Override
	public String toString() {
		return "EMail [id=" + id + ", subject=" + subject + ", senddate=" + senddate + "]";
	}

	@Override
	public int compareTo(EMail o) {
		return EmailTool.strToDate(this.senddate).compareTo(EmailTool.strToDate(o.getSenddate()));
		
	}
    
    
}