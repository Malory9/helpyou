package com.helpyouJFinal.service;

import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Message;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class MessageService {
	
	/**
	 * 得到一个用户的所有接受的信息
	 * @param receiverId 信息接受方的id
	 * @return 包含所有信息接受方message的一个list
	 */
	public List<Message> getMessageListByReceiverId(Integer receiverId) {
		String sql = "SELECT * FROM message WHERE receiverId=? ORDER BT time DESC";
		return Message.dao.find(sql, receiverId);
	}
	
	/**
	 * 添加一条新的信息记录
	 * @param senderId 发送方id
	 * @param receiverId 接受方id
	 * @param content 信息内容
	 */
	public void addNewMessage(Integer senderId,Integer receiverId,String content) {
		//新建一条记录
		Record message = new Record().set("senderId", senderId).set("receiverId", receiverId).set("time", new Date()).set("content", content);
		//保存记录
		Db.save("message", "messageId", message);
	}

}
