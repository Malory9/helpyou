package com.helpyouJFinal.test;

import org.junit.Test;

import com.helpyouJFinal.service.MessageService;

public class MessageServiceTest extends JFinalTestBase {
	
	/**
	 * 测试messageService的addNewMessage方法
	 */
	@Test
	public void addNewMessageTest() {
		MessageService messageServicel =  new MessageService();
		messageServicel.addNewMessage(1, 2, "我知道了，我会用支付宝支付的");
}
}
