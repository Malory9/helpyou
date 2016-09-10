package com.helpyouJFinal.interceptor;

import com.helpyouJFinal.model.Admin;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class ManagerAuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		//获取管理员
		Admin admin = controller.getSessionAttr("admin");
		if (admin == null) {
			controller.redirect("/manager/login");
		}
		inv.invoke();
	}

}
