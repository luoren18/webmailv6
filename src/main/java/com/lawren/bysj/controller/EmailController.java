package com.lawren.bysj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lawren.bysj.pojo.EMail;
import com.lawren.bysj.pojo.Sidebar;
import com.lawren.bysj.pojo.User;
import com.lawren.bysj.service.EMailService;
import com.lawren.bysj.tool.BeanTool;
import com.lawren.bysj.tool.CreateFileUtil;
import com.lawren.bysj.tool.EmailTool;
import com.lawren.bysj.tool.ObjToJsonTool;
import com.lawren.bysj.tool.ParseUnreadMailTool;
import com.lawren.bysj.tool.SendMailTool;

import jodd.datetime.JDateTime;



@Controller
public class EmailController {

	@Autowired
	private EMailService emailService;
	@Autowired
	private SendMailTool sendMailTool;
	@Autowired
	private ParseUnreadMailTool parseUnreadMailTool;
	
	@Autowired
	private BeanTool beanTool;
	private Gson gson = new Gson();

	@ResponseBody
	@RequestMapping(value = "getmails", method = RequestMethod.GET)
	public String getMails(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		return gson.toJson(request.getSession().getAttribute("emails"));

	}

	/**
	 * 获取未读邮件
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUnreadMail")
	public List<EMail> getUnreadMail(HttpSession session) {
		List<EMail> unreadMail = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		for (EMail m : emails) {
			if (m.getStateid().equals(1) && m.getIsnew() == true) {
				unreadMail.add(m);
			}
		}
		Collections.sort(unreadMail);
		Collections.reverse(unreadMail);
		session.setAttribute("unreadMail", unreadMail);

		return unreadMail;
	}

	/**
	 * 根据stateid获取对应的邮箱
	 * 
	 * @param stateid
	 *            需要传入stateid作为参数
	 * @param session
	 *            不需要传入session 会自动装配
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getMailBox")
	public List<EMail> getMailbox(Integer stateid, HttpSession session) {
		String sessionName = null;
		if (stateid == null) {
			sessionName = "emails";
			return (List<EMail>) session.getAttribute("emails");
		} else if (stateid.equals(1)) {
			sessionName = "receivedMail";
		} else if (stateid.equals(2)) {
			sessionName = "sendMailbox";
		} else if (stateid.equals(3)) {
			sessionName = "draftMail";
		} else if (stateid.equals(4)) {
			sessionName = "delMailBox";
		}
		List<EMail> lists = new ArrayList<>();
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		for (EMail m : emails) {
			if (m.getStateid().equals(stateid)) {
				lists.add(m);
			}

			Collections.sort(lists);
			Collections.reverse(lists);
			session.setAttribute(sessionName, lists);
		}
		return lists;

	}

	@RequestMapping("getmailById")
	public ModelAndView getmailById(Integer id, HttpServletRequest request, HttpServletResponse response) {
		EMail email = emailService.getEMailById(id);
		ModelAndView view = new ModelAndView("showmail");
		view.addObject("email", email);
		return view;
	}

	/**
	 * 邮件的发送逻辑
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("sendMail")
	public Map<String, Object> sendMail(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("发送邮件开始");
		response.setContentType("text/json;charset=UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		boolean flag = true;
		String msg = "邮件发送成功";
		String MSG_ID = "";
		// 构建需要发送的邮件对象
		EMail mymail = new EMail();
		mymail.setAddresser(user.getEmail());
		mymail.setReceiver(request.getParameter("receiver"));
		mymail.setSubject(request.getParameter("subject"));
		mymail.setAttachment(request.getParameter("attapath"));
		String bodytext = request.getParameter("bodytext");
		// 将邮件的web内容提取成文本内容
		String plaincontent = Jsoup.clean(bodytext, Whitelist.none());
		mymail.setBodytext(bodytext);
		mymail.setPlaincontent(plaincontent);
		try {
			// 发送邮件并返回邮件的ID
			MSG_ID = sendMailTool.send(mymail, user);
		} catch (Exception e) {
			msg = "邮件发送失败";
			flag = false;
		}
		mymail.setMessageid(MSG_ID);
		mymail.setUid(user.getId());
		mymail.setStateid(2);
		mymail.setSenddate(EmailTool.formatDate(null));
		// 将该邮件存入数据库
		emailService.add(mymail);
		mymail = emailService.getEmailByMsgId(MSG_ID);
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		// 将邮件添加到session
		emails.add(0, mymail);
		session.setAttribute("emails", emails);

		// 构建邮件返回的消息
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		map.put("flag", flag);
		session.setAttribute("mesg", map);
		System.out.println(msg);
		return map;
	}

	/**
	 * 删除邮件
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("delmail")
	public String delmail(Integer id, HttpSession session) {
		System.out.println("开始删除邮件，id为" + id);
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		for (int i = 0; i < emails.size(); i++) {
			if (emails.get(i).getId().equals(id)) {
				emails.get(i).setStateid(4);
				System.out.println(emails.get(i).getStateid());
				emailService.updatEmail(emails.get(i));
				break;
			}
		}
		// 更新session的内容
		session.setAttribute("emails", emails);
		System.out.println("邮件删除成功");
		return "content";
	}

	/**
	 * 更新未读邮件的逻辑
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("syncmail")
	public String syncmail(HttpSession session) {
		// 获取收到的邮件
		List<EMail> receivedMail = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		if (emails != null) {
			for (EMail m : emails) {
				if (m.getStateid().equals(1)) {
					receivedMail.add(m);
				}
			}
		}

		// 找出收到的最近的邮件的日期
		Date maxDate = EmailTool.strToDate(receivedMail.get(0).getSenddate());
		for (int i = 1; i < receivedMail.size(); i++) {
			if (EmailTool.strToDate(receivedMail.get(i).getSenddate()).compareTo(maxDate) > 0) {
				maxDate = EmailTool.strToDate(receivedMail.get(i).getSenddate());
			}
		}
		List<EMail> unreadMails = parseUnreadMailTool.parseUnread((User) session.getAttribute("user"), maxDate);
		if (unreadMails != null) {
			for (EMail e : unreadMails) {
				emailService.add(e);
			}
		}
		User user = (User) session.getAttribute("user");
		List<EMail> email = emailService.getEmailsByUid(user.getId());
		Collections.sort(email);
		Collections.reverse(email);
		session.setAttribute("emails", email);
		return "unread_content";
	}

	/**
	 * 恢复已删除的邮件
	 */
	@RequestMapping("renewmailById")
	public String renewmailById(Integer id, HttpSession session) {
		EMail email = null;
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		for (int i = 0; i < emails.size(); i++) {
			if (emails.get(i).getId().equals(id)) {
				email = emails.get(i);
				email.setStateid(1);
				break;
			}
		}
		if (email != null) {
			// 修改该邮件在数据库的邮件状态
			emailService.updatEmail(email);
		}
		return "content";
	}

