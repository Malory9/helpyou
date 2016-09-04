package com.helpyouJFinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;


/**
 * @author Administrator
 * 用于拦截未登录情况下的非浏览操作
 */
public class AuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation invocation) {
		Integer loginUserId;
				
		Controller controller = invocation.getController();
		//判断用户是否有修改信息的权限
		loginUserId = controller.getSessionAttr("userId");
		if (loginUserId > 0) {
			invocation.invoke();
		} else {
			controller.redirect("/login");
		}
	}

}
