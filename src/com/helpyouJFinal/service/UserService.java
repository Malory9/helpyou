package com.helpyouJFinal.service;

import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.User;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

//在执行的过程中如果有异常将回滚，如果return false 就也回滚
@Before(Tx.class)
public class UserService {
	
	/**
	 * 获得所有的用户信息
	 * @return 用户信息列表
	 */
	public List<User> getAllUsers() {
		String sql = "select * from user";
		return User.dao.find(sql);
	}
	
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
			new User().set("username", username).set("password", password).set("nickname", username).set("sex", "男").set("lastLoginTime", new Date()).save();
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
		User user = User.dao.findById(userId);
		if (!StrKit.isBlank(nickname)) {
			user.set("nickname", nickname);
		}
		if (!StrKit.isBlank(sex) &&  !sex.equals("未选择")) {
			user.set("sex", sex);
		}
		if (age != null && age > 0) {
			user.set("age", age);
		}
		user.update();
		return User.dao.findById(userId);
	}
	
	/**
	 * 获得一个用户实例
	 * @param userId 用户id
	 * @return 用户的model实例
	 */
	public User getUserSpecific(Integer userId) {
		return new User().findById(userId);
	}
	
	/**
	 * 获得一个用户的nickname
	 * @param userId 用户Id
	 * @return 用户的nickname
	 */
	public String getUserNickname(Integer userId) {
		String sql = "select nickname from user where userId = ?";
		return User.dao.findFirst(sql,userId).getStr("nickname");
	}
	
	/**
	 * 通过昵称获得用户ID
	 * @param nickname 用户昵称
	 * @return 用户id
	 */
	public Integer getUserId(String nickname){
		String sql = "select userId from user where nickname = ?";
		return User.dao.findFirst(sql,nickname).getInt("userId");
	}
	
	/**
	 * 冻结用户
	 * @param userId 用户id
	 * @return 是否冻结成功
	 */
	public boolean freezeUser(Integer userId) {
		return User.dao.findById(userId).set("state", 2).update();
	}
	
	/**
	 * 解冻用户
	 * @param userId 用户id
	 * @return 是否解冻成功
	 */
	public boolean unfreezeUser(Integer userId) {
		return User.dao.findById(userId).set("state", 1).update();
	}
	
	/**
	 * 检测用户积分是否足够发布任务
	 * @param userId 用户Id
	 * @param pointUsed 要花费的积分
	 * @return 积分足够返回true，不够返回false
	 */
	public boolean canPublish(Integer userId,Integer pointUsed) {
		Integer userPoint = User.dao.findById(userId).getInt("point");
		if (userPoint > pointUsed) {
			return true;
		} else {
			return false;
		}
	}
}
