package com.helpyouJFinal.service;

import com.helpyouJFinal.model.Admin;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class AdminService {

	/**
	 * 管理员登录
	 * @param adminname 管理员账户
	 * @param password 密码
	 * @return 管理员ID
	 */
	public Admin login(String adminname, String password) {
		String sql = "select * from admin where adminname=? and password=?";
		Admin admin = Admin.dao.findFirst(sql, adminname,password);
		if (admin != null) {
			return admin;
		} else {
			return null;
		}
	}
}
