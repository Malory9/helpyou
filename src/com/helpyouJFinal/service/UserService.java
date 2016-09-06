package com.helpyouJFinal.service;

import java.util.Date;

import com.helpyouJFinal.model.User;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
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
	 * @return 注册完成后的用户model实例
	 */
	public User add(String username,String password) {
		String SQL = "select userId from user where username=?";
		Integer reslut = Db.queryFirst(SQL, username);
		if (reslut == null || reslut <= 0) {
			new User().set("username", username).set("password", password).set("nickname", username).set("lastLoginTime", new Date()).save();
			String userSQL = "select * from user where username=? and password=?";
			User user = new User().findFirst(userSQL, username,password);
			return user;
		}
		return null;
	}
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password  密码
	 * @return 是否登录成功
	 */
	public User login(String username,String password) {
		//查询是否存在对应用户
		String SQL = "select userId from user where username=? and password=?";
		Integer userId = Db.queryFirst(SQL, username,password);
		if (userId != null) {
			User user = User.dao.findById(userId);
			return user;
		}else {
			return null;
		}
	}
	
	/**
	 * 更新用户信息
	 * @param userId 查询用的用户id
	 * @param nickname 用户昵称
	 * @param sex 用户性别
	 * @param age 用户年龄
	 */
	public User updateUserInfo(Integer userId,String nickname,String sex,Integer age) {
		User user = new User().findById(userId);
		if (!StrKit.isBlank(nickname)) {
			user.set("nickname", nickname);
		}
		if (!StrKit.isBlank(sex)) {
			user.set("sex", sex);
		}
		if (age != null) {
			user.set("age", age);
		}
		user.update();
		return user;
	}
	
	/**
	 * 获得一个用户实例
	 * @param userId 用户id
	 * @return 用户的model实例
	 */
	public User getUserSpecific(Integer userId) {
		return new User().findById(userId);
	}
}
