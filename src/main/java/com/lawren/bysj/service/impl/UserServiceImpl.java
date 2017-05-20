package com.lawren.bysj.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawren.bysj.dao.UserMapper;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public User getUserById(Integer id) {
		
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public User getUserByUsername(String username) {
		
		return userMapper.selectByUsername(username);
	}
	@Override
	public User getUserByEmail(String email) {
		
		return userMapper.selectByEmail(email);
	}
	@Override
	public int add(User user) {
		return userMapper.insertSelective(user);
	}
	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

}
