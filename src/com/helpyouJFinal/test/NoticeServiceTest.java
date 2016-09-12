package com.helpyouJFinal.test;

import java.util.Date;

import org.junit.Test;

import com.helpyouJFinal.service.NoticeService;

import junit.framework.Assert;

public class NoticeServiceTest extends JFinalTestBase {

	@Test
	public void getUnreadNoticeNumTest() {
		NoticeService noticeService = new NoticeService();
		Date date = new Date();
		date.setDate(1);
		System.out.println(noticeService.getUnreadNoticeNum(date));
	}
	
	@Test
	public void addNoticeTest(){
		NoticeService noticeService = new NoticeService();
		Assert.assertEquals(true, noticeService.addNewNotice("测试公告", "测试公告内容"));
	}
}
