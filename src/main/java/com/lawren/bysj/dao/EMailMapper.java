package com.lawren.bysj.dao;

import java.util.List;

import com.lawren.bysj.pojo.EMail;


public interface EMailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EMail email);

    int insertSelective(EMail email);
    
    List<EMail> selectByUid(Integer uid);
    
    List<EMail> selectByStateid(Integer stateid);

    EMail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EMail email);

    int updateByPrimaryKey(EMail record);
    
    EMail selectByPrimaryMsgId(String msgId);
}