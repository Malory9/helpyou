package com.helpyouJFinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AJAXManagerAuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		//获取管理员id
		Integer adminId = controller.getParaToInt("adminId");
		if (adminId == null || adminId <= 0) {
			controller.redirect("/manager/login");
		}
		inv.invoke();
	}

}
