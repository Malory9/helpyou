package com.helpyouJFinal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Notice;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class NoticeService {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * 查询所有通知
	 * @return 通知的一个列表
	 */
	public List<Notice> getAllNotice(){
		String sql = "select * from notice";
		return Notice.dao.find(sql);
	}
	
	/**
	 * 查询用户未读通知数量
	 * @param lastLoginTime 用户上次登录时间
	 * @return 未读通知的数量
	 */
	public Long getUnreadNoticeNum(Date lastLoginTime){
		String sql = "select count(*) from notice where time > ?";
		return Db.queryFirst(sql, simpleDateFormat.format(lastLoginTime));
	}
	
	/**
	 * 添加一条新的通知记录
	 * @param senderId 发送方id
	 * @param receiverId 接受方id
	 * @param content 信息内容
	 */
	public void addNewNotice(String title, String content) {
		// 新建一条通知记录
		new Notice().set("title", title)
						.set("time", new Date()).set("content", content).save();
	}
	
}
