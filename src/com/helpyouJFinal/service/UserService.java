package com.helpyouJFinal.service;

import java.util.Date;

import com.helpyouJFinal.model.User;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

//在执行的过程中如果有异常将回滚，如果return false 就也回滚
@Before(Tx.class)
public class UserService {
	
	/**
	 * 注册用户
	 * @param username 用户名
	 * @param password  密码
	 * @return 是否注册成功
	 */
	public boolean add(String username,String password) {
		String SQL = "select userId from user where username=?";
		Integer reslut = Db.queryFirst(SQL, username);
		if (reslut == null) {
			Record user = new Record().set("username", username).set("password", password);
			Db.save("user", user);
			return true;
		}
		return false;
	}
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password  密码
	 * @return 是否登录成功
	 */
	public Integer login(String username,String password) {
		String SQL = "select userId from user where username=? and password=?";
		Integer userId = Db.queryFirst(SQL, username,password);
		if (userId != null) {
//			通过Record和Db操作数据库
//			Record user = new Record().set("userId", userId).set("username", username).set("password", password).set("lastLoginTime", new Date());
//			Record user = Db.findById("user", "userId",userId).set("lastLoginTime", new Date());
//			Db.update("user","userId", user);
			User.dao.findById(userId).set("lastLoginTime", new Date()).update();
			return userId;
		}else {
			return 0;
		}
	}
}
