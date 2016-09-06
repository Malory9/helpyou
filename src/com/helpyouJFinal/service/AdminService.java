package com.helpyouJFinal.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class AdminService {

<<<<<<< HEAD
	/**
	 * 管理员登录
	 * @param adminname 管理员账户
	 * @param password 密码
	 * @return 管理员ID
	 */
=======
>>>>>>> dd79e600cc3c71135998e44a198fe852675ddefd
	public Integer login(String adminname, String password) {
		String SQL = "select adminId from admin where adminname=? and password=?";
		Integer adminId = Db.queryFirst(SQL, adminname, password);
		if (adminId != null) {
			return adminId;
		} else {
			return 0;
		}
	}
}
