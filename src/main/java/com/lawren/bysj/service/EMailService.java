package com.lawren.bysj.service;

import java.util.List;

import com.lawren.bysj.pojo.EMail;

public interface EMailService {
	public int delEmailById(Integer id);

    public int add(EMail email);
    
    public EMail getEMailById(Integer id);
    
    public List<EMail> getEmailsByUid(Integer uid);
    
    public EMail getEmailByMsgId(String msgId);
    
    public int updatEmail(EMail email);
}
