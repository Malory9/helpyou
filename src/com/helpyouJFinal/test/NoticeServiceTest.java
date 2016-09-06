package com.helpyouJFinal.test;

import java.util.Date;

import org.junit.Test;

import com.helpyouJFinal.service.NoticeService;

public class NoticeServiceTest extends JFinalTestBase {

	@Test
	public void getUnreadNoticeNumTest() {
		NoticeService noticeService = new NoticeService();
		Date date = new Date();
		date.setDate(1);
		System.out.println(noticeService.getUnreadNoticeNum(date));
	}
}
