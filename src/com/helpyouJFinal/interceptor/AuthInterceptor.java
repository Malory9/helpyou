package com.helpyouJFinal.interceptor;

import com.helpyouJFinal.model.User;
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
		Controller controller = invocation.getController();
		User user = controller.getSessionAttr("user");
		Integer userId = null;
		if (user != null) {
			userId = user.getInt("userId");
			if (userId > 0) {
				invocation.invoke();
			} else {
				controller.redirect("/login");
			}
		}else {
			controller.redirect("/login");
		}
	}

}
