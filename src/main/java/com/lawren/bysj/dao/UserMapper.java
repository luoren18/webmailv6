package com.lawren.bysj.dao;

import com.lawren.bysj.pojo.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User user);
    User selectByEmail(String email);
    User selectByUsername(String username);
    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
}