package com.helpyouJFinal.model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	
	//方便在service中操作的一个final对象dao
	public static final User dao = new User();
	
}
