package com.lawren.bysj.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.Sidebar;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.service.EMailService;
import com.lawren.bysj.service.UserService;
import com.lawren.bysj.tool.ParseMailTool;
import com.lawren.bysj.tool.SidebarTool;
import com.lawren.bysj.tool.UserCheck;


@Controller

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EMailService emailService;
	@Autowired
	private ParseMailTool parseMailTool;
	
	public static Logger logger = Logger.getLogger(UserController.class);
	@RequestMapping("login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("loginCheck")
	public String loginCheck(String email,String password,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String target="";
		User user =userService.getUserByEmail(email);
		if (user!=null) {
			//老用户
			if (user.getPassword().equals(password)) {
				session.setAttribute("user", user);
				List<EMail> emails=emailService.getEmailsByUid(user.getId());
				Collections.sort(emails);
				Collections.reverse(emails);
				session.setAttribute("emails", emails);
				Sidebar sidebar=SidebarTool.statistics(emails);
				session.setAttribute("sidebar", sidebar);
				target="email_main";
			}else{
				target="404";
			}
		}else{
			//新用户
			if(UserCheck.userCheck(email, password)){
				user=new User(email,password);
				List<EMail> emails=parseMailTool.parse(user);
				userService.add(user);
				user=userService.getUserByEmail(email);
				session.setAttribute("user", user);
				for(int i=0;i<emails.size();i++){
					emails.get(i).setUid(user.getId());
					emailService.add(emails.get(i));
				}
				emails=emailService.getEmailsByUid(user.getId());
				Collections.sort(emails);
				Collections.reverse(emails);
				session.setAttribute("emails", emails);
				
				target="email_main";
			}else{
				target="404";
			}
		}
		return "redirect:/"+target;
	}
	
	@RequestMapping("email_main")
	public String email_main(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		//System.out.println(session.getAttribute("user"));
		logger.info(session.getAttribute("user"));
		if (request.getSession().getAttribute("user")==null) {
			return "redirect:/login";
		}
		@SuppressWarnings("unchecked")
		Sidebar sidebar=SidebarTool.statistics((List<EMail>) session.getAttribute("emails"));
		session.setAttribute("sidebar", sidebar);
		return "email_main";
	}
	
	@ResponseBody
	@RequestMapping("updateUserInfo")
	public Map<String, Object> updateUserInfo(User user,HttpSession session){
		logger.info("修改后的用户信息"+user);
		Map<String, Object> map = new HashMap<>();
		int size=userService.updateUser(user);
		if (size==1) {
			map.put("msg", "用户信息修改成功");
			map.put("flag", true);
		}else{
			map.put("msg", "用户信息修改失败");
			map.put("flag", false);
		}
		session.setAttribute("mesg", map);
		session.setAttribute("user", user);
		return map;
	}
	
}