	/**
	 * 查收邮件 将邮件的未读状态改为已读
	 */
	@RequestMapping("toread")
	public String toread(Integer id, HttpSession session) {
		EMail email = null;
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		for (int i = 0; i < emails.size(); i++) {
			if (emails.get(i).getId().equals(id)) {
				email = emails.get(i);
				email.setIsnew(false);
				break;
			}
		}
		if (email != null) {
			// 修改该邮件在数据库的邮件状态
			emailService.updatEmail(email);
		}
		return "content";
	}

	/**
	 * 获取联系人
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getContacts")
	public Set<String> getContacts(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		Set<String> set=new HashSet<>();
		if (emails!=null) {
			for(EMail mymail:emails){
				String addresser=mymail.getAddresser();
				if (addresser!=null&&addresser!=""&&!mymail.getStateid().equals(2)) {
						
						set.add(addresser);
					
				}
			}
		}
		/*System.out.println(gson.toJson(set));*/
		return set;
	}
	
	@RequestMapping("sendmailByEmail")
	public ModelAndView sendmailByEmail(String email){
		ModelAndView modelAndView=new ModelAndView("write");
		EMail mail=new EMail();
		mail.setReceiver(email);
		modelAndView.addObject("email",mail);
		return modelAndView;
	}
	
	/**
	 * 存储草稿的逻辑
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("draftbox")
	public Map<String, Object> draftbox(HttpServletRequest request,HttpServletResponse response){
		boolean flag=true;
		String msg="邮件成功存入草稿箱";
		HttpSession session=request.getSession();
		EMail mail=new EMail();
		mail.setAddresser(request.getParameter("from"));
		mail.setReceiver(request.getParameter("to"));
		mail.setCc(request.getParameter("cc"));
		mail.setBcc(request.getParameter("bcc"));
		mail.setSubject(request.getParameter("subject"));
		mail.setStateid(3);
		String bodytext = request.getParameter("bodytext");
		// 将邮件的web内容提取成文本内容
		String plaincontent = Jsoup.clean(bodytext, Whitelist.none());
		mail.setBodytext(bodytext);
		mail.setPlaincontent(plaincontent);
		try {
			String MSG_ID=UUID.randomUUID().toString();
			mail.setMessageid(MSG_ID);
			emailService.add(mail);
			mail=emailService.getEmailByMsgId(MSG_ID);
		} catch (Exception e) {
			msg="存储失败";
			flag=false;
		}
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		// 将邮件添加到session
		emails.add(0, mail);
		session.setAttribute("emails", emails);
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		map.put("flag", flag);
		return map;
	}
	
	/**
	 * 邮件数量统计
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMailView")
	public String getMailView(HttpSession session){
		Sidebar sidebar =(Sidebar) session.getAttribute("sidebar");
		return gson.toJson(beanTool.getFiledsInfo(sidebar));
	}
	
	
	/**
	 * 根据月份统计收到的邮件的数量
	 */
	@ResponseBody
	@RequestMapping("countEmailByDate")
	public String countEmailByDate(HttpSession session){
		@SuppressWarnings("unchecked")
		List<EMail> emails = (List<EMail>) session.getAttribute("emails");
		Map<String, Map<String, Integer>> map=new HashMap<>();
		for(EMail mail:emails){
			Date date=EmailTool.strToDate(mail.getSenddate());
			JDateTime jdt=new JDateTime(date);
			String yearStr=String.valueOf(jdt.getYear());//邮件的发送年份
			String monthStr=String.valueOf(jdt.getMonth());//邮件的发送月份
			if (!map.containsKey(yearStr)) {
				Map<String, Integer> yearMap=beanTool.initMap();//初始化一个含有12个月的年的map
				int count=yearMap.get(monthStr).intValue();
				yearMap.put(monthStr, count+1);
				map.put(yearStr, yearMap);
			}else{
				Map<String, Integer> yearMap=map.get(yearStr);
				int count=yearMap.get(monthStr).intValue();
				yearMap.put(monthStr, count+1);
			}
		}
		JSONObject obj=ObjToJsonTool.objToChart2DData(map);
		
		//System.out.println(obj.toJSONString());
		return obj.toJSONString();
	}
	
	
	
	/**
	 * 附件上传功能
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/fileupload",method=RequestMethod.POST)
	public Map<String, Object> fileUpload(HttpServletRequest request) throws IOException{
		HttpSession session=request.getSession();
		MultipartHttpServletRequest filerequest=(MultipartHttpServletRequest) request;
		MultipartFile attachment=filerequest.getFile("attachment");
		Map<String, Object> message=new HashMap<>();
		if (attachment==null) {
			message.put("msgcode", false);
			message.put("msg", "没有文件被上传");
		}else{
			String attachmentName=attachment.getOriginalFilename();
			if (attachment.getSize()>10*1024*1024) {
				message.put("msg", "文件大于10M");
				message.put("msgcode", false);
			}else {
				User user=(User) session.getAttribute("user");
				String filepath=request.getSession().getServletContext().getRealPath("/")
						+"attachmentupload"+File.separator
						+user.getEmail()+File.separator
						+(new JDateTime(new Date())).getTimeInMillis()
						+"_"+attachmentName;
				CreateFileUtil.createFile(filepath);
				System.out.println(filepath);
				FileOutputStream fos=new FileOutputStream(filepath);
				fos.write(attachment.getBytes());
				fos.flush();
				fos.close();
				message.put("msg", "附件上传成功");
				message.put("filepath", filepath);
				message.put("msgcode", true);
				
			}
		}
		
		
		return message;
	}
}
