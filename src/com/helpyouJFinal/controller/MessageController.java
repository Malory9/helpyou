package com.helpyouJFinal.controller;

import java.util.ArrayList;
import java.util.List;

import com.helpyouJFinal.interceptor.AJAXAuthInterceptor;
import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Message;
import com.helpyouJFinal.model.Notice;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.MessageService;
import com.helpyouJFinal.service.NoticeService;
import com.helpyouJFinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class MessageController extends Controller{
	MessageService messageService = enhance(MessageService.class);
	UserService userService = enhance(UserService.class);
	NoticeService noticeService = enhance(NoticeService.class);
	
	/**
	 * 用户消息主页
	 */
	@Before({SetOriginUrlInterceptor.class,AuthInterceptor.class})
	public void index() {
		User user = getSessionAttr("user");
		Integer receiverId = user.getInt("userId");
		//获得用户收到的信息
		List<Message> messages = messageService.getMessageListByReceiverId(receiverId);
		//获得信息的发送者昵称
		List<String> nicknames = new ArrayList<String>();
		for(int i = 0,len = messages.size();i < len;i++){
			Integer senderId = messages.get(i).getInt("senderId");
			String nickname = userService.getUserNickname(senderId);
			nicknames.add(nickname);
		}
		List<Notice> notices = noticeService.getAllNotice();
		setAttr("messages", messages);
		setAttr("nicknames", nicknames);
		setAttr("notices", notices);
		//将未读信息量设置为0
		setSessionAttr("unreadMessageNum", 0);
		render("message.jsp");
	}
	
	/**
	 * 发送留言
	 */
	@Before(AuthInterceptor.class)
	public void send() {
		String receiverNickname = this.getPara("receiver");
		Integer ReceiverId = userService.getUserId(receiverNickname);
		String content = this.getPara("messageContent");
		User user = getSessionAttr("user");
		Integer senderId = user.getInt("userId");
		messageService.addNewMessage(senderId, ReceiverId, content);
		redirect("/message");
	}
}
