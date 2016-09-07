package com.helpyouJFinal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Message;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class MessageService {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * 得到一个用户的所有接受的信息
	 * @param receiverId 信息接受方的id
	 * @return 包含所有信息接受方message的一个list
	 */
	public List<Message> getMessageListByReceiverId(Integer receiverId) {
		String sql = "SELECT * FROM message WHERE receiverId=? ORDER BY time DESC";
		return Message.dao.find(sql, receiverId);
	}
	
	/**
	 * 添加一条新的留言记录
	 * @param senderId 发送方id
	 * @param receiverId 接受方id
	 * @param content 信息内容
	 */
	public void addNewMessage(Integer senderId,Integer receiverId,String content) {
		//新建一条留言记录
		new Message().set("senderId", senderId).set("receiverId", receiverId).set("time", new Date()).set("content", content).save();
	}
	
	/**
	 * 查询用户未读留言数量
	 * @param lastLoginTime 用户上次登录时间
	 * @return 未读通知的数量
	 */
	public Long getUnreadMessageNum(Date lastLoginTime){
		String sql = "select count(*) from message where time > ?";
		Long unreadMessageNum = Db.queryFirst(sql, simpleDateFormat.format(lastLoginTime));
		if (unreadMessageNum == null) {
			unreadMessageNum = 0L;
		}
		return unreadMessageNum;
	}
}
