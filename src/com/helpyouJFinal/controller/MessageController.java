package com.helpyouJFinal.controller;

import java.util.ArrayList;
import java.util.List;

import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Message;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.MessageService;
import com.helpyouJFinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class MessageController extends Controller{
	MessageService messageService = enhance(MessageService.class);
	UserService userService = enhance(UserService.class);
	
	/**
	 * 用户消息主页
	 */
	@Before({SetOriginUrlInterceptor.class,AuthInterceptor.class})
	public void index() {
		User user = getSessionAttr("user");
		Integer receiverId = user.getInt("userId");
		List<Message> messages = messageService.getMessageListByReceiverId(receiverId);
		List<String> nicknames = new ArrayList<String>();
		for(int i = 0,len = messages.size();i < len;i++){
			Integer senderId = messages.get(i).getInt("senderId");
			String nickname = userService.getUserNickname(senderId);
			nicknames.add(nickname);
		}
		setAttr("messages", messages);
		setAttr("nicknames", nicknames);
		render("message.jsp");
		
	}
	
	/**
	 * 发送留言
	 */
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
