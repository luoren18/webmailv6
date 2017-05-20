package com.lawren.bysj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawren.bysj.dao.EMailMapper;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.service.EMailService;

@Service("eMailServiceImpl")
public class EMailServiceImpl implements EMailService {

	@Autowired
	private EMailMapper emailMapper;
	@Override
	public int delEmailById(Integer id) {
		return emailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int add(EMail email) {
		return emailMapper.insertSelective(email);
	}

	@Override
	public EMail getEMailById(Integer id) {
		return emailMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<EMail> getEmailsByUid(Integer uid) {
		return emailMapper.selectByUid(uid);
	}

	@Override
	public EMail getEmailByMsgId(String msgId) {
		return emailMapper.selectByPrimaryMsgId(msgId);
		
	}

	@Override
	public int updatEmail(EMail email) {
		return emailMapper.updateByPrimaryKeySelective(email);
	}

}
