package com.lawren.bysj.service;

import com.lawren.bysj.pojo.User;

public interface UserService {
	public int add(User user);
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public User getUserByEmail(String email);
	public int updateUser(User user);
}
