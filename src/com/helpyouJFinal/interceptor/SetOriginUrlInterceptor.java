package com.helpyouJFinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class SetOriginUrlInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller =  inv.getController();
		//获得相对地址
		String uri;
		if (controller.getPara()!=null) {
			uri = inv.getActionKey()+"/"+controller.getPara();
		} else {
			uri = inv.getActionKey();
		}
		//将原先的地址存储在session中
		controller.setSessionAttr("originURL", uri);
		inv.invoke();
	}

}
