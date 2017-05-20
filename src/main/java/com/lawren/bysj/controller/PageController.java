package com.lawren.bysj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("logout")
	public String logout(HttpServletResponse response, HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/login";
	}
	@RequestMapping("sendmailbox")
	public String sendmailbox(){
		return "sendmailbox";
	}
	
	@RequestMapping("success")
	public String success(){
		return "success";
	}
	
	@RequestMapping("delmailbox")
	public String delmailbox(){
		return "delmailbox";
	}
	@RequestMapping("404")
	public String error(){
		return "404";
	}
	@RequestMapping("content")
	public String content(){
		return "content";
	}
	@RequestMapping("unread_content")
	public String unread_content(){
		return "unread_content";
	}
	@RequestMapping("email_music")
	public String email_music(){
		return "email_music";
	}
	@RequestMapping("write")
	public String write(){
		return "write";
	}
	
	@RequestMapping("draftmailbox")
	public String draftmailbox(){
		return "draftmailbox";
	}
	
	
	@RequestMapping("translate")
	public String translate(){
		return "translate";
	}
	
	@RequestMapping("contacts")
	public String contacts(){
		return "contacts";
	}
	
	@RequestMapping("emailview")
	public String emailview(){
		return "email_view";
	}
	
	@RequestMapping("userinfo")
	public String userinfo(){
		return "userinfo";
	}
	@RequestMapping("help")
	public String help(){
		return "help";
	}
}
