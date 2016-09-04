package com.helpyouJFinal.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		//检验用户名是否为空
		validateRequiredString("username", "errorMessage", "用户名不能为空");
		//检验密码是否为空
		validateRequiredString("password", "errorMessage", "密码不能为空");
	}

	@Override
	protected void handleError(Controller c) {
		//保持request中的username参数
		c.keepPara("username");
		c.render("login.jsp");
	}

}
