package com.helpyouJFinal.test;

import java.util.Date;

import org.junit.Test;

import com.helpyouJFinal.service.MessageService;

public class MessageServiceTest extends JFinalTestBase {

	/**
	 * 测试messageService的addNewMessage方法
	 */
	@Test
	public void addNewMessageTest() {
		MessageService messageServicel = new MessageService();
		messageServicel.addNewMessage(1, 2, "我知道了，我会用支付宝支付的");
	}
	
	/**
	 * 测试messageService的getUnreadMessageNum方法
	 */
	@Test
	public void getUnreadMessageNumTest() {
		Date date = new Date();
		date.setDate(1);
		MessageService messageServicel = new MessageService();
		System.out.println(messageServicel.getUnreadMessageNum(date));
	}
}
