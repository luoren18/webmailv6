package com.lawren.test;

import java.util.ArrayList;
import java.util.List;

import com.lawren.bysj.pojo.User;

public class Test01 {

	public static void main(String[] args) {
		User user=new User("aa", "123");
		User user2 =new User("bb","cc");
		List<User> users=new ArrayList<>();
		users.add(user);
		users.add(user2);
		for(int i=0;i<users.size();i++){
			users.get(i).setPhone("13226626");
		}
		for(User user3:users){
			System.out.println(user3.getPhone());
		}
	}
}
